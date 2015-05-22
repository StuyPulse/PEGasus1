package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.*;
import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous that lifts up the specified distance, then drives backwards
 * to the auton zone over the scoring platform
 */
public class AutonLiftAndDriveBackwardsOverBump extends CommandGroup {

    public  AutonLiftAndDriveBackwardsOverBump() {
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        addSequential(new AutonDriveBackwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES - ROBOT_LENGTH - TOTE_WIDTH + 12.0 , AUTON_DRIVETRAIN_TIMEOUT));
        // Note we start on the landfill side of the bin. Same distance as drive forward tote set auton, but 1 tote width less
    }
}
