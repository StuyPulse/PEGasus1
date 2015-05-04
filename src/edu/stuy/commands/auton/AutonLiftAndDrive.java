package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_LIFT_DISTANCE;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLiftAndDrive extends CommandGroup {

    public  AutonLiftAndDrive() {
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES - 12, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
