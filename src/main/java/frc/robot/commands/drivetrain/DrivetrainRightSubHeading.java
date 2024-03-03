
package frc.robot.commands.drivetrain;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.SwerveDrivetrain;

/**
 * Switches heading to opposite.
 */
public class DrivetrainRightSubHeading extends InstantCommand {

	private SwerveDrivetrain drivetrain;

	public DrivetrainRightSubHeading(SwerveDrivetrain drivetrain) {
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
		System.out.println("DrivetrainRightSubHeading: initialize");

		Optional<Alliance> alliance = DriverStation.getAlliance();
		Alliance allianceColor = alliance.isPresent() ? alliance.get() : Alliance.Blue;

		if (allianceColor == Alliance.Blue)
		{
			drivetrain.blueRightSubHeading();
		}
		else
		{
			drivetrain.redRightSubHeading();
		}
	}

}
