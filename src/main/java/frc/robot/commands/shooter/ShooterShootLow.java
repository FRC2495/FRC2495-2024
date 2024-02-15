// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterShootLow extends InstantCommand {

	private Shooter shooter;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ShooterShootLow(Shooter shooter_in) {

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		this.shooter = shooter_in;
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		addRequirements(
			shooter);
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		//System.out.println("ShooterShootLow: initialize");
		shooter.shootLow();
	}
}
