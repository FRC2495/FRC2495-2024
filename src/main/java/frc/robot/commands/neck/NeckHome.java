package frc.robot.commands.neck;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Neck;

/**
 *
 */
public class NeckHome extends Command {

	private Neck neck;

	public NeckHome(Neck neck) {
		this.neck = neck;
		addRequirements(neck);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("NeckHome: initialize");
		neck.home();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !neck.checkHome();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("NeckHome: end");
		neck.stop();
	}
}
