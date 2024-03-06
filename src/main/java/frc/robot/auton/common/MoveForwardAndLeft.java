package frc.robot.auton.common;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.*;
import frc.robot.subsystems.*;

// moves forward and left, ending oriented at specified heading
public class MoveForwardAndLeft extends SequentialCommandGroup {

	private double forwardDistance;
	private double leftDistance;
	private double finalHeading;

	public MoveForwardAndLeft(SwerveDrivetrain drivetrain, RobotContainer container, double forwardDistance, double leftDistance, double finalHeading) {

		this.forwardDistance = forwardDistance;
		this.leftDistance = leftDistance;
		this.finalHeading = finalHeading;
		
		addCommands(
			new DrivetrainSwerveRelative(drivetrain, container, createForwardAndLeftTrajectory(container))           
		); 

	}

	public Trajectory createForwardAndLeftTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the +X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End ahead of where we started, facing sideway
			// https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/coordinate-systems.html
			new Pose2d(+forwardDistance, +leftDistance, Rotation2d.fromDegrees(+finalHeading)),
			container.createTrajectoryConfig());

		return trajectory;
	}
   
}