package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Rotates the robot about a wheel via encoder values
 */

public class DrivetrainRotateWithEncodersCommand extends Command {

    private double startLeftEncoderValue;
    private double startRightEncoderValue;
    private double degrees;
    private double leftDistanceToGo;
    private double rightDistanceToGo;

    public DrivetrainRotateWithEncodersCommand(double _degrees) {
        requires(Robot.drivetrain);
        startLeftEncoderValue = Robot.drivetrain.getLeftEncoder();
        startRightEncoderValue = Robot.drivetrain.getRightEncoder();
        degrees = _degrees;
        if (degrees < 0) {
            leftDistanceToGo = 0.0;
            rightDistanceToGo = DRIVETRAIN_ENCODER_ROTATE_CIRCUMFERENCE * degrees / 360;
        } else if (degrees > 0) {
            leftDistanceToGo = DRIVETRAIN_ENCODER_ROTATE_CIRCUMFERENCE * degrees / 360;
            rightDistanceToGo = 0.0;
        } else {
            leftDistanceToGo = 0.0;
            rightDistanceToGo = 0.0;
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (degrees > 0) {
            Robot.drivetrain.tankDrive(0.5 , 0.0);
        } else if (degrees < 0) {
            Robot.drivetrain.tankDrive(0.0 , 0.5);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (degrees > 0) {
            return Robot.drivetrain.getLeftEncoder() - startLeftEncoderValue >= leftDistanceToGo;
        } else if (degrees < 0){
            return Robot.drivetrain.getRightEncoder() - startRightEncoderValue >= rightDistanceToGo;
        } else {
            return true; // Degree value is 0, do not rotate and exit immediately
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
