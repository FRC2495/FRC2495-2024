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
import frc.robot.commands.neck.NeckMoveStartingPositionOneSecondNoteWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.commands.neck.NeckMoveUsingCamera;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneThreeNote extends SequentialCommandGroup {

	public StartingPositionOneThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor){

		addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionOnePickupSecondNote(container, drivetrain, roller, notesensor),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)), 

			//new NeckMovePodiumWithStallDetection(neck), // check to see if this works later 

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to april tag camera command later 

			//new NeckMoveUsingCamera(neck, apriltag_camera),

			new NeckMoveStartingPositionOneSecondNoteWithStallDetection(neck),

			new ShootNote(shooter, roller),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 65, 2),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionOnePickupThirdNote(container, drivetrain, roller, object_detection_camera, notesensor),

			new NeckMovePodiumWithStallDetection(neck)

			//new StartingPositionOneShootThirdNote(container, drivetrain, apriltag_camera),

			/*new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),   
			
			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to apriltag camera command

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)

			new ShootNote(shooter, roller)*/

		); 
  
	}

	/*public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_X, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_Y, Rotation2d.fromDegrees(45)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	public static Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(-AutonConstants.ONE_AND_A_HALF_METER, 0, Rotation2d.fromDegrees(0)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

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