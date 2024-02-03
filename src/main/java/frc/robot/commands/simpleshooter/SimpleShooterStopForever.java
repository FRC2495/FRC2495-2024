
package frc.robot.commands.simpleshooter;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.SimpleShooter;

/**
 *
 */
public class SimpleShooterStopForever extends Command {

	private SimpleShooter shooter;

	public SimpleShooterStopForever(SimpleShooter shooter) {
		this.shooter = shooter;
		addRequirements(shooter);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShooterStopForever: initialize");
		shooter.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false; // we run forever (unless interrupted)
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("ShooterStopForever: end");
	}
}
