package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_LIFT_DISTANCE;
import edu.stuy.commands.DrivetrainRotateCommand;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The robot starts collinear with the auton set, with the hook in the tote.
 * 1) Lift up the tote.
 * 2) Turn 90° to the right.
 * 3) Drive forward to the auton zone.
 */
public class AutonLiftToteTurnDriveForward extends CommandGroup {

    public  AutonLiftToteTurnDriveForward() {
        // We start with the tote in the acquirer via narrow side
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateCommand(90));
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
