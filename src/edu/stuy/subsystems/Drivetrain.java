package edu.stuy.subsystems;

import static edu.stuy.RobotMap.DRIVE_FRONT_LEFT_ID;
import static edu.stuy.RobotMap.DRIVE_FRONT_RIGHT_ID;
import static edu.stuy.RobotMap.DRIVE_REAR_LEFT_ID;
import static edu.stuy.RobotMap.DRIVE_REAR_RIGHT_ID;
import edu.stuy.commands.DrivetrainTankDrive;
import edu.wpi.first.wpilibj.CANTalon;
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
	private RobotDrive drivetrain;
	
	public Drivetrain() {
		frontLeftMotor = new CANTalon(DRIVE_FRONT_LEFT_ID);
		rearLeftMotor = new CANTalon(DRIVE_REAR_LEFT_ID);
		frontRightMotor = new CANTalon(DRIVE_FRONT_RIGHT_ID);
		rearRightMotor = new CANTalon(DRIVE_REAR_RIGHT_ID);
		drivetrain = new RobotDrive(
				frontLeftMotor,
				rearLeftMotor,
				frontRightMotor,
				rearRightMotor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DrivetrainTankDrive());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}

