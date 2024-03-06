package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;

import frc.robot.auton.common.*;
import frc.robot.auton.sp1.*;
import frc.robot.auton.sp2.*;
import frc.robot.auton.sp3.*;
import frc.robot.auton.sp4.*;
import frc.robot.auton.sp6.*;

import frc.robot.commands.drivetrain.DrivetrainSwerveRelative;

// The purpose of this class is to help testing trajectories at home in simulation mode
public class TrajectoryGenerationTest extends SequentialCommandGroup {
	
	public TrajectoryGenerationTest(SwerveDrivetrain drivetrain, RobotContainer container, ICamera object_detection_camera, ICamera apriltag_camera) {
		
		addCommands(

			// replace line below by whatever trajectory-generation command you want to test:
			//new StartingPositionTwoDriveAndTurnToLeftNote(drivetrain, container, object_detection_camera)

			//new MoveIn8Shape(drivetrain, container, 3)

			//new MoveInInfinityShape(drivetrain, container, 3)


			//new MoveForwardAndHardLeft(drivetrain, container, 3, 1)

			//new MoveForwardAndHardRight(drivetrain, container, 3, 1)

			//new MoveInReverseAndHardLeft(drivetrain, container, 3, 1)
			
			//new MoveInReverseAndHardRight(drivetrain, container, 3, 1)


			//new MoveForwardAndLeft(drivetrain, container, 3, 1 , 45)

			//new MoveForwardAndRight(drivetrain, container, 3, 1, -45)

			//new MoveInReverseAndLeft(drivetrain, container, 3, 1, 135)
			
			//new MoveInReverseAndRight(drivetrain, container, 3, 1, -135)


			//new MoveInGammaShape(drivetrain, container, 3)

			//new MoveInLShapeInReverse(drivetrain, container, 3)


			// another example, calling a method that generates a trajectory direcly:
			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionOnePickupSecondNote.createPickupSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionOnePickupThirdNote.createPickupThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionOneScoreTwoPickupThreeNote.createShootThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionOneThreeNote.createShootThirdNoteTrajectory(container))


			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoDriveAndTurnToLeftNote.createPickupLeftThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoDriveAndTurnToLeftNote.createMoveForwardTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoDriveAndTurnToRightNote.createPickupRightThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoDriveAndTurnToRightNote.createMoveForwardTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoLeftThreeNote.createShootSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoLeftThreeNote.createShootThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoLeftThreeNote.createLeaveAfterShootLeftThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoPickupLeftThirdNote.createPickupLeftThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoPickupRightThirdNote.createPickupRightThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoPickupSecondNote.createPickupSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoRightThreeNote.createShootSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoRightThreeNote.createShootThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoRightThreeNote.createLeaveAfterShootRightThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoShootLeftThirdNote.createShootThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoTwoNote.createShootSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionTwoTwoNote.createLeaveAfterShootSecondNoteTrajectory(container))


			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionThreeDrivePickupSecondNote.createPickupSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionThreeThreeNote.createShootSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionThreeDrivePickupThirdNote.createPickupThirdNoteTrajectory(container))

			new DrivetrainSwerveRelative(drivetrain, container, StartingPositionThreeShootThirdNote.createShootThirdNoteTrajectory(container))


			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionFourPickupSecondNote.createPickupSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionFourPickupThirdNote.createPickupThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionFourThreeNote.createShootThirdNoteTrajectory(container))


			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionSixPickupSecondNote.createPickupSecondNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionSixPickupThirdNote.createPickupThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionSixThreeNote.createAreaBeforeThirdNotePickupTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionSixThreeNote.createAreaBeforeShootThirdNoteTrajectory(container))

			//new DrivetrainSwerveRelative(drivetrain, container, StartingPositionSixThreeNote.createShootThirdNoteTrajectory(container))
		); 
  
	}
}