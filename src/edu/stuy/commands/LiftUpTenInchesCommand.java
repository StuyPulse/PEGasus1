package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static edu.stuy.RobotMap.*;

/**
 *
 */
public class LiftUpTenInchesCommand extends Command {

    private double startHeight;
    private double startTime;

    public LiftUpTenInchesCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startHeight = Robot.lift.getLiftEncoder();
        startTime = Timer.getFPGATimestamp();
        Robot.lift.goUp();
    }

    protected boolean checkDistance() {
        return 10 - (Robot.lift.getLiftEncoder() - startHeight) <= 0;
    }

    protected boolean checkTimeout() {
        return Timer.getFPGATimestamp() - startTime > AUTON_LIFT_TIMEOUT;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return checkDistance() && checkTimeout();
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
