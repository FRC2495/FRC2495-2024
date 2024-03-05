package frc.robot.auton.sp3;

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
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.interfaces.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionThreeThreeNote extends SequentialCommandGroup {

    public StartingPositionThreeThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor){

        addCommands(

			new NeckHome(neck),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, -25, 2),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

			new NeckMoveSubWithStallDetection(neck),

			new ShootNote(shooter, roller),

			new StartingPositionThreePickupSecondNote(container, drivetrain, roller, notesensor),

			new DrivetrainTurnUsingCamera(drivetrain, apriltag_camera), // change to april tag command

			//new StartingPositionThreeShootSecondNote(container, drivetrain, camera),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new NeckMovePodiumWithStallDetection(neck), // check to see if this works later 

			new ShootNote(shooter, roller),

			new StartingPositionThreePickupThirdNote(container, drivetrain, roller, notesensor),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),
			new NeckMovePodiumWithStallDetection(neck),
			
			new StartingPositionThreeDriveShootThirdNote(container, drivetrain, apriltag_camera),
			
			new ShootNote(shooter, roller)

        ); 
  
    }





}