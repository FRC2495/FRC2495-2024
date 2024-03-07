
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.SwerveDrivetrain;
import frc.robot.interfaces.ICamera;

/**
 *
 */
public class DrivetrainTurnUsingCamera extends Command {

	private SwerveDrivetrain drivetrain;
	private ICamera camera;

	public static final double JOYSTICK_AXIS_THRESHOLD = 0.15;
	public final static int TURN_USING_CAMERA_ON_TARGET_MINIMUM_COUNT = 10;

	public int onTargetCountTurningUsingCamera;

	public DrivetrainTurnUsingCamera(SwerveDrivetrain drivetrain, ICamera camera) {
		this.drivetrain = drivetrain;
		this.camera = camera;
		
		addRequirements(drivetrain);
		addRequirements(camera);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainTurnUsingCamera: initialize");
		onTargetCountTurningUsingCamera = 0;
	}


	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {

				drivetrain.drive(
					-0,
					-0,
					-camera.getAngleToTurnToTarget()/90.00, // normalized value
					true, true);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !tripleCheckTurnUsingCamera();
	}

	public boolean tripleCheckTurnUsingCamera()
	{
		boolean isTurningUsingCamera = true;
		
		{
			boolean isOnTarget = Math.abs(camera.getAngleToTurnToTarget()) < 1.0; // angle in degree, TODO adjust if 1 degree is too strict
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountTurningUsingCamera++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountTurningUsingCamera > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountTurningUsingCamera = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (turning using camera).");
				} else {
					// we are definitely turning
				}
			}
			
			if (onTargetCountTurningUsingCamera > TURN_USING_CAMERA_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isTurningUsingCamera = false;
			}
			
			if (!isTurningUsingCamera) {
				System.out.println("You have reached the target (turning using camera).");
				//stop();				 
			}
		}

		return isTurningUsingCamera;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainTurnUsingCamera: end");
		drivetrain.stop();
	}
}
