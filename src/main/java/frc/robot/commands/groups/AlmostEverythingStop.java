
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Elevator;
//import frc.robot.subsystems.Drawer;
import frc.robot.subsystems.Neck;
import frc.robot.subsystems.Roller;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.elevator.*;
//import frc.robot.commands.drawer.*;
import frc.robot.commands.neck.*;
import frc.robot.commands.roller.*;
import frc.robot.commands.shooter.ShooterStop;


/**
 *
 */
public class AlmostEverythingStop extends SequentialCommandGroup {

	public AlmostEverythingStop(Elevator elevator, Neck neck, Roller roller, Shooter shooter) {

		addCommands(
			new ElevatorStop(elevator),
			//new DrawerStop(drawer),
			new NeckStop(neck),
			new RollerStop(roller));
			new ShooterStop(shooter);
	} 
}
