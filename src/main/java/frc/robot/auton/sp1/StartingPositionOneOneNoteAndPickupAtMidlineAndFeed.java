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
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveFeedNoteWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.commands.neck.NeckMoveUsingCamera;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;

// when using this path, make sure to position the robot closer to the starting line so note isnt bumped into

public class StartingPositionOneOneNoteAndPickupAtMidlineAndFeed extends SequentialCommandGroup {

	public StartingPositionOneOneNoteAndPickupAtMidlineAndFeed(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, NoteSensor notesensor){

		addCommands(

			new StartingPositionOneOneNoteAndPickupAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor),

			new NeckMoveFeedNoteWithStallDetection(neck),

			new ShootNote(shooter, roller)

		); 
  
	}
	
	
}