package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import edu.stuy.commands.LiftControlCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lift controls the lift system on the robot
 * Lift picks up totes and RCs to allow for stacking
 */

public class Lift extends Subsystem {

    private CANTalon liftMotor;
    private Solenoid brake;
    private DigitalInput limitSwitch;
    private Encoder liftEncoder;
    private boolean overridden;

    public Lift() {
        liftMotor = new CANTalon(LIFT_MOTOR_ID);
        brake = new Solenoid(PCM_1 , LIFT_SOLENOID_BRAKE);
        limitSwitch = new DigitalInput(LIFT_LIMIT_SWITCH_CHANNEL);
        liftEncoder = new Encoder(LIFT_ENCODER_CHANNEL_A, LIFT_ENCODER_CHANNEL_B);
        liftEncoder.setDistancePerPulse(LIFT_ENCODER_INCHES_PER_PULSE);
        overridden = false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LiftControlCommand());
    }

    public void resetEncoders() {
        liftEncoder.reset();
    }

    private void setBrake(boolean on) {
        brake.set(on);
    }

    /**
     * Moves the lift upward unless the lift is already at the top
     */

    public void goUp() {
        if (isAtTop()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(1.0);
        }
    }

    /**
     * Moves the lift downward unless the lift is already at the bottom
     */
    
    public void goDown() {
        if (isAtBottom()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(-1.0);
        }
    }

    /**
     * Manually controls the lift using a specified speed
     * @param speed The speed that the lift is moved at
     */

    public void manualControl(double speed) {
        if (Math.abs(speed) < 0.1) {
            stop();
        } else if (speed < 0 && isAtBottom()) {
            stop();
        } else {
            setBrake(false);
            liftMotor.set(speed);
        }
    }

    /**
     * Stops the lift
     */

    public void stop() {
        liftMotor.set(0.0);
        setBrake(true);
    }

    /**
     * Gets whether the lift is at the bottom
     * 
     * @return whether the lift is at the bottom
     */

    public boolean isAtBottom() {
        return limitSwitch.get() && !overridden;
    }
    
    public boolean isAboveRecyclingBinHeight() {
        return getLiftEncoderDistance() >= LIFT_ENCODER_RECYCLING_BIN_HEIGHT;
    }

    /**
     * Gets whether the lift is at the top
     * 
     * @return whether the lift is at the top
     */

    public boolean isAtTop() {
        return getLiftEncoderDistance() >= LIFT_ENCODER_MAX_HEIGHT && !overridden;
    }

    /**
     * Resets the encoder if the lift is at the bottom
     */

    public void checkForReset() {
        if (isAtBottom()) {
            liftEncoder.reset();
        }
    }

    public double getLiftEncoderDistance() {
        return liftEncoder.getDistance();
    }

    /**
     * Sets the override for the limit switch for the 
     * @param on Whether or not the limit switch should be overridden.
     */

    public void setOverridden(boolean on) {
        overridden = on;
    }

    public boolean getOverridden() {
        return overridden;
    }
}
