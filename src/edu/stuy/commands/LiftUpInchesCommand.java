package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static edu.stuy.RobotMap.*;

/**
 * Moves the lift up a specified distance
 */
public class LiftUpInchesCommand extends Command {

    private double startHeight;
    private double startTime;
    private double distance;

    public LiftUpInchesCommand(double _distance) {
        requires(Robot.lift);
        distance = _distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startHeight = Robot.lift.getLiftEncoderDistance();
        startTime = Timer.getFPGATimestamp();
        Robot.lift.goUp();
    }

    protected double getDistanceTraveled() {
        return Robot.lift.getLiftEncoderDistance() - startHeight;
    }

    protected boolean checkDistance() {
        return distance - getDistanceTraveled() <= 0;
    }

    protected double getTimeElapsed() {
        return Timer.getFPGATimestamp() - startTime;
    }

    protected boolean checkTimeout() {
        return getTimeElapsed() >= AUTON_LIFT_TIMEOUT;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return checkDistance() || checkTimeout();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
