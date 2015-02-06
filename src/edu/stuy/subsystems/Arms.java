package edu.stuy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

public class Arms extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Solenoid shortPistonOut;
    private Solenoid shortPistonIn;
    private Solenoid longPistonOut;
    private Solenoid longPistonIn;

    public Arms() {
        shortPistonOut = new Solenoid(SOLENOID_ARMS_SHORT_OUT);
        shortPistonIn = new Solenoid(SOLENOID_ARMS_SHORT_IN);
        longPistonOut = new Solenoid(SOLENOID_ARMS_LONG_OUT);
        longPistonIn = new Solenoid(SOLENOID_ARMS_LONG_IN);
    }

    private void setShortPiston(boolean out) {
        shortPistonOut.set(out);
        shortPistonIn.set(!out);
    }

    private void setLongPiston(boolean out) {
        longPistonOut.set(out);
        longPistonIn.set(!out);
    }

    /**
     * Retracts both pistons for the narrower side of the tote
     */
    public void setNarrow() {
        setShortPiston(false);
        setLongPiston(false);
    }

    /**
     * Retracts only the longer piston for the wider side of the tote
     */
    public void setWide() {
        setShortPiston(true);
        setLongPiston(false);
    }

    /**
     * Extends both pistons to release the tote
     */
    public void release() {
        setShortPiston(true);
        setLongPiston(true);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

