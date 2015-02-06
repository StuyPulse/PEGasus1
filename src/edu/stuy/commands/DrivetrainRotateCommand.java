package edu.stuy.commands;

import static edu.stuy.RobotMap.*;
import edu.stuy.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class DrivetrainRotateCommand extends PIDCommand {

    private double degrees;

    public DrivetrainRotateCommand(double deg) {
        super(DRIVE_ROTATE_P, DRIVE_ROTATE_I, DRIVE_ROTATE_D);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        degrees = deg;
        getPIDController().setContinuous();
        getPIDController().setInputRange(0, 360);
        getPIDController().setOutputRange(-1, 1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

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
        Robot.drivetrain.arcadeDrive(0, output);
    }
}
