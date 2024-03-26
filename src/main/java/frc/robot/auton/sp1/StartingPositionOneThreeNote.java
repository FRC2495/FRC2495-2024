package frc.robot.auton.sp1;

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
import frc.robot.interfaces.*;
import frc.robot.sensors.*;


public class StartingPositionOneThreeNote extends SequentialCommandGroup {

	public StartingPositionOneThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionOnePickupSecondNote(container, drivetrain, roller, notesensor, noteSensorTwo),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)), 

			/*new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to april tag camera command later 

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),*/

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),

			new ShootNote(shooter, roller),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 65, 2),

			new NeckMoveDownWithStallDetection(neck)

			/*new StartingPositionOnePickupThirdNote(container, drivetrain, roller, object_detection_camera, notesensor),

			new NeckMovePodiumWithStallDetection(neck)*/

			//new StartingPositionOneShootThirdNote(container, drivetrain, apriltag_camera),

			/*new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),   
			
			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to apriltag camera command

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)

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
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_LEFT_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_X, AutonConstants.DISTANCE_FROM_SECOND_LEFT_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_Y, Rotation2d.fromDegrees(240)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

	/*public static Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(-AutonConstants.ONE_THIRD_OF_A_METER, 0, Rotation2d.fromDegrees(0)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	/*public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_Y-AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_X-AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, Rotation2d.fromDegrees(45)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	
	
	


}