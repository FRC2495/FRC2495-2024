
package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.sensors.NoteSensor;
import frc.robot.subsystems.Roller;

/**
 *
 */
public class RollerRollUntilNoteSensed extends Command {

	private Roller roller;
	private NoteSensor notesensor;

	public RollerRollUntilNoteSensed(Roller roller, NoteSensor notesensor) {
		this.roller = roller;
		this.notesensor = notesensor;
		addRequirements(roller);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RollerRollUntilNoteSensed: initialize");
		roller.roll();
	}

	@Override
	public boolean isFinished() {
		return !notesensor.isEnergized();
	}

	@Override
	public void end(boolean interupted) {
		System.out.println("RollerRollUntilNoteSensed: end");
		roller.stop();

	}

}
