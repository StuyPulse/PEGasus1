package edu.stuy.commands.auton;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonLiftUpCommand extends Command {

    public AutonLiftUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.lift.goUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.getUpperLimitSwitchHit();
    }

    // Called once after isFinished returns true
    protected void end() {
        // No need to stop lift, as Robot.lift.goUp() automatically
        //  stops it once the limit switch is hit.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
