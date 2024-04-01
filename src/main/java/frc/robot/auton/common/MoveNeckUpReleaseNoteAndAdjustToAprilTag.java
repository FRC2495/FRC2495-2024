package frc.robot.auton.common;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.neck.*;
import frc.robot.commands.roller.RollerReleaseShortDistance;
import frc.robot.subsystems.*;
import frc.robot.interfaces.*;

public class MoveNeckUpReleaseNoteAndAdjustToAprilTag extends SequentialCommandGroup {

	public MoveNeckUpReleaseNoteAndAdjustToAprilTag( Neck neck, Roller roller, ICamera apriltag_camera) {

		addCommands(

			new NeckMoveSubWithStallDetection(neck),

			//new RollerReleaseShortDistance(roller),

			new NeckMoveUsingCamera(neck, apriltag_camera)
						
		); 
  
	}
   
}