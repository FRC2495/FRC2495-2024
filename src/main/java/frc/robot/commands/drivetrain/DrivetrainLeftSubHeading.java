
package frc.robot.commands.drivetrain;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.SwerveDrivetrain;

/**
 * Switches heading to opposite.
 */
public class DrivetrainLeftSubHeading extends InstantCommand {

	private SwerveDrivetrain drivetrain;

	public DrivetrainLeftSubHeading(SwerveDrivetrain drivetrain) {
		this.drivetrain = drivetrain;
		addRequirements(drivetrain);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("DrivetrainLeftSubHeading: initialize");

		Optional<Alliance> alliance = DriverStation.getAlliance();
		Alliance allianceColor = alliance.isPresent() ? alliance.get() : Alliance.Blue;

		if (allianceColor == Alliance.Blue)
		{
			drivetrain.blueLeftSubHeading();
		}
		else
		{
			drivetrain.redLeftSubHeading();
		}
	}

}
