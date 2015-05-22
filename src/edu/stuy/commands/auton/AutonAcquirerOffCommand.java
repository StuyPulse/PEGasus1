package edu.stuy.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.stuy.Robot;

/**
 * Autonomous command that stops both acquirer wheels from running 
 */
public class AutonAcquirerOffCommand extends Command {

    public AutonAcquirerOffCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.leftAcquirer);
        requires(Robot.rightAcquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.leftAcquirer.stop();
        Robot.rightAcquirer.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
