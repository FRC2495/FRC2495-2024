
package frc.robot.commands.simpleshooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.SimpleShooter;

/**
 *
 */
public class SimpleShooterStop extends InstantCommand {

	private SimpleShooter shooter;

	public SimpleShooterStop(SimpleShooter shooter) {
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
