package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftStopCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon liftMotor;
    
    private Solenoid brakeOn;
    private Solenoid brakeOff;

    private DigitalInput lowerLimitSwitch;
    
    private Encoder liftEncoderDown;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
        brakeOn = new Solenoid(LIFT_SOLENOID_BRAKE_ON);
        brakeOff = new Solenoid(LIFT_SOLENOID_BRAKE_OFF);
        lowerLimitSwitch = new DigitalInput(LIFT_LOWER_LIMIT_SWITCH_CHANNEL);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftStopCommand());
    }
    
    private void setBrake(boolean on) {
        brakeOn.set(on);
        brakeOff.set(!on);
    }

    public void goUp() {
        if (getLiftAtMaxHeight()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(1.0);
        }
    }

    public void goDown() {
        // If limit switch is pressed, then returns false
        if (lowerLimitSwitch.get()) {
            setBrake(false);
            liftMotor.set(-1.0);
        } else {
            stop();
            liftEncoderDown.reset();
        }
    }

    public void stop() {
        liftMotor.set(0.0);
        setBrake(true);
    }
    
    public boolean getLowerLimitSwitchHit() {
        return lowerLimitSwitch.get();
    }
    
    public boolean getLiftAtRecycleBinHeight() {
        return Math.abs(LIFT_ENCODER_RECYCLE_BIN_HEIGHT - liftEncoderDown.get()) <= .001;
    }
    
    public boolean getLiftAtMaxHeight() {
        return Math.abs(LIFT_ENCODER_MAX_HEIGHT - liftEncoderDown.get()) <= .001;
    }
}

