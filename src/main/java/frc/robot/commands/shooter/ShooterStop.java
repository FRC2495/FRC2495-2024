
package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.SimpleShooter;

/**
 *
 */
public class ShooterStop extends InstantCommand {

	private SimpleShooter shooter;

	public ShooterStop(SimpleShooter shooter) {
		this.shooter = shooter;
		addRequirements(shooter);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShooterShop: initialize");
		shooter.stop();
	
	}

}
