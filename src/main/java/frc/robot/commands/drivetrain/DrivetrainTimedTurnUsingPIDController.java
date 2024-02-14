/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.SwerveDrivetrain;

/**
 * Add your docs here.
 */
public class DrivetrainTimedTurnUsingPIDController extends WaitCommand {

	private SwerveDrivetrain drivetrain;
	private int m_angle;
	
	/**
	 * Add your docs here.
	 */
	public DrivetrainTimedTurnUsingPIDController(SwerveDrivetrain drivetrain, int angle, double timeout) {
		super(timeout);
		this.drivetrain = drivetrain;
		m_angle = angle;
		addRequirements(drivetrain);
		
		
		// ControllerBase is not a real subsystem, so no need to reserve it
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainTimedTurnUsingPIDController: initialize");
		super.initialize();
		drivetrain.turnAngleUsingPidController(m_angle);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Called once after timeout
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainTimedTurnUsingPIDController: end");
		
		super.end(interrupted);
	}
}
