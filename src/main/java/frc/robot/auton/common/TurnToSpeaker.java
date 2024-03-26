package frc.robot.auton.common;

import java.util.List;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;

import frc.robot.auton.AutonConstants;
import frc.robot.interfaces.*;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;


public class TurnToSpeaker extends ParallelCommandGroup{
	
	public TurnToSpeaker(SwerveDrivetrain drivetrain, RobotContainer container, Roller roller, Neck neck, ICamera apriltag_camera) {

		addCommands(

			new NeckMoveOptimalPositionForShooting(neck, apriltag_camera),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera)        
		);
	}
	

}
