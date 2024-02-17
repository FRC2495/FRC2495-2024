
package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Roller;

/**
 *
 */
public class RollerRollLowRpm extends Command {

	private Roller roller;

	public RollerRollLowRpm(Roller roller) {
		this.roller = roller;
		addRequirements(roller);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RollerRollLowRpm: initialize");
		roller.rollLowRpm();
	}

}
