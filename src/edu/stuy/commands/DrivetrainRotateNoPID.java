package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Rotates the Robot without PID values
 */

public class DrivetrainRotateNoPID extends Command {

    private double degrees;
    private double startAngle;

    public DrivetrainRotateNoPID(double _degrees) {
        requires(Robot.drivetrain);
        degrees = _degrees;
        startAngle = Robot.drivetrain.getGyroAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.arcadeDrive(0, degrees , false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
            return Math.abs(startAngle - Robot.drivetrain.getGyroAngle()) >= degrees;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}