package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants.IndexMotorChannelConstants;
//import frc.robot.Constants.MotorChannelConstants;
//import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeChannelConstants;
import frc.robot.Constants.IntakeConstants;



public class IntakeSubsystem extends SubsystemBase{
    private Talon intakeElavator;
    private Talon intakeMotor;
    
    public IntakeSubsystem() {
        intakeElavator = new Talon(IntakeChannelConstants.k_Elavator);
        intakeMotor = new Talon(IntakeChannelConstants.k_MainIntake);
        

    }

    public void startIntakeCommand() {
       
        intakeMotor.set(IntakeConstants.k_MainIntakeSpeed);
    }

    public void stopIntakeCommand() {
    
        intakeMotor.set(0);
    }
    public void MoveDownIntakeCommand() {
       
        intakeElavator.set(IntakeConstants. k_ElivatorMoveSpeedDown);
    }

    public void MoveUpIntakeCommand() {
    
        intakeElavator.set(IntakeConstants. k_ElivatorMoveSpeedUp);
    }
    public void StopIntakeElavator() {

        intakeElavator.set(0);
        
    }



}
