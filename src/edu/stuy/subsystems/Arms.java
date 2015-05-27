package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.util.ArmsPosition;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Arms controls the acquirer arms on the robot
 * Has three positions, narrow, wide, and release
 */

public class Arms extends Subsystem {

    private Solenoid shortPistonOut;
    private Solenoid shortPistonIn;
    private Solenoid longPistonOut;
    private Solenoid longPistonIn;
    private ArmsPosition armsPosition;

    public Arms() {
        shortPistonOut = new Solenoid(PCM_1 , SOLENOID_ARMS_SHORT_OUT);
        shortPistonIn = new Solenoid(PCM_1 , SOLENOID_ARMS_SHORT_IN);
        longPistonOut = new Solenoid(PCM_1 , SOLENOID_ARMS_LONG_OUT);
        longPistonIn = new Solenoid(PCM_1 , SOLENOID_ARMS_LONG_IN);
        armsPosition = ArmsPosition.NARROW;
    }

    private void setShortPistonOut(boolean out) {
        shortPistonOut.set(out);
        shortPistonIn.set(!out);
    }

    private void setLongPistonOut(boolean out) {
        longPistonOut.set(out);
        longPistonIn.set(!out);
    }

    /**
     * Retracts both pistons for the narrower side of the tote
     */
    public void setNarrow() {
        setShortPistonOut(false);
        setLongPistonOut(false);
        armsPosition = ArmsPosition.NARROW;
    }

    /**
     * Extends only the shorter piston for the wider side of the tote
     */
    public void setWide() {
        setShortPistonOut(true);
        setLongPistonOut(false);
        armsPosition = ArmsPosition.WIDE;
    }

    /**
     * Extends both pistons to release the tote
     */
    public void release() {
        setShortPistonOut(true);
        setLongPistonOut(true);
        armsPosition = ArmsPosition.RELEASE;
    }

    public ArmsPosition getCurrentPosition() {
        return armsPosition;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

