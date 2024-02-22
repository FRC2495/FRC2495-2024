package frc.robot.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ICamera extends Subsystem {

	public double getDistanceToTarget();
	
	public double getAngleToTurnToTarget();
}
