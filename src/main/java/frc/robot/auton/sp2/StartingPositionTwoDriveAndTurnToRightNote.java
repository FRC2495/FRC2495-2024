package frc.robot.auton.sp2;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.auton.AutonConstants;
import frc.robot.commands.drivetrain.*;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionTwoDriveAndTurnToRightNote extends SequentialCommandGroup {
	
	public StartingPositionTwoDriveAndTurnToRightNote(SwerveDrivetrain drivetrain, RobotContainer container, ICamera object_detection_camera) {
		
		addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createPickupRightThirdNoteTrajectory(container)),

			new DrivetrainTurnUsingCamera(drivetrain, object_detection_camera),  //use if above doesn't work + test

			new DrivetrainSwerveRelative(drivetrain, container, createMoveForwardTrajectory(container))

        ); 
  
    }
    
    public Trajectory createPickupRightThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_2_X_VALUE-AutonConstants.STARTING_POSITION_2_X_VALUE, AutonConstants.STARTING_POSITION_2_Y_VALUE-AutonConstants.STARTING_POSITION_2_Y_VALUE, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_2_X_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X, AutonConstants.STARTING_POSITION_2_Y_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(90)),
            container.createTrajectoryConfig());

		return trajectory;
	}	

	public Trajectory createMoveForwardTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_TO_PICKUP_RIGHT_THIRD_NOTE_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_TO_PICKUP_RIGHT_THIRD_NOTE_Y, Rotation2d.fromDegrees(180)),
            container.createTrajectoryConfig());

		return trajectory;
	}	

}