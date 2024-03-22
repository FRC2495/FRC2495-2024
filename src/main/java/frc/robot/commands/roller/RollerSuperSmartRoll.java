package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.sensors.*;
import frc.robot.subsystems.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class RollerSuperSmartRoll extends SequentialCommandGroup {

    public RollerSuperSmartRoll(Roller roller, NoteSensor notesensor, NoteSensor noteSensorTwo){

        addCommands(

			new RollerRollLowRpmUntilNoteSensed(roller, notesensor, noteSensorTwo),

            new WaitCommand(0.5), // we wait for things to settle down

            new RollerReleaseShortDistance(roller)
            
        ); 
  
    }


}