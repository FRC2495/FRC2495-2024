package frc.robot.auton.sp2;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.commands.shooter.*;
import frc.robot.commands.roller.RollerStop;
import frc.robot.commands.roller.RollerTimedRelease;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;
//import frc.robot.auton.sp2.*;


public class StartingPositionTwoTwoNote extends SequentialCommandGroup {

	public StartingPositionTwoTwoNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new WaitCommand(.5),

			new StartingPositionTwoPickupSecondNote(container, drivetrain, roller, notesensor, noteSensorTwo),

			new NeckMoveSubWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createLeaveAfterShootSecondNoteTrajectory(container))

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
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP, 0, Rotation2d.fromDegrees(180)),
			container.createReverseTrajectoryConfig());

		return trajectory;
	}

	public static Trajectory createLeaveAfterShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_LEAVE_X, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}



}