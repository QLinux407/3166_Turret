package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants.IndexMotorChannelConstants;
//import frc.robot.Constants.MotorChannelConstants;
//import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeChannelConstants;
import frc.robot.Constants.IntakeConstants;



public class Turret extends SubsystemBase{
    private TalonFx turretrot;
    private TalonFx turrethood;
    private TalonFx turretwheel;
    
    public Turret() {
        turretrot = new TalonFx(40);
        turrethood = new TalonFx(41);
        turretwheel = new TalonFx(42);

    }

    public void ShootTurret() {
       
        turretwheel(1);
    }

    public void stopTurret() {
    
        turretwheel(0)
    }
    
        
    



}
