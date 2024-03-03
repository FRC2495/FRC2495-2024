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
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;
import frc.robot.interfaces.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionFourThreeNote extends SequentialCommandGroup {

	public StartingPositionFourThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor){

		addCommands(

			new NeckHome(neck),
			
			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new StartingPositionFourPickupSecondNote(container, drivetrain, roller, notesensor),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to april tag command later

			new NeckMovePodiumWithStallDetection(neck),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),
			
			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			//new StartingPositionOnePickupThirdNote(container, drivetrain, roller),

			new NeckMovePodiumWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new ShootNote(shooter, roller)

		); 
  
	}
	
   /* public static Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_Y, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_PICKUP_TO_SHOOT_SECOND_NOTE_X-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_PICKUP_TO_SHOOT_SECOND_NOTE_Y-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_PICKUP_Y, Rotation2d.fromDegrees(155)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}*/

	public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_PICKUP_TO_SHOOT_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_THIRD_PICKUP_TO_SHOOT_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(155)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}


}