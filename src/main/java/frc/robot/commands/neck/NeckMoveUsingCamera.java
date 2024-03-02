package frc.robot.commands.neck;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Neck;
import frc.robot.util.Magic;
import frc.robot.interfaces.ICamera;

/**
 *
 */
public class NeckMoveUsingCamera extends Command {

	private Neck neck;
	private ICamera camera;

	public NeckMoveUsingCamera(Neck neck_in, ICamera camera_in) {

		this.neck = neck_in;
		this.camera = camera_in;

		addRequirements(
			neck);

	}

	@Override
	public void initialize() {
		System.out.println("NeckMoveUsingCamera: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		double distance = camera!=null? camera.getDistanceToTarget():0.0; // call will return 0.0 by convention if no target acquired

		if (distance != 0.0) // only moves neck if target is acquired - noop otherwise
		{
			double magic_encoder_ticks = Magic.getEncoderCounts(distance);

			neck.moveCustom(magic_encoder_ticks);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("NeckMoveUsingCamera: end");
		neck.stay(); // stop()
	}
}
