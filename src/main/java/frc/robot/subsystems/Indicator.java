/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.interfaces.ICamera;
import frc.robot.Ports;
//import frc.robot.commands.indicator.*;


// see https://docs.wpilib.org/en/latest/docs/software/actuators/addressable-leds.html

/**
 * The {@code Indicator} class contains fields and methods pertaining to the function of the indicator.
 */
public class Indicator extends SubsystemBase {
	private AddressableLED led;
	private AddressableLEDBuffer ledBuffer;
	private ICamera apriltag_camera;
	private ICamera object_detection_camera;

	// Store what the last hue of the first pixel is
	private int rainbowFirstPixelHue;
	
	public Indicator(ICamera apriltag_camera_in, ICamera object_detection_camera_in) 
	{
		apriltag_camera = apriltag_camera_in;
		object_detection_camera = object_detection_camera_in;
		
		led = new AddressableLED(Ports.PWM.LED_STRIP);

		// Reuse buffer
		// Default to a length of 60, start empty output
		// Length is expensive to set, so only set it once, then just update data
		ledBuffer = new AddressableLEDBuffer(60);

		led.setLength(ledBuffer.getLength());

		// Set the data
		led.setData(ledBuffer);

		led.start();
	}

	/*@Override
	public void initDefaultCommand() {  
		// Set the default command for a subsystem here.
		setDefaultCommand(new IndicatorIndicateUsingCamera());
	}*/

	@Override
	public void periodic() {
		// Put code here to be run every loop
	}

	public void updateRainbow()
	{
		// For every pixel
		for (var i = 0; i < ledBuffer.getLength(); i++) {

			// Calculate the hue - hue is easier for rainbows because the color
			// shape is a circle so only one value needs to precess
			final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;

			// Set the value
			ledBuffer.setHSV(i, hue, 255, 128);
		}

		// Increase by to make the rainbow "move"
		rainbowFirstPixelHue += 3;

		// Check bounds
		rainbowFirstPixelHue %= 180; 

		// Set the LEDs
		led.setData(ledBuffer);
	}

	public void setHue(int hue)
	{
		// For every pixel
		for (var i = 0; i < ledBuffer.getLength(); i++) {

			// Set the value
			ledBuffer.setHSV(i, hue, 255, 128);
		}

		// Set the LEDs
		led.setData(ledBuffer);
	}

	public void setRed()
	{
		setHue(0);
	}

	public void setYellow()
	{
		setHue(20/*45*/);
	}

	public void setGreen()
	{
		setHue(60);
	}

	public void setBlue()
	{
		setHue(120);
	}

	public void setPurple()
	{
		setHue(160);
	}

	public void updateFromCamera() {

		if (apriltag_camera == null) return;

		double distance = apriltag_camera.getDistanceToTarget();  // will return 0.0 by convention if no target acquired
		double angle = apriltag_camera.getAngleToTurnToTarget(); // angle call not atomic with distance call, but good enough for this use case
		
		if (distance > 0.0) { // if we saw something
			if (Math.abs(angle) < 5) { // displays green if in target
				setGreen();
			}
			else if (Math.abs(angle) < 15) { // displays yellow if close to target
				setYellow();
			}
			else { // displays red if far from target 
				setRed();
			}
		} else { // no target, so arbitrarily displays blue 
			//setBlue();


			///////////
			if (object_detection_camera == null) return;

					double distance_note = object_detection_camera.getDistanceToTarget();  // will return 0.0 by convention if no target acquired
					//double angle_note = object_detection_camera.getAngleToTurnToTarget(); // angle call not atomic with distance call, but good enough for this use case
					
					if (distance_note > 0.0) { // if we saw something
						/*if (Math.abs(angle_note) < 5) { // displays green if in target
							setGreen();
						}
						else if (Math.abs(angle_note) < 15) { // displays yellow if close to target
							setYellow();
						}
						else { // displays red if far from target 
							setRed();
						}*/
						setPurple();
					} else { // no target, so arbitrarily displays blue 
						setBlue();
					}
			//////////
			
		}

	}
}
