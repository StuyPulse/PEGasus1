package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.DrivetrainTankDriveCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon frontLeftMotor;
    private CANTalon rearLeftMotor;
    private CANTalon frontRightMotor;
    private CANTalon rearRightMotor;
    private RobotDrive robotDrive;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Gyro gyro;

    public Drivetrain() {
        frontLeftMotor = new CANTalon(DRIVE_FRONT_LEFT_ID);
        rearLeftMotor = new CANTalon(DRIVE_REAR_LEFT_ID);
        frontRightMotor = new CANTalon(DRIVE_FRONT_RIGHT_ID);
        rearRightMotor = new CANTalon(DRIVE_REAR_RIGHT_ID);
        robotDrive = new RobotDrive(
                frontLeftMotor,
                rearLeftMotor,
                frontRightMotor,
                rearRightMotor);

        leftEncoder = new Encoder(DRIVETRAIN_ENCODER_LEFT_CHANNEL_A, DRIVETRAIN_ENCODER_LEFT_CHANNEL_B, false);
        leftEncoder.setDistancePerPulse(DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE);
        // Reverse right encoder
        rightEncoder = new Encoder(DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A, DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B, true);
        rightEncoder.setDistancePerPulse(DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE);
 
        gyro = new Gyro(DRIVETRAIN_GYRO_CHANNEL);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DrivetrainTankDriveCommand());
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    //For PID only:
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInput) {
        robotDrive.arcadeDrive(moveValue, rotateValue, squaredInput);
    }

    public void setBrakeMode(boolean on) {
        frontLeftMotor.enableBrakeMode(on);
        rearLeftMotor.enableBrakeMode(on);
        frontRightMotor.enableBrakeMode(on);
        rearRightMotor.enableBrakeMode(on);
    }

    public void reset() {
        resetEncoders();
        resetGyro();
        stop();
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void stop() {
        robotDrive.tankDrive(0, 0);
    }

    public double getDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
    }
    
    public double getLeftEncoder() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoder() {
        return rightEncoder.getDistance();
    }
    
    public double getGyroAngle() {
        return gyro.getAngle();
    }

}