
package edu.stuy;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.ArmsNarrowCommand;
import edu.stuy.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.stuy.commands.auton.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static Drivetrain drivetrain;
    public static LeftAcquirer leftAcquirer;
    public static RightAcquirer rightAcquirer;
    public static Arms arms;
    public static Lift lift;
    public static OI oi;

    Command autonomousCommand;
    SendableChooser autonChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        drivetrain = new Drivetrain();
        leftAcquirer = new LeftAcquirer();
        rightAcquirer = new RightAcquirer();
        arms = new Arms();
        lift = new Lift();

        oi = new OI();

        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(leftAcquirer);
        SmartDashboard.putData(rightAcquirer);
        SmartDashboard.putData(arms);
        SmartDashboard.putData(lift);
        SmartDashboard.putData(Scheduler.getInstance());

        setupAutonChooser();
    }

    private void setupAutonChooser() {
        autonChooser = new SendableChooser();
        autonChooser.addDefault("1. Do nothing", new CommandGroup());
        autonChooser.addObject("2. Drive forward from Driver Side", new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE));
        // Driving forward from Field Side means we are doing a mobility auton routine without pushing totes
        autonChooser.addObject("3. Drive forward from Field Side", new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_FIELD_SIDE));
        // -1 means that AutonDriveForwardInches uses INCHES_LABEL. Defers reading of Smartdashboard value until auton starts
        autonChooser.addObject("4. Drive forward Custom Amount", new AutonDriveForwardInchesCommand(-1));
        autonChooser.addObject("5. Lift up Totes", new AutonLiftUpCommand());
        autonChooser.addObject("6. Acquires set and drives forward (Currently doing PID tuning)", new AutonOneSetCommand());
        SmartDashboard.putData("Auton setting", autonChooser);
        SmartDashboard.putNumber(INCHES_LABEL, -1);
        SmartDashboard.putNumber(PID_TUNING_P, DRIVE_ROTATE_P);
        SmartDashboard.putNumber(PID_TUNING_I, DRIVE_ROTATE_I);
        SmartDashboard.putNumber(PID_TUNING_D, DRIVE_ROTATE_D);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        drivetrain.resetEncoders();
        drivetrain.setBrakeMode(true);
        autonomousCommand = (Command) autonChooser.getSelected();
        // Temporary for PID tuning
        if (autonomousCommand instanceof AutonOneSetCommand) {
            autonomousCommand = new AutonOneSetCommand(SmartDashboard.getNumber(PID_TUNING_P), SmartDashboard.getNumber(PID_TUNING_I), 
                    SmartDashboard.getNumber(PID_TUNING_D));
        }
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();

        // Initialize subsystem states:
        new ArmsNarrowCommand().start();
        drivetrain.resetGyro();
        drivetrain.setBrakeMode(false);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        lift.runEncoderLogic();
        SmartDashboard.putNumber("Left Encoder:", Robot.drivetrain.getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder:", Robot.drivetrain.getRightEncoder());
        SmartDashboard.putNumber("Gyro Angle:", Robot.drivetrain.getGyroAngle());
        SmartDashboard.putNumber("Lift Encoder:", Robot.lift.getLiftEncoder());
        SmartDashboard.putBoolean("Lift Limit Switch", Robot.lift.isAtBottom());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
