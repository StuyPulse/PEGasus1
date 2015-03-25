package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.TOTE_WIDTH;
import static edu.stuy.RobotMap.AUTON_DRIVETRAIN_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_LIFT_DISTANCE;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLiftAndDriveBackwards extends CommandGroup {

    public  AutonLiftAndDriveBackwards() {
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        addSequential(new AutonDriveBackwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES - TOTE_WIDTH, AUTON_DRIVETRAIN_TIMEOUT));
        // Note we start behind bin. Same distance as drive forward tote set auton, but 1 tote width less
    }
}
