package frc.robot.auton.sp4;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.ShootNote;
import frc.robot.auton.common.TurnToSpeaker;
import frc.robot.auton.sp4.*;
import frc.robot.commands.drivetrain.DrivetrainSwerveRelative;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.roller.RollerReleaseShortDistance;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;

// when using this path, make sure to position the robot closer to the alliance station wall so note is picked up

public class StartingPositionFourTwoNoteAtMidline extends SequentialCommandGroup {

	public StartingPositionFourTwoNoteAtMidline(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new StartingPositionFourOneNoteAndLeave(container, drivetrain, roller, shooter, neck),

			new StartingPositionFourPickupMidlineNote(container, drivetrain, roller, notesensor, noteSensorTwo),

			//new DrivetrainSwerveRelative(drivetrain, container, createAfterMidlineNotePickupTrajectory(container)),

			//new DrivetrainSwerveRelative(drivetrain, container, createMoveTowardsSpeakerTrajectory(container)),

			new StartingPositionFourMoveToSpeakerAndMoveNeck(drivetrain, container, roller, neck, apriltag_camera),

			new TurnToSpeaker(drivetrain, container, roller, neck, apriltag_camera),

			new RollerReleaseShortDistance(roller),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck)

		); 
  
	}

	public static Trajectory createAfterMidlineNotePickupTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_MIDLINE_NOTE_PICKUP_TO_AREA_BEFORE_SHOOT_MIDLINE_NOTE_X, AutonConstants.DISTANCE_FROM_MIDLINE_NOTE_PICKUP_TO_AREA_BEFORE_SHOOT_MIDLINE_NOTE_Y, Rotation2d.fromDegrees(180)),
			container.createFastReverseTrajectoryConfig());

		return trajectory;
	}


	public static Trajectory createMoveTowardsSpeakerTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_AFTER_MIDLINE_NOTE_PICKUP_TO_TOWARDS_SPEAKER_X, -AutonConstants.DISTANCE_FROM_AFTER_MIDLINE_NOTE_PICKUP_TO_TOWARDS_SPEAKER_Y, Rotation2d.fromDegrees(140)),
			container.createFastReverseTrajectoryConfig());

		return trajectory;
	}
	
}