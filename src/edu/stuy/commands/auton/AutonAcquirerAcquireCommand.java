package edu.stuy.commands.auton;

import edu.wpi.first.wpilibj.command.Command;

import edu.stuy.Robot;

/**
 * Autonomous command that runs both acquirer wheels to acquire
 */
public class AutonAcquirerAcquireCommand extends Command {

    public AutonAcquirerAcquireCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.leftAcquirer);
        requires(Robot.rightAcquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.leftAcquirer.acquire();
        Robot.rightAcquirer.acquire();
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
