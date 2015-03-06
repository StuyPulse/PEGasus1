package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.AcquirerRightStopCommand;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RightAcquirer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private VictorSP rightRoller;

    public RightAcquirer() {
        rightRoller = new VictorSP(ACQUIRER_RIGHT_ROLLER_ID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new AcquirerRightStopCommand());
    }

    public void acquire() {
        rightRoller.set(ACQUIRER_ROLLER_SPEED);
    }

    public void stop() {
        rightRoller.set(0.0);
    }

    public void release() {
        rightRoller.set(-ACQUIRER_ROLLER_SPEED);
    }

}
