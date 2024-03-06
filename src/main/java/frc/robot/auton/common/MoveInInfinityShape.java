package frc.robot.auton.common;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.commands.drivetrain.*;
import frc.robot.subsystems.*;

// moves in infinity shape
public class MoveInInfinityShape extends SequentialCommandGroup {

	private double sideLength;

	public MoveInInfinityShape(SwerveDrivetrain drivetrain, RobotContainer container, double sideLength) {

		this.sideLength = sideLength;
		
		addCommands(
			new DrivetrainSwerveRelative(drivetrain, container, createFigure8ShapeTrajectory(container))           
		); 

	}

	public Trajectory createFigure8ShapeTrajectory(RobotContainer container) {
	// An example trajectory to follow. All units in meters.
	Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
		// Start at the origin facing the +X direction
		new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
		// Pass through these two interior waypoints, making an 's' curve path
		List.of(
			new Translation2d(+sideLength/4, -sideLength/4),
			new Translation2d(0, -sideLength/2),
			new Translation2d(-sideLength/4, -sideLength/4),

			new Translation2d(0, 0),

			new Translation2d(+sideLength/4, +sideLength/4),
			new Translation2d(0, +sideLength/2),
			new Translation2d(-sideLength/4, +sideLength/4)
			),
		// End n meters straight ahead of where we started, facing forward
		// https://docs.wpilib.org/en/stable/docs/software/advanced-controls/geometry/coordinate-systems.html
		new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
		container.createTrajectoryConfig());

		return trajectory;
	}
   
}