package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterStop extends InstantCommand {

	private Shooter shooter;


	public ShooterStop(Shooter shooter_in) {

		this.shooter = shooter_in;

		addRequirements(
			shooter);

	}

	// Called once when this command runs
	@Override
	public void initialize() {
		//System.out.println("ShooterStop: initialize");
		shooter.stop();
	}

}
