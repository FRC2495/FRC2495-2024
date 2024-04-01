/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.sensors.NoteSensor;
import frc.robot.subsystems.Roller;

/**
 * Add your docs here.
 */
public class RollerTimedRollUntilNoteSensed extends WaitCommand {

	private Roller roller;
	private NoteSensor notesensor;
	private NoteSensor noteSensorTwo;

	/**
	 * Add your docs here.
	 */
	public RollerTimedRollUntilNoteSensed(Roller roller, double timeout, NoteSensor notesensor, NoteSensor noteSensorTwo) {
		super(timeout);
		this.roller = roller;
		this.notesensor = notesensor;
		this.noteSensorTwo = noteSensorTwo;
		addRequirements(roller);
		
		
		// ControllerBase is not a real subsystem, so no need to reserve it
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RollerTimedRollUntilNoteSensed: initialize");
		super.initialize();
		roller.roll();

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	@Override
	public boolean isFinished() {
		return !notesensor.isEnergized() || !noteSensorTwo.isEnergized();
	}

	// Called once after timeout
	@Override
	public void end(boolean interrupted) {
		System.out.println("RollerTimedRollUntilNoteSensed: end");
		
		super.end(interrupted);
	}
}
