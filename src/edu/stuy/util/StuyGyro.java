package edu.stuy.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;

public class StuyGyro extends Gyro {
    
    private Vector<Double> gyroMeasurements;
    private Timer updateMeasurements;
    
    private final int GYRO_MEASUREMENT_SIZE = 5;
    private final int GYRO_UPDATE_PERIOD = 10;
    
    public StuyGyro(AnalogInput channel) {
        super(channel);
        gyroMeasurements = new Vector<Double>();
        startGyroUpdateThread();
    }
    
    public void startGyroUpdateThread() {
        stopGyroUpdateThread();
        updateMeasurements = new Timer();
        updateMeasurements.schedule(new TimerTask() {
            public void run() {
                synchronized (StuyGyro.this) {
                    gyroMeasurements.addElement(getInstantaneousGyroAngleInDegrees());
                    if (gyroMeasurements.size() > GYRO_MEASUREMENT_SIZE) {
                        gyroMeasurements.removeElementAt(0);
                    }
                }
            }
        }, 0, GYRO_UPDATE_PERIOD);
    }
    
    public void stopGyroUpdateThread() {
        if (updateMeasurements != null) {
            updateMeasurements.cancel();
        }
    }
    
    public void resetGyroMeasurements() {
        gyroMeasurements.removeAllElements();
    }
    
    public double getInstantaneousGyroAngleInDegrees() {
        return getAngle();
    }
    
    public double getInstantaneousGyroAngleInRadians() {
        return getAngle() * Math.PI / 180.0;
    }
    
    public double getAveragedGyroAngle() {
        if (gyroMeasurements.isEmpty()) {
            return 0;
        }
        double sum = 0;
        double min = gyroMeasurements.elementAt(0);
        double max = min;
        synchronized (this) {
            for (int i = 0; i < gyroMeasurements.size(); i++) {
                double measure = gyroMeasurements.elementAt(i);
                sum += measure;
                if (measure < min) {
                    min = measure;
                }
                else if (measure > max) {
                    max = measure;
                }
            }
            if (gyroMeasurements.size() >= 3) {
                return (sum - min - max) / (gyroMeasurements.size() - 2);
            }
            else {
                return sum / gyroMeasurements.size();
            }
        }
    }
    
    
    
}
