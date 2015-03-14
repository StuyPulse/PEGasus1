package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUpTenInchesCommand extends Command {

    private double startHeight;

    public LiftUpTenInchesCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startHeight = Robot.lift.getLiftEncoder();
        Robot.lift.goUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return 10 - (Robot.lift.getLiftEncoder() - startHeight) <= 0;
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
