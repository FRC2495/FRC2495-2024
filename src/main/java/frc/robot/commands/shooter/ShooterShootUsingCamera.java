package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Shooter;
//import frc.robot.util.Magic;

/**
 *
 */
public class ShooterShootUsingCamera extends Command {

	private Shooter shooter;

	public ShooterShootUsingCamera(Shooter shooter_in) {

		this.shooter = shooter_in;

		addRequirements(
			shooter);

	}

	// Called just before this Command runs the first time
	/*@Override
	public void initialize() {
		//System.out.println("ShooterShootUsingCamera: initialize");
		//double distance = Robot.camera!=null?Robot.camera.getDistanceToCompositeTargetUsingHorizontalFov():100;
		//double distance = Robot.camera!=null?Robot.camera.getFilteredDistanceToCompositeTarget():100;
		
		//double custom_rpm = Robot.camera!=null?3200.0+(distance-100)*10:3200;

		double verticalOffset = camera!=null? camera.getFilteredVerticalOffsetToCompositeTarget():10;

		double magic_rpm = Magic.getRpm(verticalOffset);

		shooter.shootCustom(magic_rpm);
	}*/
}
