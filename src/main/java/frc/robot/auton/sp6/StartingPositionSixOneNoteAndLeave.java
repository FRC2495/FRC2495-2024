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


public class StartingPositionSixOneNoteAndLeave extends SequentialCommandGroup {

    public StartingPositionSixOneNoteAndLeave(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, NoteSensor notesensor){

        addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createMoveAwayFromSpeakerTrajectory(container)),

			new DrivetrainSwerveRelative(drivetrain, container, createMoveTowardsMidlineNoteTrajectory(container))

        ); 
  
    }

	public static Trajectory createMoveAwayFromSpeakerTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(0, -AutonConstants.DISTANCE_FROM_STARTING_POSITION_3_TO_BEFORE_MIDLINE_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}

	public static Trajectory createMoveTowardsMidlineNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(-AutonConstants.DISTANCE_FROM_STARTING_POSITION_3_TO_BEFORE_MIDLINE_NOTE_PICKUP_X, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}