package edu.stuy;

import static edu.stuy.RobotMap.DRIVER_PAD_PORT;
import static edu.stuy.RobotMap.OPERATOR_PAD_PORT;
import edu.stuy.commands.AcquirerLeftAcquireCommand;
import edu.stuy.commands.AcquirerLeftReleaseCommand;
import edu.stuy.commands.AcquirerRightAcquireCommand;
import edu.stuy.commands.AcquirerRightReleaseCommand;
import edu.stuy.commands.ArmsGetNarrowerCommand;
import edu.stuy.commands.ArmsGetWiderCommand;
import edu.stuy.commands.CanGrabberToggleCommand;
import edu.stuy.commands.DrivetrainTankDriveOverrideOffCommand;
import edu.stuy.commands.DrivetrainTankDriveOverrideOnCommand;
import edu.stuy.commands.LiftOverrideCommand;
import edu.stuy.commands.ToteKnockerExtendCommand;
import edu.stuy.commands.ToteKnockerRetractCommand;
import edu.stuy.util.Gamepad;

/**
 * OI stands for Operator Interface. The OI class maps specific buttons on
 * the gamepads or joysticks to specific commands to be executed on the robot.
 */

public class OI {
    public Gamepad driverPad;
    public Gamepad operatorPad;

    public OI() {
        driverPad = new Gamepad(DRIVER_PAD_PORT);
        operatorPad = new Gamepad(OPERATOR_PAD_PORT);

        // Lift is controlled by LiftControlCommand, which is the Lift's default command
        // Drivetrain is controlled by DrivetrainTankDriveCommand

        operatorPad.getLeftBumper().whileHeld(new AcquirerLeftReleaseCommand());
        operatorPad.getRightBumper().whileHeld(new AcquirerRightReleaseCommand());
        operatorPad.getLeftTrigger().whileHeld(new AcquirerLeftAcquireCommand());
        operatorPad.getRightTrigger().whileHeld(new AcquirerRightAcquireCommand());

        operatorPad.getLeftButton().whenPressed(new CanGrabberToggleCommand());
        operatorPad.getRightButton().whenPressed(new CanGrabberToggleCommand());
        operatorPad.getTopButton().whenPressed(new CanGrabberToggleCommand());
        operatorPad.getDPadUp().whenPressed(new CanGrabberToggleCommand());

        operatorPad.getDPadLeft().whenPressed(new ArmsGetWiderCommand());
        operatorPad.getDPadDown().whenPressed(new ArmsGetWiderCommand());
        operatorPad.getDPadRight().whenPressed(new ArmsGetNarrowerCommand());
        operatorPad.getBottomButton().whenPressed(new ArmsGetNarrowerCommand());

        operatorPad.getStartButton().whenPressed(new LiftOverrideCommand());

        driverPad.getLeftBumper().whileHeld(new ToteKnockerExtendCommand());
        driverPad.getRightBumper().whileHeld(new ToteKnockerExtendCommand());

        driverPad.getStartButton().whenPressed(new DrivetrainTankDriveOverrideOnCommand());
        driverPad.getSelectButton().whenPressed(new DrivetrainTankDriveOverrideOffCommand());
    }
}