package frc.robot.auton.sp2;

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
import frc.robot.subsystems.*;
//import frc.robot.auton.sp2.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionTwoRightThreeNote extends SequentialCommandGroup {

    public StartingPositionTwoRightThreeNote(RobotContainer container, Elevator elevator, Drawer drawer, SwerveDrivetrain drivetrain, Roller roller, SimpleShooter shooter, Neck neck, Mouth mouth){

        addCommands(

			new ShooterTimedShoot(shooter, 0.5),

			new StartingPositionTwoPickupSecondNote(container, drivetrain, roller),

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShooterTimedShoot(shooter, 0.5),

			new StartingPositionTwoPickupRightThirdNote(container, drivetrain, roller),

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new ShooterTimedShoot(shooter, 0.5),

			new DrivetrainSwerveRelative(drivetrain, container, createLeaveAfterShootRightThirdNoteTrajectory(container))

        ); 
  
    }



    
    public Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_2_X_VALUE-AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_X, AutonConstants.STARTING_POSITION_2_Y_VALUE-AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}

	public Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(-90)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_2_X_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_X, AutonConstants.STARTING_POSITION_2_Y_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_RIGHT_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}	

	public Trajectory createLeaveAfterShootRightThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_2_X_VALUE-AutonConstants.STARTING_POSITION_2_X_VALUE, AutonConstants.STARTING_POSITION_2_Y_VALUE, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_NOTE_TO_LEAVE_X-AutonConstants.STARTING_POSITION_2_X_VALUE, AutonConstants.STARTING_POSITION_2_Y_VALUE-AutonConstants.STARTING_POSITION_2_Y_VALUE, Rotation2d.fromDegrees(180)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}


}