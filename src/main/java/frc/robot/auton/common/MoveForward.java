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

// moves forward by specified distance
public class MoveForward extends SequentialCommandGroup {

	private double distance;
	
	public MoveForward(SwerveDrivetrain drivetrain, RobotContainer container, double distance) {

		this.distance = distance;
		
		addCommands(
			new DrivetrainSwerveRelative(drivetrain, container, createMoveForwardTrajectory(container))
		); 
  
	}
	
	public Trajectory createMoveForwardTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the +X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			// https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/coordinate-systems.html
			new Pose2d(+distance, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}

}