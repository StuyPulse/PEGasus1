package edu.stuy.subsystems;

import edu.stuy.commands.AcquirerStopCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

/**
 *
 */
public class Acquirer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    CANTalon leftRoller;
    CANTalon rightRoller;

    public Acquirer() {
        leftRoller = new CANTalon(ACQUIRER_LEFT_ROLLER_ID);
        rightRoller = new CANTalon(ACQUIRER_RIGHT_ROLLER_ID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new AcquirerStopCommand());
    }

    public void acquire() {
        leftRoller.set(1.0);
        rightRoller.set(1.0);
    }

    public void stop() {
        leftRoller.set(0.0);
        rightRoller.set(0.0);
    }

    public void release() {
        leftRoller.set(-1.0);
        rightRoller.set(-1.0);
    }
}

