
package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Roller;

/**
 *
 */
public class RollerReleaseShortDistance extends Command {

	private Roller roller;

	public RollerReleaseShortDistance(Roller roller) {
		this.roller = roller;
		addRequirements(roller);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RollerReleaseShortDistance: initialize");
		roller.releaseShortDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !roller.tripleCheckMove();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("RollerReleaseShortDistance: end");
		roller.stop(); // adjust if needed
	}
}
