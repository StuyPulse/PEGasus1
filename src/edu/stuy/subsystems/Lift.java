package edu.stuy.subsystems;

import static edu.stuy.RobotMap.LIFT_LOWER_LIMIT_SWITCH_CHANNEL;
import static edu.stuy.RobotMap.LIFT_MOTOR_ID;
import static edu.stuy.RobotMap.LIFT_UPPER_LIMIT_SWITCH_CHANNEL;
import edu.stuy.commands.LiftStopCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon liftMotor;

    private DigitalInput upperLimitSwitch;
    private DigitalInput lowerLimitSwitch;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
        lowerLimitSwitch = new DigitalInput(LIFT_LOWER_LIMIT_SWITCH_CHANNEL);
        upperLimitSwitch = new DigitalInput(LIFT_UPPER_LIMIT_SWITCH_CHANNEL);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftStopCommand());
    }

    public void goUp() {
        if (upperLimitSwitch.get()) {
            liftMotor.set(1.0);
        } else {
            stop();
        }
    }

    public void goDown() {
        if (lowerLimitSwitch.get()) {
            liftMotor.set(-1.0);
        } else {
            stop();
        }
    }

    public void stop() {
        liftMotor.set(0.0);
    }
    
    public boolean getUpperLimitSwitchHit() {
        return upperLimitSwitch.get();
    }
    
    public boolean getLowerLimitSwitchHit() {
        return lowerLimitSwitch.get();
    }
}

