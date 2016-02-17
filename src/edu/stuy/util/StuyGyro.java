package edu.stuy.util;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;

public class StuyGyro extends AnalogGyro {

    private LinkedList<Double> gyroMeasurements;
    private Timer updateMeasurements;

    private final static int GYRO_MEASUREMENTS_SAMPLE_SIZE = 5;
    private final static int GYRO_UPDATE_PERIOD_MILLISECONDS = 10;

    public StuyGyro(AnalogInput channel) {
        super(channel);
        gyroMeasurements = new LinkedList<Double>();
        startGyroUpdateThread();
    }

    public StuyGyro(int channel) {
        super(channel);
        gyroMeasurements = new LinkedList<Double>();
        startGyroUpdateThread();
    }

    public void startGyroUpdateThread() {
        stopGyroUpdateThread();
        updateMeasurements = new Timer();
        updateMeasurements.schedule(new TimerTask() {
            public void run() {
                synchronized (StuyGyro.this) {
                    gyroMeasurements.add(getInstantaneousGyroAngleInDegrees());
                    if (gyroMeasurements.size() > GYRO_MEASUREMENTS_SAMPLE_SIZE) {
                        gyroMeasurements.removeFirst();
                    }
                }
            }
        }, 0, GYRO_UPDATE_PERIOD_MILLISECONDS);
    }

    public void stopGyroUpdateThread() {
        if (updateMeasurements != null) {
            updateMeasurements.cancel();
        }
    }

    public void resetGyroMeasurements() {
        gyroMeasurements.clear();
    }

    public double getInstantaneousGyroAngleInDegrees() {
        return getAngle();
    }

    public double getInstantaneousGyroAngleInRadians() {
        return Math.toRadians(getAngle());
    }

    public double getAveragedGyroAngle() {
        if (gyroMeasurements.isEmpty()) {
            return getInstantaneousGyroAngleInDegrees();
        }
        double min = gyroMeasurements.get(0);
        double max = min;
        double sum = min;
        synchronized (this) {
            for (int i = 1; i < gyroMeasurements.size(); i++) {
                double measure = gyroMeasurements.get(i);
                sum += measure;
                if (measure < min) {
                    min = measure;
                } else if (measure > max) {
                    max = measure;
                }
            }
            if (gyroMeasurements.size() >= 3) {
                return (sum - min - max) / (gyroMeasurements.size() - 2);
            } else {
                return sum / gyroMeasurements.size();
            }
        }
    }
}
