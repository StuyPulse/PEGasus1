package edu.stuy.commands.auton;

import edu.stuy.commands.DrivetrainRotateNoPIDCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonThreeTote extends CommandGroup {
    
    public  AutonThreeTote() {
        addSequential(new DrivetrainRotateNoPIDCommand(30));
    }
}
