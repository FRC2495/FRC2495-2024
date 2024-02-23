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
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneThreeNote extends SequentialCommandGroup {

    public StartingPositionOneThreeNote(RobotContainer container, Elevator elevator, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera camera, NoteSensor notesensor){

        addCommands(

            new NeckMoveSubWithStallDetection(neck),

            new ShootNote(shooter, roller),

            new NeckMoveDownWithStallDetection(neck),

            new DrivetrainTurnUsingCamera(drivetrain, camera),
            
			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 145, 2),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootPreloadTrajectory(container)),

            new StartingPositionOnePickupSecondNote(container, drivetrain, roller, notesensor),

            new NeckMovePodiumWithStallDetection(neck), // check to see if this works later 

			new DrivetrainTurnUsingCamera(drivetrain, camera), // change to april tag camera command later 

            new ShootNote(shooter, roller),

			//new DrivetrainTimedTurnUsingPIDController(drivetrain, 65, 2),

            new NeckMoveDownWithStallDetection(neck),

            new StartingPositionOnePickupThirdNote(container, drivetrain, roller, camera, notesensor),

            new NeckMovePodiumWithStallDetection(neck),

			new StartingPositionOneShootThirdNote(container, drivetrain, camera),

            //new DrivetrainSwerveRelative(drivetrain, container, createShootSecondNoteTrajectory(container)

			new ShootNote(shooter, roller)

        ); 
  
    }

    
    
    


}