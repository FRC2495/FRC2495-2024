package frc.robot.auton.sp1;

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
import frc.robot.commands.mouth.*;
import frc.robot.commands.roller.RollerSuperSmartRoll;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;


public class StartingPositionOnePickupThirdNote extends ParallelCommandGroup {

	public StartingPositionOnePickupThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, ICamera object_detection_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor, noteSensorTwo),

			//new DrivetrainTurnUsingCamera(drivetrain, camera),     use only if below doesnt work 

			new StartingPositionOneDrivePickupThirdNote(container, drivetrain, object_detection_camera)
 
		); 
  
	}

	/*public static Trajectory createPickupThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_X, -AutonConstants.ONE_THIRD_OF_A_METER, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}*/


}