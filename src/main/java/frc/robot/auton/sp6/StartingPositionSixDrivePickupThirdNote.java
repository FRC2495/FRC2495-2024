package frc.robot.auton.sp6;

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


public class StartingPositionSixDrivePickupThirdNote extends SequentialCommandGroup {

	public StartingPositionSixDrivePickupThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera object_detection_camera){

		addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createPickupThirdNoteTrajectory(container)),

			new DrivetrainTurnUsingCamera(drivetrain, object_detection_camera)
			
		); 
  
	}
   
	public static Trajectory createPickupThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_Y-AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_X, AutonConstants.DISTANCE_FROM_SHOOT_SECOND_TO_AREA_BEFORE_THIRD_PICKUP_X-AutonConstants.DISTANCE_FROM_AREA_BEFORE_THIRD_PICKUP_TO_THIRD_PICKUP_Y, Rotation2d.fromDegrees(-160)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}