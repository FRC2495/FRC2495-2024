package frc.robot.auton.sp3;

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
import frc.robot.interfaces.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;


public class StartingPositionThreeThreeNote extends SequentialCommandGroup {

    public StartingPositionThreeThreeNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

        addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionThreePickupSecondNote(container, drivetrain, object_detection_camera, roller, notesensor, noteSensorTwo),

			new NeckMoveSubWithStallDetection(neck), // moves neck up so note isnt dragging on the floor

			//new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),

			//new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),
			
			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			/*new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera),

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),*/

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck)

			/*new DrivetrainSwerveRelative(drivetrain, container, createAfterShootSecondNoteTrajectory(container)), // test to see where robot ends up

			new StartingPositionThreePickupThirdNote(container, drivetrain, object_detection_camera, roller, notesensor),

			new NeckMovePodiumWithStallDetection(neck),
			
			new StartingPositionThreeDriveShootThirdNote(container, drivetrain, apriltag_camera),

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
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_X, -AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_Y, Rotation2d.fromDegrees(100)),
			container.createFastReverseTrajectoryConfig());

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
			new Pose2d(-AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_X, 0, Rotation2d.fromDegrees(0)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	/*public static Trajectory createAfterShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_X, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}*/


}