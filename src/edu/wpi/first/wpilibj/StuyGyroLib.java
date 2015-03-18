/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

/**
 * Use a rate gyro to return the robots heading relative to a starting position.
 * The Gyro class tracks the robots heading based on the starting position. As
 * the robot rotates the new heading is computed by integrating the rate of
 * rotation returned by the sensor. When the class is instantiated, it does a
 * short calibration routine where it samples the gyro while at rest to
 * determine the default offset. This is subtracted from each sample to
 * determine the heading.
 */
public class StuyGyroLib extends Gyro {

    public StuyGyroLib(AnalogInput channel) {
        super(channel);
    }

    public StuyGyroLib(int channel) {
        super(channel);
    }
    /**
     * Set the size of the neutral zone.  Any voltage from the gyro less than
     * this amount from the center is considered stationary.  Setting a
     * deadband will decrease the amount of drift when the gyro isn't rotating,
     * but will make it less accurate.
     *
     * @param volts The size of the deadband in volts
     */
    public void setDeadband(double volts) {
        super.setDeadband(volts);
    }
}
