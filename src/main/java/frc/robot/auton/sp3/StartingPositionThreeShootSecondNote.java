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
import frc.robot.commands.mouth.*;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionThreeShootSecondNote extends ParallelCommandGroup {

    public StartingPositionThreeShootSecondNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera camera){

        addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),
			
			new DrivetrainTurnUsingCamera(drivetrain, camera)
        ); 
  
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


}