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

        if (Robot.oi.driverPad.getRawLeftTrigger()) {
            Robot.drivetrain.setSpeedUp(false);
        }
        if (Robot.oi.driverPad.getRawRightTrigger()) {
            Robot.drivetrain.setSpeedUp(true);
        }

        if (Robot.drivetrain.isOverridden()) {
            if (Robot.drivetrain.isSpeedUp()) {
                Robot.drivetrain.tankDrive(-left, -right);
            } else {
                Robot.drivetrain.tankDrive(-left * DRIVETRAIN_SLOWNESS_FACTOR, -right * DRIVETRAIN_SLOWNESS_FACTOR);
            }
        } else {
            if (Robot.drivetrain.isSpeedUp()) {
                Robot.drivetrain.tankDrive(ramp(-left, Robot.drivetrain.getLeftVoltage()), ramp(-right, Robot.drivetrain.getRightVoltage()));
            } else {
                Robot.drivetrain.tankDrive(ramp(-left, Robot.drivetrain.getLeftVoltage() * DRIVETRAIN_SLOWNESS_FACTOR), ramp(-right, Robot.drivetrain.getRightVoltage() * DRIVETRAIN_SLOWNESS_FACTOR));
            }
        }
    }

    private double ramp(double input, double currentVoltage) {
        return currentVoltage + (input - currentVoltage) * RAMP_CONSTANT;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // This is Drivetrain's default command.
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