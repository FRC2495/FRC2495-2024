package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.interfaces.*;
//import frc.robot.RobotContainer;
//import frc.robot.Ports;


/**
 * The {@code Roller} class contains fields and methods pertaining to the function of the roller.
 */
public class Roller extends SubsystemBase implements IRoller{
	/**
	 * 
	 */
	static final double MAX_PCT_OUTPUT = 1.0;
	static final double ALMOST_MAX_PCT_OUTPUT = 1.0;
	static final double HALF_PCT_OUTPUT = 0.5;
	static final double REDUCED_PCT_OUTPUT = 0.85;
	//todo fix
	
	static final int WAIT_MS = 1000;
	static final int TIMEOUT_MS = 5000;

	static final int TALON_TIMEOUT_MS = 20;

	static final int ROLL_DISTANCE_INCHES = 13;
	static final int RELEASE_DISTANCE_INCHES = 17;
	static final int SHOOT_DISTANCE_INCHES = 17;
	
	WPI_TalonSRX roller;
	BaseMotorController roller_follower; 
		
	boolean isRolling;
	boolean isReleasing;
	boolean isShooting;
	
		
	public Roller(WPI_TalonSRX roller_in, BaseMotorController roller_follower_in) {
		
		roller = roller_in;
		roller_follower = roller_follower_in; 

		roller.configFactoryDefault();
		roller_follower.configFactoryDefault();
		
		// Mode of operation during Neutral output may be set by using the setNeutralMode() function.
		// As of right now, there are two options when setting the neutral mode of a motor controller,
		// brake and coast.
		roller.setNeutralMode(NeutralMode.Coast);
		roller_follower.setNeutralMode(NeutralMode.Coast);
		
		// Motor controller output direction can be set by calling the setInverted() function as seen below.
		// Note: Regardless of invert value, the LEDs will blink green when positive output is requested (by robot code or firmware closed loop).
		// Only the motor leads are inverted. This feature ensures that sensor phase and limit switches will properly match the LED pattern
		// (when LEDs are green => forward limit switch and soft limits are being checked).
		roller.setInverted(false);
		roller_follower.setInverted(false);  // TODO comment out if switching to Talon FX

		// Both the Talon SRX and Victor SPX have a follower feature that allows the motor controllers to mimic another motor controller's output.
		// Users will still need to set the motor controller's direction, and neutral mode.
		// The method follow() allows users to create a motor controller follower of not only the same model, but also other models
		// , talon to talon, victor to victor, talon to victor, and victor to talon.
		roller_follower.follow(roller);

		// Motor controllers that are followers can set Status 1 and Status 2 to 255ms(max) using setStatusFramePeriod.
		// The Follower relies on the master status frame allowing its status frame to be slowed without affecting performance.
		// This is a useful optimization to manage CAN bus utilization.
		roller_follower.setStatusFramePeriod(StatusFrame.Status_1_General, 255, TALON_TIMEOUT_MS);
		roller_follower.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 255, TALON_TIMEOUT_MS);
		
		// set peak output to max in case if had been reduced previously
		setNominalAndPeakOutputs(MAX_PCT_OUTPUT);
	}
	
	/*@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		setDefaultCommand(new RollerStop());
	}*/

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}

	public void roll() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, REDUCED_PCT_OUTPUT);
		
		isRolling = true;
		isReleasing = false;
		isShooting = false;
	}
	
	public void release() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, -ALMOST_MAX_PCT_OUTPUT);
		
		isReleasing = true;
		isRolling = false;
		isShooting = false;
	}

	public void shoot() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, -MAX_PCT_OUTPUT);
		
		isRolling = true;
		isReleasing = false;
		isShooting = false;
	}
	
	
	public void stop() {
		roller.set(ControlMode.PercentOutput, 0);
		
		isRolling = false;
		isReleasing = false;
		isShooting = false;
	}
	
		
	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput)
	{
		roller.configPeakOutputForward(peakOutput, TALON_TIMEOUT_MS);
		roller.configPeakOutputReverse(-peakOutput, TALON_TIMEOUT_MS);

		roller.configNominalOutputForward(0, TALON_TIMEOUT_MS);
		roller.configNominalOutputReverse(0, TALON_TIMEOUT_MS);
	}
	
	public boolean isRolling(){
		return isRolling;
	}
	
	public boolean isReleasing(){
		return isReleasing;
	}

	public boolean isShooting(){
		return isShooting;
	}

	// for debug purpose only
	public void joystickControl(Joystick joystick)
	{
		roller.set(ControlMode.PercentOutput, joystick.getY());
	}

	
}










