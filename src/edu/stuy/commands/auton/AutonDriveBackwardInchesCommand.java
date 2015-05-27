package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.*;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Autonomous command that drives backward a specified number of inches
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
        usingCustomDistance = _distance < 0;
        distance = _distance;
        timeout = _timeout;
        requires(Robot.drivetrain);
    }

    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

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

    protected boolean isFinished() {
        return Robot.drivetrain.getDistanceAbsolute() >= distance || Timer.getFPGATimestamp() - startTime >= timeout;
    }

    protected void end() {
        Robot.drivetrain.stop();
    }

    protected void interrupted() {
    }

}
