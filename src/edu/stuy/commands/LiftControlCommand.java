package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftControlCommand extends Command {

    public LiftControlCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.operatorPad.getRawDPadUp()) {
    		Robot.lift.goUp();
    	} else if (Robot.oi.operatorPad.getRawDPadDown()) {
    		Robot.lift.goDown();
    	} else {
    		Robot.lift.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
