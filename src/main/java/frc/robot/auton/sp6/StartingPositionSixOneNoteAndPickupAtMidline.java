package frc.robot.auton.sp6;

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
import frc.robot.commands.neck.NeckMoveDownWithStallDetection;
import frc.robot.commands.neck.NeckMoveOptimalPositionForShooting;
import frc.robot.commands.neck.NeckMovePodiumWithStallDetection;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.interfaces.*;
import frc.robot.subsystems.*;
import frc.robot.auton.sp1.*;
import frc.robot.sensors.*;


public class StartingPositionSixOneNoteAndPickupAtMidline extends SequentialCommandGroup {

    public StartingPositionSixOneNoteAndPickupAtMidline(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

        addCommands(

			new StartingPositionSixOneNoteAndLeave(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor),

			new StartingPositionSixPickupMidlineNote(container, drivetrain, object_detection_camera, roller, notesensor, noteSensorTwo)

        ); 
  
    }

}