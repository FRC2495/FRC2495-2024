package frc.robot.auton.sp4;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.sp4.*;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;

// when using this path, make sure to position the robot closer to the starting line so note isnt bumped into

public class StartingPositionFourOneNoteAndPickupAtMidline extends SequentialCommandGroup {

	public StartingPositionFourOneNoteAndPickupAtMidline(RobotContainer container, SwerveDrivetrain drivetrain, Roller roller, Shooter shooter, Neck neck, ICamera object_detection_camera, NoteSensor notesensor, NoteSensor noteSensorTwo){

		addCommands(

			new StartingPositionFourOneNoteAndLeave(container, drivetrain, roller, shooter, neck),

			new StartingPositionFourPickupMidlineNote(container, drivetrain, roller, notesensor, noteSensorTwo)

		); 
  
	}
	
	
}