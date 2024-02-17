package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterShootLow extends Command {

	private Shooter shooter;


	public ShooterShootLow(Shooter shooter_in) {

		this.shooter = shooter_in;

		addRequirements(
			shooter);

	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		//System.out.println("ShooterShootLow: initialize");
		shooter.shootLow();
	}
}
