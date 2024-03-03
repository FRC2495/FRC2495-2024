package frc.robot.auton.common;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;

import frc.robot.auton.sp1.*;
import frc.robot.auton.sp2.*;
import frc.robot.auton.sp3.*;
import frc.robot.auton.sp4.*;
import frc.robot.auton.sp6.*;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class TrajectoryGenerationTest extends SequentialCommandGroup {
	
	public TrajectoryGenerationTest(SwerveDrivetrain drivetrain, RobotContainer container, ICamera object_detection_camera, ICamera apriltag_camera) {
		
		addCommands(

			// replace line below by whatever trajectory-generation command you want to test
			new StartingPositionTwoDriveAndTurnToLeftNote(drivetrain, container, object_detection_camera)
		); 
  
	}

}