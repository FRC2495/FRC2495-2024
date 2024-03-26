/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.auton.common.JustShootNote;
import frc.robot.auton.sp1.*;
import frc.robot.auton.sp2.*;
import frc.robot.auton.sp3.*;
import frc.robot.auton.sp4.*;
import frc.robot.auton.sp6.StartingPositionSixOneNoteAndPickupAtMidline;
import frc.robot.auton.sp6.StartingPositionSixOneNoteAndPickupAtMidlineAndFeed;
import frc.robot.auton.sp6.StartingPositionSixThreeNote;
import frc.robot.auton.sp6.StartingPositionSixTwoNoteAtMidline;
import frc.robot.interfaces.*;
//import frc.robot.auton.common.*;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;

public class CustomAuton extends SequentialCommandGroup {

	String gamePiece;
	String startPosition;
	String mainTarget;
	String cameraOption;
	String sonarOption;
	String autonOption;

	/**
	 * Add your docs here.
	 * 
	 * @param gamePiece_in game piece
	 * @param startPosition_in starting position
	 * @param mainTarget_in    main target
	 * @param cameraOption_in  camera option
	 * @param sonarOption_in   sonar option
	 * @param autonOption_in   auton option
	*/
	public CustomAuton(String gamePiece_in, String startPosition_in, String mainTarget_in, String cameraOption_in,
			String sonarOption_in, String autonOption_in, SwerveDrivetrain drivetrain, RobotContainer container,
			Elevator elevator, Roller roller, Neck neck, Shooter shooter, ICamera object_detection_camera, ICamera apriltag_camera, NoteSensor notesensor, NoteSensor noteSensorTwo) {

		gamePiece = gamePiece_in;
		startPosition = startPosition_in;
		mainTarget = mainTarget_in;
		cameraOption = cameraOption_in;
		sonarOption = sonarOption_in;
		autonOption = autonOption_in;

		switch (startPosition) {
			case RobotContainer.START_POSITION_1:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionOneOneNoteAndPickupAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, 
								notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_FEED_NOTE:
								addCommands(new StartingPositionOneOneNoteAndPickupAtMidlineAndFeed(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionOneTwoNoteAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionOneThreeNote(container, elevator, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
			case RobotContainer.START_POSITION_2:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								//todo
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								addCommands(new StartingPositionTwoTwoNote(container, drivetrain, roller, shooter, neck, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionTwoLeftThreeNote(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								addCommands(new StartingPositionTwoRightThreeNote(container, drivetrain, roller, shooter, neck, notesensor, noteSensorTwo, apriltag_camera));
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
			case RobotContainer.START_POSITION_3:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionThreeOneNoteAndPickupAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_FEED_NOTE:
								addCommands(new StartingPositionThreeOneNoteAndPickupAtMidlineAndFeed(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionThreeTwoNoteAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionThreeThreeNote(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
			case RobotContainer.START_POSITION_4:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionFourOneNoteAndPickupAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_FEED_NOTE:
								addCommands(new StartingPositionFourOneNoteAndPickupAtMidlineAndFeed(container, drivetrain, roller, shooter, neck, object_detection_camera, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionFourTwoNoteAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionFourThreeNote(container, elevator, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
			case RobotContainer.START_POSITION_5:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								addCommands(new StartingPositionTwoTwoNote(container, drivetrain, roller, shooter, neck, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionTwoLeftThreeNote(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								addCommands(new StartingPositionTwoRightThreeNote(container, drivetrain, roller, shooter, neck, notesensor, noteSensorTwo, object_detection_camera));
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
				
			case RobotContainer.START_POSITION_6:
				switch (gamePiece) {
					case RobotContainer.GAME_PIECE_1_NOTE:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								addCommands(new JustShootNote(neck, shooter, roller));
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionSixOneNoteAndPickupAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_FEED_NOTE:
								addCommands(new StartingPositionSixOneNoteAndPickupAtMidlineAndFeed(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_2_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionSixTwoNoteAtMidline(container, drivetrain, roller, shooter, neck, object_detection_camera, apriltag_camera, notesensor, noteSensorTwo));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
					case RobotContainer.GAME_PIECE_3_NOTES:
						switch (autonOption) {
							case RobotContainer.AUTON_OPTION_JUST_SHOOT_NOTE:
								//TODO
								break;
							case RobotContainer.AUTON_OPTION_LEAVE_COMMUNITY:
								//addCommands(new DropTopCubeAndShrink(container, elevator, drawer, roller));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_MIDLINE:
								addCommands(new StartingPositionSixThreeNote(container, drivetrain, roller, shooter, neck, notesensor, noteSensorTwo, object_detection_camera, apriltag_camera));
								break;
							case RobotContainer.AUTON_OPTION_PICKUP_NOTE_AT_WING:
								//TODO
								break;
							default:
								// nothing
								break;
						}	
						break;
				
					default:
						// nothing
						break;
						
				}
				break;
			default:{
				//nothing
				break;
			}
			

		} // end switch
	}
}