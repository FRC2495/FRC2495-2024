package frc.robot.auton.sp4;

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
import frc.robot.commands.shooter.*;
import frc.robot.commands.mouth.*;
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;
import frc.robot.interfaces.*;


public class StartingPositionFourThreeNote extends SequentialCommandGroup {

	public StartingPositionFourThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new NeckHome(neck),
			
			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionFourPickupSecondNote(container, drivetrain, object_detection_camera, roller, notesensor, noteSensorTwo),

			/*new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),*/

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),

			new ShootNote(shooter, roller),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)), // test if needed 

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),
			
			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck)

			/*new DrivetrainSwerveRelative(drivetrain, container, createAfterShootSecondNoteTrajectory(container)), // test if needed 

			new StartingPositionFourPickupThirdNote(container, drivetrain, object_detection_camera, roller, notesensor, noteSensorTwo),

			new NeckMovePodiumWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new ShootNote(shooter, roller)*/

		); 
  
	}
	
   public static Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
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

	public static Trajectory createAfterShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.ONE_METER, 0, Rotation2d.fromDegrees(180)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

	public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_PICKUP_TO_SHOOT_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_THIRD_PICKUP_TO_SHOOT_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(20)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}


}