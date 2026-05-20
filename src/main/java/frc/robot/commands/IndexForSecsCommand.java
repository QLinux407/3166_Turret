package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
public class IndexForSecsCommand extends Command{
    IndexerSubsystem m_IndexerSubsystem;
  double m_seconds;
  Timer m_timer = new Timer();


 // Constructor
  public IndexForSecsCommand(IndexerSubsystem IndexerSubsystem, double seconds) {
        
    // Definitions and setting parameters are equal to members!
    m_IndexerSubsystem = IndexerSubsystem;
    addRequirements(IndexerSubsystem);
    m_seconds = seconds;
  }

  // Reset timer when the command starts executing
  public void initialize() {
    m_timer.start();
    m_timer.reset();
  }
  
  // Actual command
  public void execute() {
      m_IndexerSubsystem.startIndexerCommand();
      SmartDashboard.putString("index end", "started");

  }

  // Stuff that happens when command is over
  public void end(boolean interrupted) {
    m_IndexerSubsystem.stopIndexerCommand();
    SmartDashboard.putString("index end", "ended");
  }

  // Checks if the command is done
  public boolean isFinished() {
    // Am I done?  Am I done? Am I finally done?
    return m_timer.hasElapsed(m_seconds);
  }
}
