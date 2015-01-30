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
    int DRIVE_FRONT_LEFT_ID = 1;
    int DRIVE_REAR_LEFT_ID = 2;
    int DRIVE_FRONT_RIGHT_ID = 3;
    int DRIVE_REAR_RIGHT_ID = 4;

    int ACQUIRER_LEFT_ROLLER_ID = 5;
    int ACQUIRER_RIGHT_ROLLER_ID = 6;

    int LIFT_MOTOR_ID = 7;

    // Solenoid Ports:
    int SOLENOID_ARMS_SHORT_IN = 0;
    int SOLENOID_ARMS_SHORT_OUT = 1;
    int SOLENOID_ARMS_LONG_IN = 2;
    int SOLENOID_ARMS_LONG_OUT = 3;

    // Joystick ports
    int DRIVER_PAD_PORT = 0;
    int OPERATOR_PAD_PORT = 1;
    
    // Drivetrain channels:
    int DRIVETRAIN_ENCODER_CHANNEL_A = 0;
    int DRIVETRAIN_ENCODER_CHANNEL_B = 1;
    int DRIVETRAIN_GYRO_CHANNEL = 2;
    
    // Lift limit switch channels:
    int LIFT_LOWER_LIMIT_SWITCH_CHANNEL = 3;
    int LIFT_UPPER_LIMIT_SWITCH_CHANNEL = 4;
}
