package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Shooter;

public class ShooterDecreasePresetRpm extends InstantCommand {

	private Shooter shooter;

	public ShooterDecreasePresetRpm(Shooter shooter) {
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
		//System.out.println("ShooterDecreasePresetRpm: initialize");
		shooter.decreasePresetRpm();
	}
}
