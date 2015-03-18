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
        double input = squareInput(Robot.oi.operatorPad.getRightY()) * 0.75;
        Robot.lift.manualControl(-input);
    }

    // Square the value of the input while preserving its sign
    private double squareInput(double input) {
        boolean negative = input < 0;
        input = input * input;
        if (negative) {
            input = -input;
        }
        return input;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // This is the Lift's default command.
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
