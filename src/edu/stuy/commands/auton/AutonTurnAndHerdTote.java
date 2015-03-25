package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import edu.stuy.commands.DrivetrainRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Robot starts collinear with the auton set, with the acquirer grasping the tote.
 * 1) Acquire the tote.
 * 2) Turn 90° to the right.
 * 3) Drive forward to the auton zone.
 */
public class AutonTurnAndHerdTote extends CommandGroup {

    public  AutonTurnAndHerdTote() {
        addSequential(new AutonAcquirerAcquireCommand());
        addSequential(new AutonAcquirerOffCommand());
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateCommand(90) , 2.0);
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
