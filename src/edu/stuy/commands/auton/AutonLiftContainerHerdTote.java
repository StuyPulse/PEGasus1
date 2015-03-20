package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.TOTE_WIDTH;
import static edu.stuy.RobotMap.AUTON_DRIVETRAIN_TOTE_WIDTH_TIMEOUT;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES;
import static edu.stuy.RobotMap.AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT;
import edu.stuy.commands.DrivetrainRotateCommand;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonLiftContainerHerdTote extends CommandGroup {

    public  AutonLiftContainerHerdTote() {
        addSequential(new LiftUpInchesCommand(15.0));
        addParallel(new AutonAcquirerAcquireCommand());
        addSequential(new AutonDriveForwardInchesCommand(TOTE_WIDTH , AUTON_DRIVETRAIN_TOTE_WIDTH_TIMEOUT));
        addSequential(new AutonAcquirerOffCommand());
        // Tote is on the right from the driver's point of view, so we turn 90 degrees
        addSequential(new DrivetrainRotateCommand(-90) , 2.0);
        // Go to auton zone
        addSequential(new AutonDriveForwardInchesCommand(AUTON_DRIVE_FORWARD_DRIVER_SIDE_INCHES, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
    }
}
