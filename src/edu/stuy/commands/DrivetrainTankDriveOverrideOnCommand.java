package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns on the override for the ramping of the drivetrain
 */
public class DrivetrainTankDriveOverrideOnCommand extends Command {

    public DrivetrainTankDriveOverrideOnCommand() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.setOverridden(true);
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