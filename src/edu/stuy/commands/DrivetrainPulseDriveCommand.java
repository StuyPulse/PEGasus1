package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainPulseDriveCommand extends Command {

    /* ______      ______
     * |     |_____|     |__ ...
     *  T_on  T_off
     *
     * T_on is the duration of time (in milliseconds) that the motor spins
     * T_off is the duration of time (in milliseconds) that the motor is not spinning
     * T_off_max is the maximum time (in milliseconds) that T_off can be.
     * 0.0 <= T_off <= T_off_max
     *
     * The cycle will restart if the joysticks are not being pushed.
     * Each cycle is at most 150 ms, and there is an "on time" and an "off time."
     */

    private double T_on;
    private double T_off;
    private double T_off_max;
    private double cycleStart;
    private boolean inCycle;

    private double currentSpeedMultiplier;

    public DrivetrainPulseDriveCommand() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        T_on = 30.0;
        T_off = 120.0;
        T_off_max = 120.0;
        currentSpeedMultiplier = 1.0;
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
                pulse(-left, -right, false);
            } else {
                pulse(-left, -right, true);
            }
        }
    }

    private void pulse(double joystickLeft, double joystickRight, boolean lowSpeed) {
        if (lowSpeed) {
            currentSpeedMultiplier = DRIVETRAIN_SLOWNESS_FACTOR;
        } else {
            currentSpeedMultiplier = 1.0;
        }
        if (Math.abs(joystickLeft) < 0.05 && Math.abs(joystickRight) < 0.05) {
            Robot.drivetrain.tankDrive(0.0, 0.0);
            inCycle = false;
            return;
        }
        if (inCycle) {
            double currentTime = System.currentTimeMillis() - cycleStart;
            if (currentTime < T_on) {
                Robot.drivetrain.tankDrive(joystickLeft * currentSpeedMultiplier, joystickRight * currentSpeedMultiplier);
            } else if (currentTime < T_on + T_off) {
                Robot.drivetrain.tankDrive(0.0 , 0.0);
            } else {
                inCycle = false;
            }
        } else {
            inCycle = true;
            cycleStart = System.currentTimeMillis();
            T_off = Math.abs((T_on + T_off_max) * (1 - Math.abs(joystickRight + joystickLeft) / 2));
            Robot.drivetrain.tankDrive(joystickLeft * currentSpeedMultiplier, joystickRight * currentSpeedMultiplier);
        }
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