package edu.stuy.subsystems;

import static edu.stuy.RobotMap.ACQUIRER_LEFT_ROLLER_ID;
import static edu.stuy.RobotMap.ACQUIRER_ROLLER_SPEED;

import com.ctre.CANTalon;

import edu.stuy.commands.AcquirerLeftStopCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * LeftAcquirer controls the left acquirer wheel
 * The acquirer wheels are on the arms to acquire totes and RCs
 */

public class LeftAcquirer extends Subsystem {

    private CANTalon leftRoller;

    public LeftAcquirer() {
        leftRoller = new CANTalon(ACQUIRER_LEFT_ROLLER_ID);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new AcquirerLeftStopCommand());
    }

    public void acquire() {
        leftRoller.set(-ACQUIRER_ROLLER_SPEED);
    }

    public void stop() {
        leftRoller.set(0.0);
    }

    public void release() {
        leftRoller.set(ACQUIRER_ROLLER_SPEED);
    }
}
