package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs.IndexerConfigs;
import frc.robot.Constants.IndexCIDConstants;

import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.ShooterCIDConstants;
import com.ctre.phoenix6.hardware.TalonFX;


public class IndexerSubsystem extends SubsystemBase{
    private TalonFX IndexMotor;
    private TalonFX ShooterintakeMotor;
    public IndexerSubsystem() {
        IndexMotor = new TalonFX(IndexCIDConstants.k_indexMotor,"secondcanloop");
        ShooterintakeMotor = new TalonFX(ShooterCIDConstants.k_shooterIntake,"secondcanloop");
        
        IndexMotor.getConfigurator().apply(IndexerConfigs.IdexerConfig);
        ShooterintakeMotor.getConfigurator().apply(IndexerConfigs.siConfig);

    }

    public void startIndexerCommand() {
        IndexMotor.set(IndexerConstants.k_IndexerSpeed);
        ShooterintakeMotor.set(IndexerConstants.k_ShooterIntakeSpeed);
    }

    public void stopIndexerCommand() {
        IndexMotor.set(0);
        ShooterintakeMotor.set(0);
    }
    


}
