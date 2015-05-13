package edu.stuy.subsystems;

import edu.stuy.commands.CanGrabberCloseCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

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

    public void toggle() {
        if (opened) {
            open(false);
        } else {
            open(true);
        }
    }

    public void open(boolean open) {
        cannerSolenoidOpen.set(open);
        cannerSolenoidClose.set(!open);
        opened = open;
    }

}