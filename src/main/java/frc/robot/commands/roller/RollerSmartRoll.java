package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.sensors.*;
import frc.robot.subsystems.*;


// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class RollerSmartRoll extends SequentialCommandGroup {

    public RollerSmartRoll(Roller roller, NoteSensor notesensor){

        addCommands(

			new RollerRollUntilNoteSensed(roller, notesensor),

            new RollerTimedRelease(roller, 0.1)
            
        ); 
  
    }


}