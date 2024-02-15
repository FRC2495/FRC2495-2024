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
import frc.robot.commands.shooter.*;
import frc.robot.commands.mouth.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneThreeNote extends SequentialCommandGroup {

    public StartingPositionOneThreeNote(RobotContainer container, Elevator elevator, Drawer drawer, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, Mouth mouth){

        addCommands(

			new DrivetrainTimedTurnUsingPIDController(drivetrain, 145, 2),

            new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

            new ShooterTimedShootHigh(shooter, 0.5),

			new DrivetrainTimedTurnUsingPIDController(drivetrain, -35, 2),

            new StartingPositionOnePickupSecondNote(container, drivetrain, roller),
            
            new ShooterTimedShootHigh(shooter, 0.5), // will have to change in some way to compensate for the distance

			new DrivetrainTimedTurnUsingPIDController(drivetrain, 65, 2),

            new StartingPositionOnePickupThirdNote(container, drivetrain, roller),

            new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShooterTimedShootHigh(shooter, 0.5) // will have to change in some way to compensate for the distance

        ); 
  
    }

    public Trajectory createShootPreloadTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_1_X_VALUE-AutonConstants.STARTING_POSITION_1_X_VALUE, AutonConstants.STARTING_POSITION_1_Y_VALUE-AutonConstants.STARTING_POSITION_1_Y_VALUE, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_1_X_VALUE-AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_AREA_TO_SHOOT_PRELOAD_X, AutonConstants.STARTING_POSITION_1_Y_VALUE-AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_AREA_TO_SHOOT_PRELOAD_Y, Rotation2d.fromDegrees(180)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}
    
    public Trajectory createShootSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_X, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_Y, Rotation2d.fromDegrees(155)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}



}