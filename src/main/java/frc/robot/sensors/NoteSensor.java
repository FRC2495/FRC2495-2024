// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The {@code NoteSensor} class contains fields and methods pertaining to the function of the note sensor.
 */
public class NoteSensor
{
	private DigitalInput digitalInput;

	public NoteSensor(int port) {
		this.digitalInput = new DigitalInput(port);
	}

	/**
	 * Returns the state of the note sensor.
	 *
	 * @return the current state of the note sensor.
	 */
	public boolean isEnergized() {
		return digitalInput.get();
	}
}