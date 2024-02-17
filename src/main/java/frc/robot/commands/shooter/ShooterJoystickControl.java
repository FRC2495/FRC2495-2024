package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrivetrain;

/**
 *
 */
public class ShooterJoystickControl extends Command {

	private Shooter shooter;
	//private SwerveDrivetrain drivetrain;
	private Joystick joystick;


	public ShooterJoystickControl(Shooter shooter, SwerveDrivetrain drivetrain, Joystick joystick) {
		this.shooter = shooter;
		//this.drivetrain = drivetrain;
		this.joystick = joystick;
		
		addRequirements(
			shooter,
			drivetrain); // this is needed so that the default drivetrain command does not run at the same time
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShooterJoystickControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		shooter.joystickControl(joystick);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("ShooterJoystickControl: end");
		shooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	/*@Override
	public void interrupted() {
		System.out.println("ShooterJoystickControl: interrupted");
		end(); // TODO check if this is a good idea
	}*/
}
