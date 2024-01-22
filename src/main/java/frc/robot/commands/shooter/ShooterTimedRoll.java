/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.Shooter;

/**
 * Add your docs here.
 */
public class ShooterTimedRoll extends WaitCommand {

	private Shooter shooter;

	/**
	 * Add your docs here.
	 */
	public ShooterTimedRoll(Shooter shooter, double timeout) {
		super(timeout);
		this.shooter = shooter;
		addRequirements(shooter);
		
		
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
		System.out.println("ShooterTimedRoll: initialize");
		super.initialize();
		shooter.roll();

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Called once after timeout
	@Override
	public void end(boolean interrupted) {
		System.out.println("ShooterTimedRoll: end");
		
		super.end(interrupted);
	}
}
