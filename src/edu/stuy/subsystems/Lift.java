package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftStopCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon liftMotor;
    private Solenoid brakeOn;
    private Solenoid brakeOff;
    private DigitalInput limitSwitch;
    private Encoder liftEncoder;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
        brakeOn = new Solenoid(LIFT_SOLENOID_BRAKE_ON);
        brakeOff = new Solenoid(LIFT_SOLENOID_BRAKE_OFF);
        limitSwitch = new DigitalInput(LIFT_LIMIT_SWITCH_CHANNEL);
        liftEncoder = new Encoder(LIFT_ENCODER_CHANNEL_A, LIFT_ENCODER_CHANNEL_B);
        liftEncoder.setDistancePerPulse(LIFT_ENCODER_DISTANCE_PER_PULSE);
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
        if (isAtTop()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(1.0);
        }
    }

    public void goDown() {
        if (isAtBottom()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(-1.0);
        }
    }

    public void stop() {
        liftMotor.set(0.0);
        setBrake(true);
    }

    public boolean isAtBottom() {
        return !limitSwitch.get();
    }
    
    public boolean isAboveRecycleBinHeight() {
        return liftEncoder.get() >= LIFT_ENCODER_RECYCLE_BIN_HEIGHT;
    }
    
    public boolean isAtTop() {
        return liftEncoder.get() >= LIFT_ENCODER_MAX_HEIGHT;
    }

    public void runEncoderLogic() {
        if (isAtBottom()) {
            liftEncoder.reset();
        }
    }

}