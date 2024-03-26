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
import frc.robot.commands.roller.RollerSuperSmartRoll;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;


public class StartingPositionTwoPickupLeftThirdNote extends ParallelCommandGroup {

	public StartingPositionTwoPickupLeftThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, ICamera object_detection_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor, noteSensorTwo),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 90, .3),       use only if below doesnt work

			//new DrivetrainSwerveRelative(drivetrain, container, createPickupLeftThirdNoteTrajectory(container)),

			new StartingPositionTwoDriveAndTurnToLeftNote(drivetrain, container, object_detection_camera)

			//new DrivetrainTurnUsingCamera(drivetrain, object_detection_camera)
			
		); 
  
	}
   
	



}