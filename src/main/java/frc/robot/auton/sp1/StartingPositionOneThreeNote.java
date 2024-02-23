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
import frc.robot.commands.shooter.*;
import frc.robot.commands.mouth.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.interfaces.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneThreeNote extends SequentialCommandGroup {

    public StartingPositionOneThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera camera){

        addCommands(

        
			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 145, 2),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

			new StartingPositionOneShootFirstNote(container, drivetrain, camera),

            new ShooterTimedShootHigh(shooter, 0.5), // will have to change in some way to compensate for the distance

			new DrivetrainTimedTurnUsingPIDController(drivetrain, -35, 2),

            new StartingPositionOnePickupSecondNote(container, drivetrain, roller),

			new DrivetrainTurnUsingCamera(drivetrain, camera), // test to see if it works without timer
            
            new ShooterTimedShootHigh(shooter, 0.5), // will have to change in some way to compensate for the distance

			new DrivetrainTimedTurnUsingPIDController(drivetrain, 65, 2),

            new StartingPositionOnePickupThirdNote(container, drivetrain, roller),

			new StartingPositionOneShootThirdNote(container, drivetrain, camera),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)),

			new ShooterTimedShootHigh(shooter, 0.5) // will have to change in some way to compensate for the distance

        ); 
  
    }

    
    
    


}