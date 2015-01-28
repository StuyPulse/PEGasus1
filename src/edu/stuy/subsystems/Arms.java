package edu.stuy.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import static edu.stuy.RobotMap.*;

/**
 *
 */
public class Arms extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Solenoid shortPistonOut;
	Solenoid shortPistonIn;
	Solenoid longPistonOut;
	Solenoid longPistonIn;
    
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
    
    public void setNarrow() {
    	setShortPiston(false);
    	setLongPiston(false);
    }
    
    public void setWide() {
    	setShortPiston(false);
    	setLongPiston(true);
    }
    
    public void release() {
    	setShortPiston(false);
    	setLongPiston(false);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

