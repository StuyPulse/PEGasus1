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
    int LIFT_SOLENOID_BRAKE_ON = 4;
    int LIFT_SOLENOID_BRAKE_OFF = 5;

    // Joystick Ports
    int DRIVER_PAD_PORT = 0;
    int OPERATOR_PAD_PORT = 1;

    // Digital IO Ports:
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_A = 0;
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_B = 1;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A = 2;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B = 3;
    int LIFT_LOWER_LIMIT_SWITCH_CHANNEL = 4;
    int LIFT_LOWER_ENCODER_CHANNEL_A = 5;
    int LIFT_LOWER_ENCODER_CHANNEL_B = 6;

    // Analog Channels
    int DRIVETRAIN_GYRO_CHANNEL = 0;
    
    // Auton Constants
    int DRIVETRAIN_ROTATE_THRESHOLD = 1;
    double AUTON_ONE_SET_DRIVE_INCHES_FIRST = 25.0;
    double AUTON_ONE_SET_ROTATE_DEGREES = 90.0;
    double AUTON_ONE_SET_DRIVE_INCHES_SECOND = 112.0;

    // Tote Dimensions (inches)
    double TOTE_WIDTH = 16.9;
    double TOTE_LENGTH = 26.9;

    // Robot Dimensions approx. (inches)
    double ROBOT_LENGTH = 28.0;

    // Auton Mobility DriveForward
    double AUTON_DRIVE_FORWARD_FIELD_SIDE = 112.0;
    double AUTON_DRIVE_FORWARD_DRIVER_SIDE = AUTON_DRIVE_FORWARD_FIELD_SIDE
            + TOTE_WIDTH + ROBOT_LENGTH;
    String INCHES_LABEL = "If you are using Setting 4, then input number of inches";

    //Lift Encoder Distance
    double LIFT_ENCODER_RECYCLE_BIN_HEIGHT = 100.0;
    double LIFT_ENCODER_MAX_HEIGHT = 200.0;
}
