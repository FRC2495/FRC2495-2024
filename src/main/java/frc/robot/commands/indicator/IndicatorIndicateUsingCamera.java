
package frc.robot.commands.indicator;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Indicator;

/**
 *
 */
public class IndicatorIndicateUsingCamera extends Command {

	private Indicator indicator;

	public IndicatorIndicateUsingCamera(Indicator indicator) {
		this.indicator = indicator;
		addRequirements(indicator);
	}

	// This instant command can run disabled
	/*@Override
	public boolean runsWhenDisabled() {
		return true;
	}*/

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("IndicatorIndicateUsingCamera: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		indicator.updateFromCamera();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false; // we are never finished
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("IndicatorIndicateUsingCamera: end");
		//indicator.stop();
	}
}
