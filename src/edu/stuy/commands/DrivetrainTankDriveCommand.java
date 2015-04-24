package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainTankDriveCommand extends Command {

    private double startTime;

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

        if (Math.abs(left) < .05 && Math.abs(right) < .05) {
            startTime = Timer.getFPGATimestamp();
        }

        if (Robot.oi.driverPad.getRawLeftTrigger() || Robot.oi.driverPad.getRawLeftBumper()) {
            Robot.drivetrain.setSpeedUp(false);
        }
        if (Robot.oi.driverPad.getRawRightTrigger() || Robot.oi.driverPad.getRawRightBumper()) {
            Robot.drivetrain.setSpeedUp(true);
        }

        if (Robot.drivetrain.isSpeedUp()) {
            if ((left < 0 && right > 0) || (left > 0 && right < 0)) { // Rotation does not use ramping
                Robot.drivetrain.tankDrive(-left, -right);
            } else {
                Robot.drivetrain.tankDrive(getRampSpeed(-left), getRampSpeed(-right));
            }
        } else {
            if ((left < 0 && right > 0) || (left > 0 && right < 0)) { // Rotation does not use ramping
                Robot.drivetrain.tankDrive(-left, -right);
            } else {
                Robot.drivetrain.tankDrive(getRampSpeed(-left) * DRIVETRAIN_SLOWNESS_FACTOR, getRampSpeed(-right) * DRIVETRAIN_SLOWNESS_FACTOR);
            }
        }
    }

    private double getRampSpeed(double m) {
        double t = Timer.getFPGATimestamp() - startTime;
        double speed;
        if (t < 0.5) {
            speed = 2 * t * t;
        } else if (t < 1) {
            speed = -2 * t * t + 4 * t - 1;
        } else {
            speed = 1;
        }
        return /* speed * */ m;
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
