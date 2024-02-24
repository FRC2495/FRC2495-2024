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
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;


public class StartingPositionOneShootThirdNote extends ParallelCommandGroup {

    public StartingPositionOneShootThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, ICamera apriltag_camera){

        addCommands(

			new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),   
			
			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera) // change to apriltag camera command
        ); 
  
    }
   
	public Trajectory createShootThirdNoteTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_X, AutonConstants.DISTANCE_FROM_SECOND_NOTE_PICKUP_TO_THIRD_NOTE_PICKUP_Y, Rotation2d.fromDegrees(180)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_X, AutonConstants.DISTANCE_FROM_THIRD_NOTE_PICKUP_TO_SHOOT_THIRD_NOTE_Y, Rotation2d.fromDegrees(125)),
            container.createReverseTrajectoryConfig());

		return trajectory;
	}

}