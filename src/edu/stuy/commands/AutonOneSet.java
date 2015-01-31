package edu.stuy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import static edu.stuy.RobotMap.*;

/**
 *
 */
public class AutonOneSet extends CommandGroup {
    
    public  AutonOneSet() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        addSequential(new ArmsNarrowCommand());
        addSequential(new AcquirerAcquireCommand());
        addSequential(new AutonLiftUpCommand());
        addSequential(new AutonDriveForwardInches(AUTON_ONE_SET_DRIVE_INCHES_FIRST));
        addSequential(new AcquirerAcquireCommand());
        addSequential(new DrivetrainRotateCommand(AUTON_ONE_SET_ROTATE_DEGREES));
        addSequential(new AutonDriveForwardInches(AUTON_ONE_SET_DRIVE INCHES_SECOND));
        

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
