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
    private CANTalon leftRoller;
    private CANTalon rightRoller;

    public Acquirer() {
        leftRoller = new CANTalon(ACQUIRER_LEFT_ROLLER_ID);
        rightRoller = new CANTalon(ACQUIRER_RIGHT_ROLLER_ID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new AcquirerStopCommand());
    }

    public void rightAcquire() {
        rightRoller.set(1.0);
    }

    public void leftAcquire() {
        leftRoller.set(-1.0);
    }

    public void stop() {
        leftRoller.set(0.0);
        rightRoller.set(0.0);
    }

    public void rightRelease() {
        rightRoller.set(-1.0);
    }

    public void leftRelease() {
        leftRoller.set(1.0);
    }
}

