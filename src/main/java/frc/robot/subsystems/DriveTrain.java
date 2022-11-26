package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Auto-executing driving subsystem
 * 
 */
public class DriveTrain extends SubsystemBase {

    private DifferentialDrive driveSystem = RobotMap.driveSystem;

    public DriveTrain() {
        // Naming sensors for live window
        addChild("Drive", driveSystem);
        driveSystem.setDeadband(0.1);

        Command justDrive = new RunCommand(() -> Robot.driveTrain.arcadeDrive(Robot.io.driverController), Robot.driveTrain);
        setDefaultCommand(justDrive);
    }

    public void stop() {
        driveSystem.stopMotor();
    }

    /**
     * Explicit drive
     * 
     * @param left  - power of left side: -1 to 1 (if not inside this range it will be clamped to it)
     * @param right - power of right side: -1 to 1 (if not inside this range it will be clamped to it)
     */
    public void tankDrive(XboxController driverController) {
        double left = driverController.getY(Hand.kLeft);
        double right = driverController.getY(Hand.kLeft);
        driveSystem.tankDrive(left, right);
    }

    /**
     * Implicit drive using joystick
     * 
     * @param joy - driving joystick
     */
    public void arcadeDrive(XboxController driverController) {

        /** If enabled, right trigger moves the robot forwards and left trigger moves the robot backwards. */
        boolean octaneDrive = true; // marston was here
        
        double forwardPower;
        double turnMod;

        if (octaneDrive) {
            forwardPower = -(driverController.getTriggerAxis(Hand.kRight) - driverController.getTriggerAxis(Hand.kLeft));
            turnMod = driverController.getX(Hand.kRight);
        } else {
            forwardPower = -driverController.getY(Hand.kLeft);
            turnMod = driverController.getX(Hand.kRight);
        }

        if (!driverController.getBumper(Hand.kRight)) forwardPower *= 0.75;

        if (driverController.getBumper(Hand.kLeft)) turnMod *= 0.5;

        driveSystem.arcadeDrive(forwardPower, turnMod);
    }

    /**
     * Log important data
     */
    public void log() {
        
    }
}
