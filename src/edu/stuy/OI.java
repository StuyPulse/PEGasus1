package edu.stuy;

import static edu.stuy.RobotMap.DRIVER_PAD_PORT;
import static edu.stuy.RobotMap.OPERATOR_PAD_PORT;
import edu.stuy.commands.AcquirerAcquireCommand;
import edu.stuy.commands.AcquirerReleaseCommand;
import edu.stuy.commands.ArmsNarrowCommand;
import edu.stuy.commands.ArmsReleaseCommand;
import edu.stuy.commands.ArmsWideCommand;
import edu.stuy.util.Gamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public Gamepad driverPad;
    public Gamepad operatorPad;

    public OI() {
        driverPad = new Gamepad(DRIVER_PAD_PORT);
        operatorPad = new Gamepad(OPERATOR_PAD_PORT);
        // Lift is controlled by LiftControlCommand

        operatorPad.getLeftBumper().whileHeld(new AcquirerReleaseCommand());
        operatorPad.getRightBumper().whileHeld(new AcquirerReleaseCommand());
        operatorPad.getLeftTrigger().whileHeld(new AcquirerAcquireCommand());
        operatorPad.getRightTrigger().whileHeld(new AcquirerAcquireCommand());

        operatorPad.getLeftButton().whenPressed(new ArmsNarrowCommand());
        operatorPad.getBottomButton().whenPressed(new ArmsWideCommand());
        operatorPad.getRightButton().whenPressed(new ArmsReleaseCommand());
    }
}