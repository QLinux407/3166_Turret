package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class TurretPos {
    // Initialize Kraken X60/X66
    private final TalonFX turretMotor;
    
    // Pre-allocate the closed-loop position request object to save RAM
    private final PositionVoltage positionRequest = new PositionVoltage(0);
    
    // Hardware gear ratio (Motor Spins : Turret Spins)
    private static final double GEAR_RATIO = 10.5; 

    /**
     * Constructor for the Turret Position Controller.
     * @param canId The CAN ID assigned to the Kraken motor controller.
     */
    public TurretPos(int canId) {
        turretMotor = new TalonFX(canId);
        configureMotor();
    }

    /**
     * Configures the Kraken motor with your linear, no-wrap, and safety settings.
     */
    private void configureMotor() {
        TalonFXConfiguration configs = new TalonFXConfiguration();
        
        // --- 1. GEAR RATIO SETUP ---
        // Scales motor encoder math directly to final Turret Rotations
        configs.Feedback.SensorToMechanismRatio = GEAR_RATIO; 
        
        // --- 2. NO-WRAP LINEAR SETTINGS ---
        configs.ClosedLoop.ContinuousWrap = false; // FORCES linear -359 deg return trip instead of 1 deg shortcut
        configs.MotorOutput.NeutralMode = NeutralModeValue.Brake; // Locks turret when no input is sent
        configs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive; // Flips direction if needed

        // --- 3. TURRET PID + FEEDFORWARD GAINS ---
        configs.Slot0.kP = 10.0; // 10 Volts applied per 1 full rotation of error
        configs.Slot0.kI = 0.0;  // Leave at 0 to avoid dangerous overshoot
        configs.Slot0.kD = 0.3;  // Dampens shakes/oscillations caused by turret inertia
        configs.Slot0.kS = 0.2;  // Overcomes static friction of the turret ring bearings

        // --- 4. WIRE PROTECTION (SOFTWARE LIMITS) ---
        // Units are in Turret Rotations (1.0 = 360 degrees).
        configs.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 1.1; // Allows a tiny bit past 360 deg (+396 deg)
        
        configs.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = -0.1; // Allows a tiny bit past 0 deg (-36 deg)

        // Push all configurations permanently to the Kraken controller microprocessor
        turretMotor.getConfigurator().apply(configs);
        
        // Assumes you aligned the turret to physical 0 (center) before powering on
        zeroSensor();
    }

    /**
     * Force-zeros the sensor position at the current physical orientation.
     */
    public void zeroSensor() {
        turretMotor.setPosition(0.0);
    }

    /**
     * Commands the turret to a target position in degrees.
     * @param targetDegrees The target angle (e.g., 360.0 or 1.0).
     */
    public void setTargetDegrees(double targetDegrees) {
        // Convert degrees to mechanism rotations (360 degrees = 1.0 rotation)
        double targetRotations = targetDegrees / 360.0;
        turretMotor.setControl(positionRequest.withPosition(targetRotations));
    }

    /**
     * Gets the current position of the turret in rotations.
     */
    public double getPositionRotations() {
        return turretMotor.getPosition().getValueAsDouble();
    }

    /**
     * Gets the current position of the turret in degrees.
     */
    public double getPositionDegrees() {
        return getPositionRotations() * 360.0;
    }
}
