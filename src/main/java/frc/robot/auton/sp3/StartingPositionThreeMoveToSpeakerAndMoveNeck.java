package frc.robot.auton.sp3;

import java.util.List;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.MoveNeckUpReleaseNoteAndAdjustToAprilTag;
import frc.robot.interfaces.*;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.neck.NeckMoveUsingCamera;


public class StartingPositionThreeMoveToSpeakerAndMoveNeck extends ParallelRaceGroup{
	
	public StartingPositionThreeMoveToSpeakerAndMoveNeck(SwerveDrivetrain drivetrain, RobotContainer container, Roller roller, Neck neck, ICamera apriltag_camera) {

		addCommands(

			//new NeckMoveUsingCamera(neck, apriltag_camera),

			new MoveNeckUpReleaseNoteAndAdjustToAprilTag(neck, roller, apriltag_camera),

			new DrivetrainSwerveRelative(drivetrain, container, createAreaBeforeShootSecondNoteTrajectory(container))

		);
	}
	
	public static Trajectory createAreaBeforeShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_X, -AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
			container.createFastReverseTrajectoryConfig());

		return trajectory;
	}

	public static Trajectory createShootMidlineNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_MIDLINE_NOTE_PICKUP_TO_AREA_TOWARDS_SPEAKER_X, -AutonConstants.DISTANCE_FROM_MIDLINE_NOTE_PICKUP_TO_AREA_TOWARDS_SPEAKER_Y, Rotation2d.fromDegrees(120)),
			container.createFastReverseTrajectoryConfig());

		return trajectory;
	}

}
