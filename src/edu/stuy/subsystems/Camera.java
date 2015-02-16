package edu.stuy.subsystems;

import static edu.stuy.RobotMap.*;
import static edu.stuy.util.StuyCV.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import edu.stuy.util.StuyCV;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private VideoCapture cap;
    private Mat frame;

    public Camera() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        cap = new VideoCapture(0);
    }

    public void loadFrame() {
        StuyCV.getFromFeed(cap , frame);
    }

    public boolean checkTote() {
        return true; //Check if there is a legit toteg
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

