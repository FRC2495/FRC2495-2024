
package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.sensors.NoteSensor;
import frc.robot.subsystems.Roller;

/**
 *
 */
public class RollerRollLowRpmUntilNoteSensed extends Command {

	private Roller roller;
	private NoteSensor notesensor;
	private NoteSensor noteSensorTwo;

	public RollerRollLowRpmUntilNoteSensed(Roller roller, NoteSensor notesensor, NoteSensor noteSensorTwo) {
		this.roller = roller;
		this.notesensor = notesensor;
		this.noteSensorTwo = noteSensorTwo;
		addRequirements(roller);
	}


	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RollerRollLowRpmUntilNoteSensed: initialize");
		roller.rollLowRpm();
	}

	@Override
	public boolean isFinished() {
		return !notesensor.isEnergized();
		//return !notesensor.isEnergized() || !noteSensorTwo.isEnergized();
	}

	@Override
	public void end(boolean interupted) {
		System.out.println("RollerRollLowRpmUntilNoteSensed: end");
		roller.stop();

	}

}
