package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class AutonDriveBackwardInchesCommand extends Command {

    private double distance;
    private double startTime;
    private double timeout;
    private boolean usingCustomDistance;

    /**
     * If distance < 0, get it from SmartDashboard.
     * @param _distance If negative, read desired inches from SmartDashboard.
     * @param _timeout Does not exceed this time.
     */
    public AutonDriveBackwardInchesCommand(double _distance, double _timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        usingCustomDistance = _distance < 0;
        distance = _distance;
        timeout = _timeout;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (usingCustomDistance) {
            distance = SmartDashboard.getNumber(INCHES_LABEL);
        }
        double speed = getRampSpeed();
        Robot.drivetrain.tankDrive(-speed, -speed);
    }

    /**
     * Ramping algorithm for linear acceleration and smooth velocity
     * Takes one second to ramp up to max speed
     * if t < 0.5, then speed = 2t^2;
     * if t < 1, then speed = -2t^2 + 4t - 1;
     * if t >= 1, then speed = 1;
     * @return the speed the robot should go at the current time
     */
    // This code exists in both forward and backward
    private double getRampSpeed() {
        double t = Timer.getFPGATimestamp() - startTime;
        double speed;
        if (t < 0.5) {
            speed = 2 * t * t;
        } else if (t < 1) {
            speed = -2 * t * t + 4 * t - 1;
        } else {
            speed = 1;
        }
        return speed * 0.6; // Lower maximum speed to .5
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDistanceAbsolute() >= distance || Timer.getFPGATimestamp() - startTime >= timeout;
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
