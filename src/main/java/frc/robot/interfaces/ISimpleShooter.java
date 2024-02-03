package frc.robot.interfaces;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ISimpleShooter extends Subsystem {

	public void take();
	
	//public void release();

	public void shoot();
	
	public void stop();
		
	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput);
	
	public boolean isTaking();
	
	//public boolean isReleasing();

	public boolean isShooting();
	
	// for debug purpose only
	public void joystickControl(Joystick joystick);

}
