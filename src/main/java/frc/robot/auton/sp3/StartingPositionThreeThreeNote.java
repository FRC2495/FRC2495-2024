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
import frc.robot.interfaces.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionThreeThreeNote extends SequentialCommandGroup {

    public StartingPositionThreeThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera camera, NoteSensor notesensor){

        addCommands(

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, -25, 2),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

			new StartingPositionThreeShootFirstNote(container, drivetrain, camera),

            new ShooterTimedShootHigh(shooter, 0.5),

			new DrivetrainTimedTurnUsingPIDController(drivetrain, 35, .2),

			new StartingPositionThreePickupSecondNote(container, drivetrain, roller, notesensor),

			new StartingPositionThreeShootSecondNote(container, drivetrain, camera),

			//new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShooterTimedShootHigh(shooter, 0.5),

			new StartingPositionThreePickupThirdNote(container, drivetrain, roller, notesensor),


			//new DrivetrainSwerveRelative(drivetrain, container, createShootThirdNoteTrajectory(container)),

			new StartingPositionThreeShootThirdNote(container, drivetrain, camera),

			new ShooterTimedShootHigh(shooter, 0.5)

        ); 
  
    }





}