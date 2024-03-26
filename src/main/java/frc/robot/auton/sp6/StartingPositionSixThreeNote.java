package frc.robot.auton.sp6;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;
import frc.robot.interfaces.*;


public class StartingPositionSixThreeNote extends SequentialCommandGroup {

	public StartingPositionSixThreeNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, NoteSensor notesensor, NoteSensor noteSensorTwo, ICamera object_detection_camera, ICamera apriltag_camera){

		addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionSixPickupSecondNote(container, drivetrain, object_detection_camera, roller, notesensor, noteSensorTwo),

			/*new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),*/

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck)

			/*new DrivetrainSwerveRelative(drivetrain, container, createAfterShootSecondNoteTrajectory(container)),

			//new DrivetrainSwerveRelative(drivetrain, container, createAreaBeforeThirdNotePickupTrajectory(container)),

			new StartingPositionSixPickupThirdNote(container, drivetrain, roller, notesensor, noteSensorTwo),

			new NeckMovePodiumWithStallDetection(neck),

			//new DrivetrainSwerveRelative(drivetrain, container, createAreaBeforeShootThirdNoteTrajectory(container)),

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),

			new ShootNote(shooter, roller)*/

		); 
  
	}

	public static Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.ONE_METER, 0, Rotation2d.fromDegrees(0)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

	public static Trajectory createAfterShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.ONE_METER, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}

	/*public static Trajectory createAreaBeforeThirdNotePickupTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_6_X_VALUE-AutonConstants.STARTING_POSITION_6_X_VALUE, AutonConstants.STARTING_POSITION_6_Y_VALUE-AutonConstants.STARTING_POSITION_6_Y_VALUE, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_6_X_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X, AutonConstants.STARTING_POSITION_6_Y_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_Y, Rotation2d.fromDegrees(-160)),
			container.createTrajectoryConfig());

		return trajectory;
	}
	
	public static Trajectory createAreaBeforeShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_X, Rotation2d.fromDegrees(160)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_AFTER_SHOOT_SECOND_NOTE_TO_AREA_BEFORE_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_AFTER_SHOOT_SECOND_NOTE_TO_AREA_BEFORE_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(-160)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}



}