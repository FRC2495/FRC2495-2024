package frc.robot.auton.sp3;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.shooter.*;
import frc.robot.commands.mouth.*;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;


public class StartingPositionThreeDriveShootThirdNote extends SequentialCommandGroup {

	public StartingPositionThreeDriveShootThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera apriltag_camera){

		addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),
			
			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera) 
		); 
  
	}
   
	
	/*public static Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(-35)),
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
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_Y, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_X, Rotation2d.fromDegrees(-35)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

}