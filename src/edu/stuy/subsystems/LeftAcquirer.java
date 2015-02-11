package edu.stuy.subsystems;

import edu.stuy.commands.AcquirerLeftStopCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

public class LeftAcquirer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon leftRoller;

    public LeftAcquirer() {
        leftRoller = new CANTalon(ACQUIRER_LEFT_ROLLER_ID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new AcquirerLeftStopCommand());
    }

    public void acquire() {
        leftRoller.set(-1.0);
    }

    public void stop() {
        leftRoller.set(0.0);
    }

    public void release() {
        leftRoller.set(1.0);
    }
}
