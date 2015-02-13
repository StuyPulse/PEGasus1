package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftControlCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANTalon liftMotor;
    private Solenoid brake;
    private DigitalInput limitSwitch;
    private Encoder liftEncoder;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
        brake = new Solenoid(LIFT_SOLENOID_BRAKE);
        limitSwitch = new DigitalInput(LIFT_LIMIT_SWITCH_CHANNEL);
        liftEncoder = new Encoder(LIFT_ENCODER_CHANNEL_A, LIFT_ENCODER_CHANNEL_B);
        liftEncoder.setDistancePerPulse(LIFT_ENCODER_DISTANCE_PER_PULSE);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftControlCommand());
    }

    private void setBrake(boolean on) {
        brake.set(on);
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

    public void manualControl(double speed) {
        if (Math.abs(speed) < 0.1) {
            setBrake(true);
            liftMotor.set(0.0);
        } else if ((speed < 0 && isAtBottom()) || (speed > 0 && isAtTop())) {
            setBrake(true);
            liftMotor.set(0.0);
        } else {
            setBrake(false);
            liftMotor.set(speed);
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
        return getLiftEncoder() >= LIFT_ENCODER_RECYCLE_BIN_HEIGHT;
    }
    
    public boolean isAtTop() {
        return getLiftEncoder() >= LIFT_ENCODER_MAX_HEIGHT;
    }

    public void runEncoderLogic() {
        if (isAtBottom()) {
            liftEncoder.reset();
        }
    }
    
    public double getLiftEncoder() {
        return liftEncoder.getDistance();
    }

}