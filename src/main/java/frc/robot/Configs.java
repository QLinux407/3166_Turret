package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.Units;



import com.ctre.phoenix6.signals.NeutralModeValue;

import org.ejml.equation.VariableType;


import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.UpdateModeValue;

import edu.wpi.first.units.Units;





public class Configs {
  public static final class IndexerConfigs {

     public static final TalonFXConfiguration IdexerConfig = new TalonFXConfiguration();
     public static final TalonFXConfiguration siConfig = new TalonFXConfiguration();
    static{
      siConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 0.05;
      siConfig.MotorOutput.PeakForwardDutyCycle = 0.6;
      siConfig.MotorOutput.PeakReverseDutyCycle = -0.6;
      siConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      siConfig.CurrentLimits.SupplyCurrentLimit = 40;

      IdexerConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 0.05;
      IdexerConfig.MotorOutput.PeakForwardDutyCycle =0.95;
      IdexerConfig.MotorOutput.PeakReverseDutyCycle = -0.95;
      IdexerConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
      IdexerConfig.CurrentLimits.SupplyCurrentLimit = 40;

      

    }
    
  }
  public static final class ShooterConfigs {
     public static final TalonFXConfiguration s1Configs = new TalonFXConfiguration();
     public static final TalonFXConfiguration s2Configs = new TalonFXConfiguration();
     static{
      s1Configs.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 0.5;
      s1Configs.MotorOutput.PeakForwardDutyCycle = 0.7;
      s1Configs.MotorOutput.PeakReverseDutyCycle = -0.7;
      s1Configs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      s1Configs.CurrentLimits.SupplyCurrentLimit = 40;

      s2Configs.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = 0.5;
      s2Configs.MotorOutput.PeakForwardDutyCycle = 0.7;
      s2Configs.MotorOutput.PeakReverseDutyCycle = -0.7;
      s2Configs.MotorOutput.NeutralMode = NeutralModeValue.Coast;
      s2Configs.CurrentLimits.SupplyCurrentLimit = 40;
  
     }
    
  }
}