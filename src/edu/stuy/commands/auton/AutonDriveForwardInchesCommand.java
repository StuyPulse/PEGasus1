package edu.stuy.commands.auton;

import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.Command;
import static edu.stuy.RobotMap.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class AutonDriveForwardInchesCommand extends Command {

    private double inches;
    
    
    /** 
     * If distance < 0, get it from SmartDashboard
     * @param dist
     */

    public AutonDriveForwardInchesCommand(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        inches = dist;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (inches < 0) {
            inches = SmartDashboard.getNumber(INCHES_LABEL);
        }
        Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.tankDrive(1.0, 1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDistance() >= inches;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stopDriveStraight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
