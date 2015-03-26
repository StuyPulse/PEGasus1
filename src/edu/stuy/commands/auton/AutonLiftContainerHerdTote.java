package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.TOTE_HEIGHT;
import static edu.stuy.PhysicalConstants.TOTE_WIDTH;
import static edu.stuy.RobotMap.AUTON_DRIVETRAIN_TOTE_WIDTH_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_TOTE_COLLISION_AVOIDANCE_OFFSET;
import edu.stuy.commands.DrivetrainRotateNoPIDCommand;
import edu.stuy.commands.LiftDownInchesCommand;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The robot starts collinear with the auton set, with the hook in the can.
 * 1) Lift up the can.
 * 2) Drive forward and acquire the tote.
 * 3) Turn 90° to the left.
 * 4) Drive forward to the auton zone.
 * 5) Drop the can onto the tote.
 */
public class AutonLiftContainerHerdTote extends CommandGroup {

    public  AutonLiftContainerHerdTote() {
        addSequential(new LiftUpInchesCommand(TOTE_HEIGHT + AUTON_TOTE_COLLISION_AVOIDANCE_OFFSET));
        addParallel(new AutonAcquirerAcquireCommand());
        addSequential(new AutonDriveForwardInchesCommand(TOTE_WIDTH , AUTON_DRIVETRAIN_TOTE_WIDTH_TIMEOUT));
        addSequential(new AutonAcquirerOffCommand());
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateNoPIDCommand(-90) , 2.0);
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
        addSequential(new LiftDownInchesCommand(TOTE_HEIGHT + AUTON_TOTE_COLLISION_AVOIDANCE_OFFSET));
    }
}
