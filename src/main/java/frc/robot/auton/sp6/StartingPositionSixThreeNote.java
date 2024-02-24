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
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.auton.sp3.StartingPositionThreePickupThirdNote;
import frc.robot.sensors.*;
import frc.robot.interfaces.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionSixThreeNote extends SequentialCommandGroup {

    public StartingPositionSixThreeNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, NoteSensor notesensor, ICamera camera){

        addCommands(

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

            new NeckMoveDownWithStallDetection(neck),

			new StartingPositionSixPickupSecondNote(container, drivetrain, roller, notesensor),

			new NeckMovePodiumWithStallDetection(neck),

			new DrivetrainTurnUsingCamera(drivetrain, camera),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShootNote(shooter, roller),

			new NeckMoveDownWithStallDetection(neck),

			new DrivetrainSwerveRelative(drivetrain, container, createAreaBeforeThirdNotePickupTrajectory(container)),

			new StartingPositionThreePickupThirdNote(container, drivetrain, roller, notesensor),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new ShooterTimedShootHigh(shooter, 0.5)

        ); 
  
    }

	public Trajectory createAreaBeforeThirdNotePickupTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.STARTING_POSITION_6_X_VALUE-AutonConstants.STARTING_POSITION_6_X_VALUE, AutonConstants.STARTING_POSITION_6_Y_VALUE-AutonConstants.STARTING_POSITION_6_Y_VALUE, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.STARTING_POSITION_6_X_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X, AutonConstants.STARTING_POSITION_6_Y_VALUE-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_Y, Rotation2d.fromDegrees(120)),
            container.createTrajectoryConfig());

		return trajectory;
	}
    
	/*public Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(180.0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_6_TO_SHOOT_PRELOAD_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_STARTING_POSITION_6_TO_SHOOT_PRELOAD_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(120)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}*/



}