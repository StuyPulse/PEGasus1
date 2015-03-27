package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Rotates the Robot without PID values
 */

public class DrivetrainRotateNoPIDCommand extends Command {

    private double degrees;
    private double startAngle;

    public DrivetrainRotateNoPIDCommand(double _degrees) {
        requires(Robot.drivetrain);
        degrees = _degrees;
        startAngle = Robot.drivetrain.getGyroAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (degrees < 0) {
            Robot.drivetrain.tankDrive(-0.5, 0.5);
        } else if (degrees > 0) {
            Robot.drivetrain.tankDrive(0.5, -0.5);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double soFar = Math.abs(startAngle - Robot.drivetrain.getGyroAngle());
        if (degrees < 0) {
            return soFar <= degrees;
        } else if (degrees > 0) {
            return soFar >= degrees;
        } else {
            return true;
        }
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