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
import frc.robot.commands.simpleshooter.*;
import frc.robot.commands.mouth.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionThreeThreeNote extends SequentialCommandGroup {

    public StartingPositionThreeThreeNote(RobotContainer container, Elevator elevator, Drawer drawer, SwerveDrivetrain drivetrain, Roller roller, SimpleShooter shooter, Neck neck, Mouth mouth){

        addCommands(

            new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

            new SimpleShooterTimedShoot(shooter, 0.5),

			new StartingPositionThreePickupSecondNote(container, drivetrain, roller),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new SimpleShooterTimedShoot(shooter, 0.5),

			new StartingPositionThreePickupThirdNote(container, drivetrain, roller),

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new SimpleShooterTimedShoot(shooter, 0.5)

        ); 
  
    }

    public Trajectory createShootPreloadTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_3_X_VALUE-AutonConstants.STARTING_POSITION_3_X_VALUE, AutonConstants.STARTING_POSITION_3_Y_VALUE-AutonConstants.STARTING_POSITION_3_Y_VALUE, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_3_TO_SHOOT_PRELOAD_X-AutonConstants.STARTING_POSITION_3_X_VALUE, AutonConstants.DISTANCE_FROM_STARTING_POSITION_3_TO_SHOOT_PRELOAD_Y-AutonConstants.STARTING_POSITION_3_Y_VALUE, Rotation2d.fromDegrees(120)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}

	public Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_X-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_SHOOT_SECOND_NOTE_Y-AutonConstants.DISTANCE_FROM_SHOOT_PRELOAD_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(120)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}
    
	public Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(120)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}



}