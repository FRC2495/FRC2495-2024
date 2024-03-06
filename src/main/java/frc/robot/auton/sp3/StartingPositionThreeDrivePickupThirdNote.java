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


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionThreeDrivePickupThirdNote extends SequentialCommandGroup {

	public StartingPositionThreeDrivePickupThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera object_detection_camera){

		addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createPickupThirdNoteTrajectory(container)),

			new DrivetrainTurnUsingCamera(drivetrain, object_detection_camera)
			
		); 
  
	}

	/*public static Trajectory createPickupSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_TO_SECOND_NOTE_PICKUP_X, -AutonConstants.ONE_THIRD_OF_A_METER, Rotation2d.fromDegrees(60)),
			container.createTrajectoryConfig());

		return trajectory;
	}*/

	public static Trajectory createPickupThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_AFTER_SHOOT_SECOND_NOTE_TO_AREA_BEFORE_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_AFTER_SHOOT_SECOND_NOTE_TO_AREA_BEFORE_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(60)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}