package frc.robot.util;

/**
 * Using two double solenoids to allow floating of piston.
 * @author Marston Connell
 */
public class FloatableSolenoid{

    private DoubleSolenoidToggler one;
    private DoubleSolenoidToggler two;
    private boolean extended = false;

    public FloatableSolenoid(int portOne, int portTwo, int portThree, int portFour) {
        one = new DoubleSolenoidToggler(portOne, portTwo);
        two = new DoubleSolenoidToggler(portThree, portFour);
    }

    public void floatPiston(){
        one.retract();
        two.retract();
    }

    public void retractPiston() {
        two.retract();
        one.extend();
        extended = false;
    }

    public void extendPiston() {
        one.retract();
        two.extend();
        extended = true;
    }

    public void togglePiston() {
        if (extended) {
            retractPiston();
        } else {
            extendPiston();
        }
    }

    public boolean isExtended() {
        return extended;
    }

}
