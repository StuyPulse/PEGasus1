package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DrivetrainRotateCommand extends PIDCommand {

    private double degrees;

    public DrivetrainRotateCommand(double degrees) {
        this(degrees, DRIVE_ROTATE_P, DRIVE_ROTATE_I, DRIVE_ROTATE_D);
    }
    
    public DrivetrainRotateCommand(double _degrees, double p, double i, double d) {
        super(p, i, d);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        degrees = _degrees;
        getPIDController().setContinuous();
        getPIDController().setInputRange(0, 360);
        getPIDController().setOutputRange(-1, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetGyro();
    }

    // Does not get called because we subclass PIDCommand
    protected void execute() {}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(returnPIDInput() - degrees) <= DRIVETRAIN_ROTATE_THRESHOLD_DEGREES;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    @Override
    protected double returnPIDInput() {
        double temp = Robot.drivetrain.getGyroAngle() % 360;
 
        // Ensure that our gyro value is in the range [0, 360).
        if (temp < 0) {
            temp += 360;
        }
        return temp;
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use arcade drive for easier rotation
        // Squared input is disabled so the robot turns correctly
        Robot.drivetrain.arcadeDrive(0, output, false);
    }
}
