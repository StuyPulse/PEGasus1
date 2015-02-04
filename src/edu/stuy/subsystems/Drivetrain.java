package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.DrivetrainTankDriveCommand;
import edu.stuy.util.StuyGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon frontLeftMotor;
    private CANTalon rearLeftMotor;
    private CANTalon frontRightMotor;
    private CANTalon rearRightMotor;
    private RobotDrive robotDrive;
    private Gyro gyro;
    
    private Encoder leftEncoder;
    private Encoder rightEncoder;

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
        
        leftEncoder = new Encoder(DRIVETRAIN_ENCODER_LEFT_CHANNEL_A, DRIVETRAIN_ENCODER_LEFT_CHANNEL_B);
        rightEncoder = new Encoder(DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A, DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B);
 
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
    
    public void reset() {
        resetEncoders();
        resetGyro();
        stopDriveStraight();
    }
    
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    public void resetGyro() {
        gyro.reset();
    }
    
    public void stopDriveStraight() {
        robotDrive.tankDrive(0, 0);
    }
    
    public double getDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
    }
    
    public double getGyroAngle() {
        return gyro.getAngle();
    }

}

