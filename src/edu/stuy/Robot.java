
package edu.stuy;

import edu.stuy.commands.ArmsNarrowCommand;
import edu.stuy.subsystems.Acquirer;
import edu.stuy.subsystems.Arms;
import edu.stuy.subsystems.Drivetrain;
import edu.stuy.subsystems.Lift;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static edu.stuy.RobotMap.*;
import edu.stuy.commands.auton.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static final Drivetrain drivetrain = new Drivetrain();
    public static final Acquirer acquirer = new Acquirer();
    public static final Arms arms = new Arms();
    public static final Lift lift = new Lift();
    public static OI oi;

    Command autonomousCommand;
    SendableChooser autonChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();
        
        autonChooser = new SendableChooser();
        autonChooser.addDefault("1. Do nothing", new CommandGroup());
        autonChooser.addObject("2. Drive forward from Driver Side", new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE));
        autonChooser.addObject("3. Drive forward from Field Side", new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_FIELD_SIDE));
        // -1 means that AutonDriveForwardInches uses INCHES_LABEL. Defers reading of Smartdashboard value until auton starts
        autonChooser.addObject("4. Drive forward Custom Amount", new AutonDriveForwardInchesCommand(-1));
        autonChooser.addObject("5. Lift up Totes", new AutonLiftUpCommand());
        SmartDashboard.putData("Auton setting", autonChooser);
        SmartDashboard.putNumber(INCHES_LABEL, -1);
        
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        autonomousCommand = (Command) autonChooser.getSelected();
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
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
