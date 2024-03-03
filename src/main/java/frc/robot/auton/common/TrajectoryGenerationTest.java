package frc.robot.auton.common;


import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.sp1.*;
import frc.robot.auton.sp2.*;
import frc.robot.auton.sp3.*;
import frc.robot.auton.sp4.*;
import frc.robot.auton.sp6.*;
import frc.robot.commands.drivetrain.DrivetrainSwerveRelative;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class TrajectoryGenerationTest extends SequentialCommandGroup {
	
	public TrajectoryGenerationTest(SwerveDrivetrain drivetrain, RobotContainer container, ICamera object_detection_camera, ICamera apriltag_camera) {
		
		addCommands(

			// replace line below by whatever trajectory-generation command you want to test
			//new StartingPositionTwoDriveAndTurnToLeftNote(drivetrain, container, object_detection_camera)

			// another example, calling a trajectory direcly:
			new DrivetrainSwerveRelative(drivetrain, container, StartingPositionOnePickupSecondNote.createPickupSecondNoteTrajectory(container))
		); 
  
	}
}