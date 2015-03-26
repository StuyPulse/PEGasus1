package edu.stuy;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap extends PhysicalConstants {

    // CAN device IDs
    int DRIVE_FRONT_LEFT_ID = 1;
    int DRIVE_REAR_LEFT_ID = 2;
    int DRIVE_FRONT_RIGHT_ID = 3;
    int DRIVE_REAR_RIGHT_ID = 4;
    int ACQUIRER_LEFT_ROLLER_ID = 5;
    int ACQUIRER_RIGHT_ROLLER_ID = 6;
    int LIFT_MOTOR_ID = 7;

    // Solenoid Ports
    int SOLENOID_ARMS_LONG_OUT = 0;
    int SOLENOID_ARMS_LONG_IN = 1;
    int SOLENOID_ARMS_SHORT_OUT = 3;
    int SOLENOID_ARMS_SHORT_IN = 2;
    int LIFT_SOLENOID_BRAKE = 5;

    // Joystick Ports
    int DRIVER_PAD_PORT = 0;
    int OPERATOR_PAD_PORT = 1;

    // Digital IO Ports
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_A = 2;
    int DRIVETRAIN_ENCODER_LEFT_CHANNEL_B = 3;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_A = 0;
    int DRIVETRAIN_ENCODER_RIGHT_CHANNEL_B = 1;
    int LIFT_LIMIT_SWITCH_CHANNEL = 4;
    int LIFT_ENCODER_CHANNEL_A = 5;
    int LIFT_ENCODER_CHANNEL_B = 6;

    // Analog Channels
    int DRIVETRAIN_GYRO_CHANNEL = 0;

    // Auton Constants
    double DRIVETRAIN_ROTATE_THRESHOLD_DEGREES = 5.0;
    double AUTON_DRIVETRAIN_TIMEOUT = 15.0;
    double AUTON_DRIVETRAIN_TOTE_WIDTH_TIMEOUT = 3.0;
    double AUTON_LIFT_TIMEOUT = 3.5;
    double AUTON_LIFT_DISTANCE = 10.0;
    double AUTON_TOTE_COLLISION_AVOIDANCE_OFFSET = 9.0;

    // Auton Mobility DriveForward
    double AUTON_DRIVE_BACKWARD_SCORING_PLATFORM_INCHES = AUTON_ZONE_WIDTH / 2 + 12;
    double AUTON_DRIVE_BACKWARD_SCORING_PLATFORM_TIMEOUT = 5.2;
    double AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES = TOTE_SET_TO_LANDMARK_INCHES + TOTE_WIDTH + 12;
    double AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT = 15;

    // Displayed on Smart Dashboard
    String INCHES_LABEL = "If you are using Setting 4 or 5, then input number of inches";

    // Encoder Distances
    double LIFT_ENCODER_RECYCLING_BIN_HEIGHT = RECYCLING_BIN_HEIGHT + TOTE_HEIGHT + 4.0;
    double LIFT_ENCODER_MAX_HEIGHT = 50.0;
    double LIFT_SPROCKET_CIRCUMFERENCE = 3 * Math.PI;
    double DRIVETRAIN_WHEEL_CIRCUMFERENCE = 6 * Math.PI;
    double PULSES_PER_REVOLUTION = 250;
    double LIFT_ENCODER_INCHES_PER_PULSE = LIFT_SPROCKET_CIRCUMFERENCE / PULSES_PER_REVOLUTION;
    double DRIVETRAIN_ENCODER_INCHES_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE / PULSES_PER_REVOLUTION;

    // PID Constants
    // MAYBE WE'LL BE NICE AND USE THESE??? UNTESTED!!!
    double DRIVE_ROTATE_P = .01;
    double DRIVE_ROTATE_I = 0;
    double DRIVE_ROTATE_D = 0;

    // Tank drive squares the inputs given to Robot.drivetrain.tankDrive in
    // DrivetrainTankDriveCommand, so to produce 0.5 as the speed multiplier we
    // take the square root of 0.5
    double DRIVETRAIN_SLOWNESS_FACTOR = Math.sqrt(0.5);
    double ACQUIRER_ROLLER_SPEED = 0.75;

}
