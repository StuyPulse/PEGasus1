package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the can grabber
 */
public class CanGrabberOpenCommand extends Command {

    public CanGrabberOpenCommand() {
        requires(Robot.canner);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.canner.open(true);
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