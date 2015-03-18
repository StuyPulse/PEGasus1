package edu.stuy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static edu.stuy.RobotMap.*;

public class Crossbow extends Subsystem {

    private Solenoid crossbowOut;
    private Solenoid crossbowIn;

    public Crossbow() {
        crossbowOut = new Solenoid(CROSSBOW_SOLENOID_OUT);
        crossbowIn = new Solenoid(CROSSBOW_SOLENOID_IN);
    }

    public void extend() {
        crossbowOut.set(true);
        crossbowIn.set(false);
    }

    public void retract() {
        crossbowOut.set(false);
        crossbowIn.set(true);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

