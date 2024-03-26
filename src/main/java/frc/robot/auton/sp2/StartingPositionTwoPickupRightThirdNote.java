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
import frc.robot.sensors.*;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;


public class StartingPositionTwoPickupRightThirdNote extends ParallelCommandGroup {

	public StartingPositionTwoPickupRightThirdNote(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, NoteSensor notesensor, NoteSensor noteSensorTwo, ICamera object_detection_camera){

		addCommands(

			new RollerSuperSmartRoll(roller, notesensor, noteSensorTwo),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, -90, .5),

			new StartingPositionTwoDriveAndTurnToRightNote(drivetrain, container, object_detection_camera)

			//new DrivetrainSwerveRelative(drivetrain, container, createPickupRightThirdNoteTrajectory(container))

			//new DrivetrainTurnUsingCamera(drivetrain, object_detection_camera)  //use if above doesn't work + test
			
		); 
  
	}
   

}