package edu.stuy;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.ArmsSetNarrowCommand;
import edu.stuy.commands.LiftUpInchesCommand;
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
    public static CanGrabber canner;
    public static ToteKnocker toteknocker;
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
        canner = new CanGrabber();
        toteknocker = new ToteKnocker();
        oi = new OI();

        setupAutonChooser();
    }

    private void setupAutonChooser() {
        autonChooser = new SendableChooser();
        // Driving backward from Scoring Platform means we are doing a mobility auton routine without pushing totes
        autonChooser.addObject("1. Drive backward from Scoring Platform", new AutonDriveBackwardInchesCommand(AUTON_DRIVE_BACKWARD_SCORING_PLATFORM_INCHES, AUTON_DRIVE_BACKWARD_SCORING_PLATFORM_TIMEOUT));
        autonChooser.addObject("2. Drive forward from Driver Side with arms narrow to push tote", new AutonOneTotePushForwardCommand());
        autonChooser.addObject("3. Drive forward from Driver Side with arms released to push both tote and bin", new AutonOneSetPushForwardCommand());
        // -1 means that AutonDriveForwardInches/AutonDriveBackwardInches uses INCHES_LABEL. Defers reading of SmartDashboard value until auton starts
        autonChooser.addObject("4. Drive forward Custom Amount", new AutonDriveForwardInchesCommand(-1, AUTON_DRIVETRAIN_TIMEOUT));
        autonChooser.addObject("5. Drive backward Custom Amount", new AutonDriveBackwardInchesCommand(-1, AUTON_DRIVETRAIN_TIMEOUT));
        autonChooser.addObject("6. Lift ten inches then do auton 3", new AutonLiftAndDrive());
        autonChooser.addObject("7. Lift ten inches, then do nothing", new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        autonChooser.addObject("8. Lift ten inches, then drive backwards", new AutonLiftAndDriveBackwards());
        autonChooser.addObject("9. Lift ten inches, then drive backwards over bump" , new AutonLiftAndDriveBackwardsOverBump());
        autonChooser.addObject("10. Lift tote to auton zone", new AutonLiftToteTurnDriveForward());
        autonChooser.addObject("11. Lift container to auton zone", new AutonLiftContainerTurnDriveForward());
        autonChooser.addObject("12. Lift container and herd tote to auton zone", new AutonLiftContainerHerdTote());
        autonChooser.addObject("13. Herd tote into auton zone", new AutonTurnAndHerdTote());
        autonChooser.addDefault("14. Do nothing", new CommandGroup());
        SmartDashboard.putData("Auton setting", autonChooser);
        SmartDashboard.putNumber(INCHES_LABEL, -1);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        drivetrain.resetEncoders();
        drivetrain.setBrakeMode(true);
        lift.setOverridden(false);
        autonomousCommand = (Command) autonChooser.getSelected();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Encoder:", Robot.drivetrain.getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder:", Robot.drivetrain.getRightEncoder());
        SmartDashboard.putNumber("Gyro: ", Robot.drivetrain.getGyroAngle());
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();

        // Initialize subsystem states:
        drivetrain.setBrakeMode(false);
        drivetrain.setSpeedUp(true);
        lift.setOverridden(false);
        canner.open(false);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        canner.open(true);
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
        SmartDashboard.putBoolean("Lift Overridden?" , Robot.lift.getOverridden());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
