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
    int SOLENOID_ARMS_LONG_OUT = 0;
    int SOLENOID_ARMS_LONG_IN = 1;
    int SOLENOID_ARMS_SHORT_OUT = 2;
    int SOLENOID_ARMS_SHORT_IN = 3;
    int LIFT_SOLENOID_BRAKE = 4;

    // Joystick Ports
    int DRIVER_PAD_PORT = 0;
    int OPERATOR_PAD_PORT = 1;

    // Digital IO Ports:
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_A = 0;
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_B = 1;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A = 2;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B = 3;
    int LIFT_LIMIT_SWITCH_CHANNEL = 4;
    int LIFT_ENCODER_CHANNEL_A = 5;
    int LIFT_ENCODER_CHANNEL_B = 6;

    // Analog Channels
    int DRIVETRAIN_GYRO_CHANNEL = 0;
    
    // Auton Constants
    double DRIVETRAIN_ROTATE_THRESHOLD_DEGREES = 5.0;
    double AUTON_ONE_SET_DRIVE_INCHES_FIRST = 25.0;
    double AUTON_ONE_SET_ROTATE_DEGREES = 90.0;
    double AUTON_ONE_SET_DRIVE_INCHES_SECOND = 112.0; //If this is changed, also change AUTON_DRIVE_FORWARD_FIELD_SIDE
    double AUTON_ACQUIRE_TIME = 1;
    double AUTON_DRIVETRAIN_TIMEOUT = 2;
    double AUTON_LIFT_TIMEOUT = 5;

    // Tote Dimensions (inches)
    double TOTE_WIDTH = 16.9;
    double TOTE_LENGTH = 26.9;

    // Robot Dimensions approx. (inches)
    double ROBOT_LENGTH = 28.0;

    // Auton Mobility DriveForward
    double AUTON_DRIVE_FORWARD_FIELD_SIDE = 112.0; //If this is changed, also change AUTON_ONE_SET_DRIVE_INCHES_SECOND
    double AUTON_DRIVE_FORWARD_DRIVER_SIDE = AUTON_DRIVE_FORWARD_FIELD_SIDE
            + TOTE_WIDTH + ROBOT_LENGTH;
    //Displayed on Smart Dashboard
    String INCHES_LABEL = "If you are using Setting 4, then input number of inches";
    String PID_TUNING_P = "PID: p";
    String PID_TUNING_I = "PID: i";
    String PID_TUNING_D = "PID: d";

    // Encoder Distances
    double LIFT_ENCODER_RECYCLE_BIN_HEIGHT = 60.0;
    double LIFT_ENCODER_MAX_HEIGHT = 50.0;
    double LIFT_ENCODER_DISTANCE_PER_PULSE = 3 * Math.PI / 250;
    double DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE = 6 * Math.PI / 250;

    // PID Constants
    // TODO: NEED TO TUNE
    double DRIVE_ROTATE_P = .01;
    double DRIVE_ROTATE_I = 0;
    double DRIVE_ROTATE_D = 0;
}
