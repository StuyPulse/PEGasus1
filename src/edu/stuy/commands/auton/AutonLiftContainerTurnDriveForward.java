package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import edu.stuy.commands.DrivetrainRotateCommand;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The robot starts collinear with the auton set, with the hook in the can.
 * 1) Lift up the can.
 * 2) Turn 90° to the left.
 * 3) Drive forward to the auton zone.
 */
public class AutonLiftContainerTurnDriveForward extends CommandGroup {

    public  AutonLiftContainerTurnDriveForward() {
        addSequential(new LiftUpInchesCommand(15.0));
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateCommand(-90) , 2.0);
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
