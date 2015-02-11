 package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainTankDriveCommand extends Command {
    
    public DrivetrainTankDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left = Robot.oi.driverPad.getLeftY();
        double right = Robot.oi.driverPad.getRightY();
        if (Robot.oi.driverPad.getRawLeftTrigger() || Robot.oi.driverPad.getRawRightTrigger()) {
            // Fast mode (when a trigger is pressed)
            Robot.drivetrain.tankDrive(-left, -right);
        } else {
            // Slow mode (default)
            Robot.drivetrain.tankDrive(-left * DRIVETRAIN_SLOWNESS_FACTOR, -right * DRIVETRAIN_SLOWNESS_FACTOR);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
