package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.interfaces.*;
//import frc.robot.RobotContainer;
//import frc.robot.Ports;


/**
 * The {@code Roller} class contains fields and methods pertaining to the function of the roller.
 */
public class Roller extends SubsystemBase implements IRoller{
	public static final int LENGTH_OF_SHORT_DISTANCE_TICKS = 20000; 

	static final double MAX_PCT_OUTPUT = 1.0;
	static final double ALMOST_MAX_PCT_OUTPUT = 1.0;
	static final double HALF_PCT_OUTPUT = 0.5;
	static final double REDUCED_PCT_OUTPUT = 0.6; //0.8;
	static final double REDUCED_PCT_OUTPUT_SHORT_DISTANCE = 0.4;
	static final double SUPER_REDUCED_PCT_OUTPUT = 0.2; // 0.3
	
	//todo fix
	
	static final int WAIT_MS = 1000;
	static final int TIMEOUT_MS = 5000;

	static final int TALON_TIMEOUT_MS = 20;

	static final int ROLL_DISTANCE_INCHES = 13;
	static final int RELEASE_DISTANCE_INCHES = 17;
	static final int SHOOT_DISTANCE_INCHES = 17;
	
	WPI_TalonSRX roller;
	BaseMotorController roller_follower; 
		
	boolean isMoving;
	boolean isRolling;
	boolean isReleasing;
	boolean isShooting;

	double tac;

	private int onTargetCount; // counter indicating how many times/iterations we were on target 

	// close loop settings
	static final int PRIMARY_PID_LOOP = 0;

	static final int SLOT_0 = 0;

	static final double ROLL_PROPORTIONAL_GAIN = 0.25;
	static final double ROLL_PROPORTIONAL_GAIN_SHORT_DISTANCE = 0.1;
	static final double ROLL_INTEGRAL_GAIN = 0.001;
	static final double ROLL_INTEGRAL_GAIN_SHORT_DISTANCE = 0.0001;
	static final double ROLL_DERIVATIVE_GAIN = 20.0;
	static final double ROLL_DERIVATIVE_GAIN_SHORT_DISTANCE = 2.0;
	static final double ROLL_FEED_FORWARD = 1023.0/30000.0; // 1023 = Talon SRX/FX full motor output, max measured velocity ~ 30000 native units per 100ms

	static final double TICK_THRESH = 2048;
	public static final double TICK_PER_100MS_THRESH = 1;

	private final static int MOVE_ON_TARGET_MINIMUM_COUNT= 20; // number of times/iterations we need to be on target to really be on target

	static final double ROLL_HIGH_RPM = 3200.0;
	static final double ROLL_LOW_RPM = 3000.0;

	private double presetRpm = ROLL_HIGH_RPM; // preset rpm

	static final double PRESET_DELTA_RPM = 100.0; // by what we increase/decrease by default

	static final int CTRE_MAGNETIC_ENCODER_SENSOR_TICKS_PER_ROTATION = 4096; // units per rotation
	
		
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

		// Sensors for motor controllers provide feedback about the position, velocity, and acceleration
		// of the system using that motor controller.
		// Note: With Phoenix framework, position units are in the natural units of the sensor.
		// This ensures the best resolution possible when performing closed-loops in firmware.
		// CTRE Magnetic Encoder (relative/quadrature) =  4096 units per rotation
		// FX Integrated Sensor = 2048 units per rotation
		roller.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);

		// Sensor phase is the term used to explain sensor direction.
		// In order for limit switches and closed-loop features to function properly the sensor and motor has to be in-phase.
		// This means that the sensor position must move in a positive direction as the motor controller drives positive output.  
		roller.setSensorPhase(true); // TODO flip if needed
		
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

	// This method should be called to assess the progress of a move
	public boolean tripleCheckMove() {
		if (isMoving) {
			
			double error = roller.getClosedLoopError(PRIMARY_PID_LOOP);
			
			boolean isOnTarget = (Math.abs(error) < TICK_THRESH);
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCount++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCount > 0) { // even though we were on target at least once during a previous iteration
					onTargetCount = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (roller moving).");
				} else {
					// we are definitely moving
				}
			}
			
			if (onTargetCount > MOVE_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isMoving = false;
			}
			
			if (!isMoving) {
				System.out.println("You have reached the target (roller moving).");
				//drawer.set(ControlMode.PercentOutput,0);
				if (isReleasing)	{
					stop(); // adjust if needed
				} else {
					stop(); // adjust if needed
				}
			}
		}
		return isMoving; 
	}

	public void roll() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, -REDUCED_PCT_OUTPUT);
		
		isRolling = true;
		isReleasing = false;
		isShooting = false;

		isMoving = false;
	}

	public void rollLowRpm() {

		setPIDParameters();
		setNominalAndPeakOutputs(MAX_PCT_OUTPUT); //this has a global impact, so we reset in stop()

		double targetVelocity_UnitsPer100ms = -ROLL_LOW_RPM * CTRE_MAGNETIC_ENCODER_SENSOR_TICKS_PER_ROTATION / 600; // 1 revolution = TICKS_PER_ROTATION ticks, 1 min = 600 * 100 ms

		roller.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
		
		isRolling = true;
		isReleasing = false;
		isShooting = false;

		isMoving = false;
	}
	
	public void release() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, SUPER_REDUCED_PCT_OUTPUT);
		
		isReleasing = true;
		isRolling = false;
		isShooting = false;

		isMoving = false;
	}

	public void releaseShortDistance() {
		stop(); // in case we were still doing something
		
		resetEncoder(); // set new virtual zero
		resetEncoder(); // set new virtual zero TWICE
		
		setPIDParametersShortDistance();
		System.out.println("Releasing");
		setNominalAndPeakOutputs(REDUCED_PCT_OUTPUT_SHORT_DISTANCE);

		tac = +LENGTH_OF_SHORT_DISTANCE_TICKS;
		
		roller.set(ControlMode.Position,tac);
		
		isReleasing = true;
		isRolling = false;
		isShooting = false;

		isMoving = true;
		onTargetCount = 0;
	}

	public void shoot() {
		//SwitchedCamera.setUsbCamera(Ports.UsbCamera.GRASPER_CAMERA);

		roller.set(ControlMode.PercentOutput, -MAX_PCT_OUTPUT);
		
		isRolling = false;
		isReleasing = false;
		isShooting = true;

		isMoving = false;
	}
	
	public double getEncoderPosition() {
		return roller.getSelectedSensorPosition(PRIMARY_PID_LOOP);
	}

	public double getPresetRpm()
	{
		return presetRpm;
	}
	
	public void stop() {
		roller.set(ControlMode.PercentOutput, 0);
		
		isRolling = false;
		isReleasing = false;
		isShooting = false;

		isMoving = false;

		setNominalAndPeakOutputs(MAX_PCT_OUTPUT); // we undo what me might have changed
	}

	public void setPIDParameters()
	{
		roller.configAllowableClosedloopError(SLOT_0, TICK_PER_100MS_THRESH, TALON_TIMEOUT_MS);
		
		// P is the proportional gain. It modifies the closed-loop output by a proportion (the gain value)
		// of the closed-loop error.
		// P gain is specified in output unit per error unit.
		// When tuning P, it's useful to estimate your starting value.
		// If you want your mechanism to drive 50% output when the error is 4096 (one rotation when using CTRE Mag Encoder),
		// then the calculated Proportional Gain would be (0.50 X 1023) / 4096 = ~0.125.
		
		// I is the integral gain. It modifies the closed-loop output according to the integral error
		// (summation of the closed-loop error each iteration).
		// I gain is specified in output units per integrated error.
		// If your mechanism never quite reaches your target and using integral gain is viable,
		// start with 1/100th of the Proportional Gain.
		
		// D is the derivative gain. It modifies the closed-loop output according to the derivative error
		// (change in closed-loop error each iteration).
		// D gain is specified in output units per derivative error.
		// If your mechanism accelerates too abruptly, Derivative Gain can be used to smooth the motion.
		// Typically start with 10x to 100x of your current Proportional Gain.

		// Feed-Forward is typically used in velocity and motion profile/magic closed-loop modes.
		// F gain is multiplied directly by the set point passed into the programming API.
		// The result of this multiplication is in motor output units [-1023, 1023]. This allows the robot to feed-forward using the target set-point.
		// In order to calculate feed-forward, you will need to measure your motor's velocity at a specified percent output
		// (preferably an output close to the intended operating range).
			
		roller.config_kP(SLOT_0, ROLL_PROPORTIONAL_GAIN, TALON_TIMEOUT_MS);
		roller.config_kI(SLOT_0, ROLL_INTEGRAL_GAIN, TALON_TIMEOUT_MS);
		roller.config_kD(SLOT_0, ROLL_DERIVATIVE_GAIN, TALON_TIMEOUT_MS);	
		roller.config_kF(SLOT_0, ROLL_FEED_FORWARD, TALON_TIMEOUT_MS);
	}		


	public void setPIDParametersShortDistance()
	{
		roller.configAllowableClosedloopError(SLOT_0, TICK_PER_100MS_THRESH, TALON_TIMEOUT_MS);
		
		// P is the proportional gain. It modifies the closed-loop output by a proportion (the gain value)
		// of the closed-loop error.
		// P gain is specified in output unit per error unit.
		// When tuning P, it's useful to estimate your starting value.
		// If you want your mechanism to drive 50% output when the error is 4096 (one rotation when using CTRE Mag Encoder),
		// then the calculated Proportional Gain would be (0.50 X 1023) / 4096 = ~0.125.
		
		// I is the integral gain. It modifies the closed-loop output according to the integral error
		// (summation of the closed-loop error each iteration).
		// I gain is specified in output units per integrated error.
		// If your mechanism never quite reaches your target and using integral gain is viable,
		// start with 1/100th of the Proportional Gain.
		
		// D is the derivative gain. It modifies the closed-loop output according to the derivative error
		// (change in closed-loop error each iteration).
		// D gain is specified in output units per derivative error.
		// If your mechanism accelerates too abruptly, Derivative Gain can be used to smooth the motion.
		// Typically start with 10x to 100x of your current Proportional Gain.

		// Feed-Forward is typically used in velocity and motion profile/magic closed-loop modes.
		// F gain is multiplied directly by the set point passed into the programming API.
		// The result of this multiplication is in motor output units [-1023, 1023]. This allows the robot to feed-forward using the target set-point.
		// In order to calculate feed-forward, you will need to measure your motor's velocity at a specified percent output
		// (preferably an output close to the intended operating range).
			
		roller.config_kP(SLOT_0, ROLL_PROPORTIONAL_GAIN_SHORT_DISTANCE, TALON_TIMEOUT_MS);
		roller.config_kI(SLOT_0, ROLL_INTEGRAL_GAIN_SHORT_DISTANCE, TALON_TIMEOUT_MS);
		roller.config_kD(SLOT_0, ROLL_DERIVATIVE_GAIN_SHORT_DISTANCE, TALON_TIMEOUT_MS);	
		roller.config_kF(SLOT_0, ROLL_FEED_FORWARD, TALON_TIMEOUT_MS);
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

	public boolean isMoving(){
		return isMoving;
	}

	// for debug purpose only
	public void joystickControl(Joystick joystick)
	{
		roller.set(ControlMode.PercentOutput, -joystick.getY());
	}

	// in units per 100 ms
	public int getEncoderVelocity() {
		return (int) (roller.getSelectedSensorVelocity(PRIMARY_PID_LOOP));
	}

	// in revolutions per minute
	public int getRpm() {
		return (int) (roller.getSelectedSensorVelocity(PRIMARY_PID_LOOP)*600/CTRE_MAGNETIC_ENCODER_SENSOR_TICKS_PER_ROTATION);  // 1 min = 600 * 100 ms, 1 revolution = TICKS_PER_ROTATION ticks 
	}

	public double getTarget() {
		return tac;
	}	

	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoder() {
		roller.set(ControlMode.PercentOutput,0); // we stop AND MAKE SURE WE DO NOT MOVE WHEN SETTING POSITION
		roller.setSelectedSensorPosition(0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS); // we mark the virtual zero
	}
}










