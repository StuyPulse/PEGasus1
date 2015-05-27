package edu.stuy.subsystems;

import edu.stuy.commands.CanGrabberCloseCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;


/**
 * CanGrabber represents the can-grabbing mechanism of PEGasus1
 * Can open and close using a double solenoid
 */

public class CanGrabber extends Subsystem {

    private Solenoid cannerSolenoidOpen;
    private Solenoid cannerSolenoidClose;
    private boolean opened;

    public CanGrabber() {
        cannerSolenoidOpen = new Solenoid(PCM_2, CANNER_SOLENOID_OPEN);
        cannerSolenoidClose = new Solenoid(PCM_2 , CANNER_SOLENOID_CLOSE);
    }

    public void initDefaultCommand() {
    }

    /**
     * Toggles the state of the can grabber from open to close
     */
    
    public void toggle() {
        if (opened) {
            open(false);
        } else {
            open(true);
        }
    }

    /**
     * Changes the state of the can grabber
     * @param open Whether or not the can grabber should be opened.
     */
    
    public void open(boolean open) {
        cannerSolenoidOpen.set(open);
        cannerSolenoidClose.set(!open);
        opened = open;
    }

}