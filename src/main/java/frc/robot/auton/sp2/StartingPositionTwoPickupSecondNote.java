package frc.robot.auton.sp2;

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
import frc.robot.commands.roller.RollerRollUntilNoteSensed;
import frc.robot.commands.roller.RollerSmartRoll;
import frc.robot.commands.roller.RollerSuperSmartRoll;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;


public class StartingPositionTwoPickupSecondNote extends ParallelCommandGroup {

	public StartingPositionTwoPickupSecondNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor, noteSensorTwo),

			new DrivetrainSwerveRelative(drivetrain, container, createPickupSecondNoteTrajectory(container))
			
		); 
  
	}
   
	
	public static Trajectory createPickupSecondNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_STARTING_POSITION_2_TO_SECOND_NOTE_PICKUP, 0, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}	


}