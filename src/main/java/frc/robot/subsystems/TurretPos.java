package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class TurretPos {

    private final TalonFX turretMotor;
    private final PositionVoltage positionRequest = new PositionVoltage(0);

    private static final double GEAR_RATIO = 10.5;

    /**
     * Constructor
     * @param canId CAN ID of the Kraken motor
     */
    public TurretPos(int canId) {
        turretMotor = new TalonFX(canId);
        configureMotor();
    }

    private void configureMotor() {
        TalonFXConfiguration configs = new TalonFXConfiguration();

        // Gear ratio
        configs.Feedback.SensorToMechanismRatio = GEAR_RATIO;

        // NO WRAPPING - linear behavior (this is what you asked for)
        configs.ClosedLoop.ContinuousWrap = false;

        configs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        // PID + Feedforward
        configs.Slot0.kP = 10.0;
        configs.Slot0.kI = 0.0;
        configs.Slot0.kD = 0.3;
        configs.Slot0.kS = 0.2;

        // Soft limits (in turret rotations)
        configs.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 1.1;   // ~396 deg
        configs.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        configs.SoftwareLimitSwitch.ReverseSoftLimitThreshold = -0.1;  // ~-36 deg

        turretMotor.getConfigurator().apply(configs);
        zeroSensor();
    }

    public void zeroSensor() {
        turretMotor.setPosition(0.0);
    }

    /**
     * MAIN FUNCTION - This is the one you should call
     * Sets the turret to an absolute position in degrees.
     * 
     * No wrapping: If turret is at 350° and you call turretpos(5), 
     * it will go the long way around (as requested).
     */
    public void turretpos(double targetDegrees) {
        double targetRotations = targetDegrees / 360.0;
        turretMotor.setControl(positionRequest.withPosition(targetRotations));
    }

    // ====================== Helper Methods ======================

    public double getPositionDegrees() {
        return turretMotor.getPosition().getValueAsDouble() * 360.0;
    }

    public double getPositionRotations() {
        return turretMotor.getPosition().getValueAsDouble();
    }

    /**
     * Optional: Relative movement (adds degrees to current position)
     */
    public void turretposRelative(double deltaDegrees) {
        double currentRot = getPositionRotations();
        double targetRot = currentRot + (deltaDegrees / 360.0);
        turretMotor.setControl(positionRequest.withPosition(targetRot));
    }
}