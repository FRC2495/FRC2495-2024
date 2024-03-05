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

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class MoveInReverseAndLeft extends SequentialCommandGroup {

	private double reverseDistance;
	private double leftDistance;
	private double finalHeading;

	public MoveInReverseAndLeft(SwerveDrivetrain drivetrain, RobotContainer container,  double reverseDistance, double leftDistance, double finalHeading) {

		this.reverseDistance = reverseDistance;
		this.leftDistance = leftDistance;
		this.finalHeading = finalHeading;

		addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createReverseAndRightTrajectory(container))
		   
		); 
  
	}

	public Trajectory createReverseAndRightTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End ahead of where we started, facing sideway
			// https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/coordinate-systems.html
			new Pose2d(+reverseDistance, -leftDistance, Rotation2d.fromDegrees(finalHeading)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

   
}