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

	public StartingPositionOnePickupThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, ICamera object_detection_camera, NoteSensor notesensor){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor),

			//new DrivetrainTurnUsingCamera(drivetrain, camera),     use only if below doesnt work 

			new DrivetrainSwerveRelative(drivetrain, container, createPickupThirdNoteTrajectory(container))
 
		); 
  
	}
   
	public static Trajectory createPickupThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_Y, AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_X-AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_STARTING_POSITION_1_TO_SECOND_NOTE_PICKUP_Y-AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(-45)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}