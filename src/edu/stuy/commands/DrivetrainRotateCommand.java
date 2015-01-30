package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;
import static edu.stuy.RobotMap.*;

/**
 *
 */
public class DrivetrainRotateCommand extends Command {

    private double degrees;
    
    public DrivetrainRotateCommand(double deg) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        degrees = deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.tankDrive(1.0, -1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getGyroAngle() >= degrees - DRIVETRAIN_ROTATE_THRESHOLD;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stopDriveStraight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
