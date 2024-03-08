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
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.interfaces.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionSixOneNoteAndPickupAtMidline extends SequentialCommandGroup {

    public StartingPositionSixOneNoteAndPickupAtMidline(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor){

        addCommands(

			new StartingPositionSixOneNoteAndLeave(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor),

			new StartingPositionSixPickupMidlineNote(container, drivetrain, object_detection_camera, roller, notesensor)

        ); 
  
    }

	public static Trajectory createMoveAwayFromSpeakerTrajectory(RobotContainer container) {
		// An example trajectory to follow. All units in meters.
		Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
			// Start at the origin facing the -X direction
			new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
			// Pass through these waypoints
			List.of(),
			// End straight ahead of where we started, facing forward
			new Pose2d(0, -AutonConstants.DISTANCE_FROM_STARTING_POSITION_3_TO_BEFORE_MIDLINE_NOTE_PICKUP_Y, Rotation2d.fromDegrees(0)),
			container.createTrajectoryConfig());

		return trajectory;
	}


}