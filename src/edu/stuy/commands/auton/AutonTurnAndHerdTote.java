package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import edu.stuy.commands.DrivetrainRotateNoPIDCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Robot starts collinear with the auton set, with the acquirer grasping the tote.
 * 1) Acquire the tote.
 * 2) Turn 90° to the right.
 * 3) Drive forward to the auton zone.
 */
public class AutonTurnAndHerdTote extends CommandGroup {

    public  AutonTurnAndHerdTote() {
        // addParallel(new AutonAcquirerAcquireCommand());
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateNoPIDCommand(90));
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
