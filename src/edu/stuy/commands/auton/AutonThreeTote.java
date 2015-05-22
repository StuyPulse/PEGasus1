package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.*;
import edu.stuy.commands.ArmsSetNarrowCommand;
import edu.stuy.commands.DrivetrainRotateNoPIDCommand;
import edu.stuy.commands.LiftDownInchesCommand;
import edu.stuy.commands.LiftUpInchesCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonThreeTote extends CommandGroup {
    
    public  AutonThreeTote() {
        addParallel(new AutonAcquirerAcquireCommand(), 15);
        addSequential(new DrivetrainRotateNoPIDCommand(30));
        addSequential(new AutonDriveForwardInchesCommand(10, 2));
        addSequential(new DrivetrainRotateNoPIDCommand(-30));
        addSequential(new AutonDriveForwardInchesCommand(50, 5));
        addSequential(new LiftUpInchesCommand(RECYCLING_BIN_HEIGHT+TOTE_HEIGHT));
        addSequential(new AutonDriveForwardInchesCommand(24, 3));
        addSequential(new AutonDriveBackwardInchesCommand(4, 3));
        addSequential(new LiftDownInchesCommand(RECYCLING_BIN_HEIGHT + TOTE_HEIGHT));
        addSequential(new ArmsSetNarrowCommand());
        addSequential(new AutonDriveForwardInchesCommand(24, 3));
        addSequential(new AutonDriveBackwardInchesCommand(4, 3));
        addSequential(new LiftDownInchesCommand(RECYCLING_BIN_HEIGHT + TOTE_HEIGHT));
        addSequential(new AutonDriveForwardInchesCommand(10, 3));
        addSequential(new ArmsSetNarrowCommand());
    }
}
