package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.*;
import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLiftAndDriveBackwards extends CommandGroup {

    public  AutonLiftAndDriveBackwards() {
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        addSequential(new AutonDriveBackwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES - ROBOT_LENGTH - TOTE_WIDTH, AUTON_DRIVETRAIN_TIMEOUT));
        // Note we start behind bin. Same distance as drive forward tote set auton, but 1 tote width less
    }
}
