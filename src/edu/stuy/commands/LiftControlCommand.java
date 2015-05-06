package edu.stuy.commands;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftControlCommand extends Command {

    public LiftControlCommand() {
        requires(Robot.lift);
    }

    protected void initialize() {
    }

    protected void execute() {
        double right = Robot.oi.operatorPad.getRightY();
        if(Math.abs(right) > 0.0){
            Robot.toteknocker.retract();
        }
        double input = squareInput(right) * 0.75;
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
