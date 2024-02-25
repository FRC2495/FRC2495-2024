
package frc.robot.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrivetrain;
import frc.robot.interfaces.ICamera;

/**
 *
 */
public class DrivetrainDriveUsingAprilTagCamera extends Command {

	private SwerveDrivetrain drivetrain;
	private ICamera camera;
	private Joystick joystick;

	public static final double JOYSTICK_AXIS_THRESHOLD = 0.15;

	public DrivetrainDriveUsingAprilTagCamera(SwerveDrivetrain drivetrain, ICamera camera, Joystick joystick) {
		this.drivetrain = drivetrain;
		this.camera = camera;
		this.joystick = joystick;
		
		addRequirements(drivetrain);
		addRequirements(camera);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainDriveUsingAprilTagCamera: initialize");
	}


	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {

		drivetrain.drive(
			-MathUtil.applyDeadband(joystick.getY(), JOYSTICK_AXIS_THRESHOLD),
			-MathUtil.applyDeadband(joystick.getX(), JOYSTICK_AXIS_THRESHOLD),
			-camera.getAngleToTurnToTarget()/90.00,
			true, true);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainDriveUsingAprilTagCamera: end");
		drivetrain.stop();
	}
}
