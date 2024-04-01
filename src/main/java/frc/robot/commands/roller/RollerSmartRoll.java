package frc.robot.commands.roller;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.sensors.*;
import frc.robot.subsystems.*;

public class RollerSmartRoll extends SequentialCommandGroup {

    public RollerSmartRoll(Roller roller, NoteSensor notesensor, NoteSensor noteSensorTwo){

        addCommands(

			new RollerRollUntilNoteSensed(roller, notesensor, noteSensorTwo),

            new RollerTimedRelease(roller, 0.1)
            
        ); 
  
    }


}