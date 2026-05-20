package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants.IndexMotorChannelConstants;
//import frc.robot.Constants.MotorChannelConstants;
//import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeChannelConstants;
import frc.robot.Constants.IntakeConstants;



public class Turret extends SubsystemBase{
    private Talon intakeElavator;
    private Talon intakeMotor;
    
    public Turret() {
        intakeElavator = new Talon(IntakeChannelConstants.k_Elavator);
        intakeMotor = new Talon(IntakeChannelConstants.k_MainIntake);
        

    }

    public void ShootTurret() {
       
        intakeMotor.set(IntakeConstants.k_MainIntakeSpeed);
    }

    public void stopTurret() {
    
        intakeMotor.set(0);
    }
    
        
    



}
