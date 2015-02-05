package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.stuy.Robot;

/**
 *
 */
public class AutonAcquirerCommand extends Command {
    
    private double startTime;

    public AutonAcquirerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.acquirer.acquire();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= AUTON_ACQUIRE_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.acquirer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
