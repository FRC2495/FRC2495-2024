
package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterTake extends Command {

	private Shooter shooter;

	public ShooterTake(Shooter shooter) {
		this.shooter = shooter;
		addRequirements(shooter);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShooterTake: initialize");
		shooter.take();
	}

}
