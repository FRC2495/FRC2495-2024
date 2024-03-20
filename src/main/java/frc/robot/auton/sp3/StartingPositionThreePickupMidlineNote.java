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
import frc.robot.interfaces.ICamera;
import frc.robot.commands.mouth.*;
import frc.robot.commands.roller.RollerSuperSmartRoll;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;


public class StartingPositionThreePickupMidlineNote extends ParallelCommandGroup {

	public StartingPositionThreePickupMidlineNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera object_detection_camera, Roller roller, NoteSensor notesensor){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor),

			//new DrivetrainSwerveRelative(drivetrain, container, createPickupMidlineNoteTrajectory(container))

			//new StartingPositionThreeDrivePickupMidlineNote(container, drivetrain, object_detection_camera)

			new DrivetrainSwerveRelative(drivetrain, container, createMoveTowardsMidlineNoteTrajectory(container))
		); 
  
	}

	/*public static Trajectory createPickupMidlineNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_X, -AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_Y, Rotation2d.fromDegrees(-80)),
			container.createTrajectoryConfig());

		return trajectory;
	}*/

	/*public static Trajectory createPickupSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.ONE_THIRD_OF_A_METER, AutonConstants.DISTANCE_FROM_STARTING_POSITION_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(60)),
			container.createTrajectoryConfig());

		return trajectory;
	}*/

	
	public static Trajectory createMoveTowardsMidlineNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_X, -AutonConstants.DISTANCE_FROM_BEFORE_MIDLINE_NOTE_PICKUP_TO_MIDLINE_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}