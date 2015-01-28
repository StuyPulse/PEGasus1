package edu.stuy;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// CAN device IDs
	int DRIVE_FRONT_LEFT_ID = 5;
	int DRIVE_REAR_LEFT_ID = 6;
	int DRIVE_FRONT_RIGHT_ID = 3;
	int DRIVE_REAR_RIGHT_ID = 4;
	//TODO set IDs back to 5,6
	int ACQUIRER_LEFT_ROLLER_ID = 1;
	int ACQUIRER_RIGHT_ROLLER_ID = 2;
	
	// Solenoid Ports:
	int SOLENOID_ARMS_SHORT_IN = 0;
	int SOLENOID_ARMS_SHORT_OUT = 1;
	int SOLENOID_ARMS_LONG_IN = 2;
	int SOLENOID_ARMS_LONG_OUT = 3;
	
	// Joystick ports
	int DRIVER_PAD_PORT = 0;
	int OPERATOR_PAD_PORT = 1;
	
	// Joystick buttons:
	int ACQUIRER_RELEASE_LEFT_TRIGGER = 5;
	int ACQUIRER_RELEASE_RIGHT_TRIGGER = 6;
	int ACQUIRER_ACQUIRE_LEFT_TRIGGER = 7;
	int ACQUIRER_ACQUIRE_RIGHT_TRIGGER = 8;
	
	int ARMS_NARROW_BUTTON = 1;
	int ARMS_WIDE_BUTTON = 2;
	int ARMS_RELEASE_BUTTON = 3;
}
