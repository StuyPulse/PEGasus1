package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import edu.stuy.commands.ArmsSetNarrowCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The robot starts behind the tote.
 * 1) Set arms to narrow.
 * 2) Drive forward to the auton zone.
 */
public class AutonOneTotePushForwardCommand extends CommandGroup {

    public  AutonOneTotePushForwardCommand() {
        addSequential(new ArmsSetNarrowCommand());
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
