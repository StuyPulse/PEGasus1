package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class AutonDriveForwardInchesCommand extends Command {

    private double inches;
    private double startTime;
    private boolean usingCustomDistance;
    
    /** 
     * If distance < 0, get it from SmartDashboard
     * @param dist
     */

    public AutonDriveForwardInchesCommand(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        usingCustomDistance = dist < 0;
        inches = dist;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (usingCustomDistance) {
            inches = SmartDashboard.getNumber(INCHES_LABEL);
        }
        double speed = getRampSpeed();
        Robot.drivetrain.tankDrive(speed, speed);
    }
    
    /**
     * Ramping algorithm for linear acceleration and smooth velocity
     * Takes one second to ramp up to max speed
     * if t < 0.5, then speed = 2t^2;
     * if t < 1, then speed = -2t^2 + 4t - 1;
     * if t >= 1, then speed = 1;
     * @return the speed the robot should go at the current time
     */
    
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
        return speed / 2;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDistance() >= inches || Timer.getFPGATimestamp() - startTime >= AUTON_DRIVETRAIN_TIMEOUT;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
