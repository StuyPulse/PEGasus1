package edu.stuy.subsystems;

import edu.stuy.commands.ToteKnockerRetractCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static edu.stuy.RobotMap.*;

/**
 * ToteKnocker controls the tote knocking mechanism
 * Knocks over vertical totes
 * Can be used to dislodge RCs
 */

public class ToteKnocker extends Subsystem {

    Solenoid toteKnockerPiston;

    public ToteKnocker() {
        toteKnockerPiston = new Solenoid(PCM_2 , TOTE_KNOCKER_SOLENOID_ID);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ToteKnockerRetractCommand());
    }

    public void extend() {
        toteKnockerPiston.set(true);
    }

    public void retract() {
        toteKnockerPiston.set(false);
    }

}