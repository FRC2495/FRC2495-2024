package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterShootCustom extends Command {

	private Shooter shooter;

	private double custom_rpm;

	public ShooterShootCustom(Shooter shooter_in, double custom_rpm_in) {

		this.shooter = shooter_in;
		custom_rpm = custom_rpm_in;

		addRequirements(
			shooter);
	}


	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		//System.out.println("ShooterShootCustom: initialize");
		shooter.shootCustom(custom_rpm);
	}

}

