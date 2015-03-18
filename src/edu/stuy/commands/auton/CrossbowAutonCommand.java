package edu.stuy.commands.auton;

import static edu.stuy.PhysicalConstants.LANDFILL_TO_AUTON_ZONE;
import static edu.stuy.RobotMap.*;
import edu.stuy.commands.CrossbowDownCommand;
import edu.stuy.commands.CrossbowUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossbowAutonCommand extends CommandGroup {

    public  CrossbowAutonCommand() {
        addSequential(new CrossbowDownCommand() , AUTON_CROSSBOW_TIMEOUT);
        addSequential(new AutonDriveForwardInchesCommand(LANDFILL_TO_AUTON_ZONE, AUTON_DRIVETRAIN_TIMEOUT_FROM_LANDFILL_TO_AUTON_ZONE));
        addSequential(new CrossbowUpCommand() , AUTON_CROSSBOW_TIMEOUT);
    }
}
