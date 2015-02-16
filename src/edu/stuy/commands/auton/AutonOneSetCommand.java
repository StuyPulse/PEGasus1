package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.ArmsNarrowCommand;
import edu.stuy.commands.DrivetrainRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonOneSetCommand extends CommandGroup {
    
    public AutonOneSetCommand() {
        this(DRIVE_ROTATE_P, DRIVE_ROTATE_I, DRIVE_ROTATE_D);
    }
    
    public AutonOneSetCommand(double p, double i, double d) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        addSequential(new ArmsNarrowCommand());
        addSequential(new AutonAcquirerCommand());
        addSequential(new AutonLiftUpCommand());
        addSequential(new AutonDriveForwardInchesCommand(AUTON_ONE_SET_DRIVE_INCHES_FIRST, AUTON_DRIVE_FORWARD_DRIVER_SIDE_TIMEOUT));
        addSequential(new AutonAcquirerCommand());
        addSequential(new DrivetrainRotateCommand(AUTON_ONE_SET_ROTATE_DEGREES, p, i, d));
        addSequential(new AutonDriveForwardInchesCommand(AUTON_ONE_SET_DRIVE_INCHES_SECOND, AUTON_DRIVE_FORWARD_SCORING_PLATFORM_TIMEOUT));

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
