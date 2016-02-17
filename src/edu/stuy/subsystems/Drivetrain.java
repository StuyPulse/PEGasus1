package edu.stuy.subsystems;

import static edu.stuy.RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE;
import static edu.stuy.RobotMap.DRIVETRAIN_ENCODER_LEFT_CHANNEL_A;
import static edu.stuy.RobotMap.DRIVETRAIN_ENCODER_LEFT_CHANNEL_B;
import static edu.stuy.RobotMap.DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A;
import static edu.stuy.RobotMap.DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B;
import static edu.stuy.RobotMap.DRIVETRAIN_GYRO_CHANNEL;
import static edu.stuy.RobotMap.DRIVE_FRONT_LEFT_ID;
import static edu.stuy.RobotMap.DRIVE_FRONT_RIGHT_ID;
import static edu.stuy.RobotMap.DRIVE_REAR_LEFT_ID;
import static edu.stuy.RobotMap.DRIVE_REAR_RIGHT_ID;
import edu.stuy.commands.DrivetrainTankDriveCommand;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

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
    private boolean speedUp;

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

        leftEncoder = new Encoder(DRIVETRAIN_ENCODER_LEFT_CHANNEL_A, DRIVETRAIN_ENCODER_LEFT_CHANNEL_B, true, EncodingType.k2X);
        leftEncoder.setDistancePerPulse(DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        rightEncoder = new Encoder(DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A, DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B, false, EncodingType.k2X);
        rightEncoder.setDistancePerPulse(DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

        gyro = new AnalogGyro(DRIVETRAIN_GYRO_CHANNEL);
        speedUp = true;
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

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void setSpeedUp(boolean b) {
        speedUp = b;
    }

    public void stop() {
        robotDrive.tankDrive(0, 0);
    }

    /**
     * Get the positive values of left and right encoder
     *
     * @return the larger of the absolute values of left and right encoder
     */

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public boolean isSpeedUp() {
        return speedUp;
    }

}
