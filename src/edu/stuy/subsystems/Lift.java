package edu.stuy.subsystems;

import edu.stuy.commands.LiftControlCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon liftMotor;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftControlCommand());
    }

    public void goUp() {
        liftMotor.set(1.0);
    }

    public void goDown() {
        liftMotor.set(-1.0);
    }

    public void stop() {
        liftMotor.set(0.0);
    }
}

