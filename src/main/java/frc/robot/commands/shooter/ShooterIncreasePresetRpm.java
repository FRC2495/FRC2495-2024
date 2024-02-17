package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Shooter;

public class ShooterIncreasePresetRpm extends InstantCommand {

	private Shooter shooter;

	public ShooterIncreasePresetRpm(Shooter shooter) {
		this.shooter = shooter;
		addRequirements(shooter);
	}

	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		//System.out.println("ShooterIncreasePresetRpm: initialize");
		shooter.increasePresetRpm();
	}
}
