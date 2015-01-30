package edu.stuy.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
        addSequential(new AutonDriveForwardInches(25.0));
        addSequential(new AcquirerAcquireCommand());
        addSequential(new DrivetrainRotateCommand(90));
        addSequential(new AutonDriveForwardInches(112));
        

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
