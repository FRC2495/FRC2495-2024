package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.RobotContainer;
import frc.robot.commands.drawer.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.roller.*;


public class DropTopCube extends SequentialCommandGroup{
    
    public DropTopCube(RobotContainer container) {

        addCommands(
            
            new ElevatorMoveUpWithStallDetection(null),

            new DrawerExtendWithStallDetection(null),

            new RollerRelease(null)
        
        );


    }

}