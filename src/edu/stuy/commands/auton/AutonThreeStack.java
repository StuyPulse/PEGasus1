package edu.stuy.commands.auton;

import static edu.stuy.RobotMap.AUTON_LIFT_DISTANCE;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.stuy.commands.*;

/**
 *
 */
public class AutonThreeStack extends CommandGroup {
    
    public  AutonThreeStack() {
        addSequential(new LiftUpInchesCommand(AUTON_LIFT_DISTANCE));
        addSequential(new DrivetrainRotateNoPIDCommand(-30));
        
    }
}
