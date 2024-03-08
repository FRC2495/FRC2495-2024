package frc.robot.auton.common;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.neck.NeckHome;
import frc.robot.commands.neck.NeckMoveSubWithStallDetection;
import frc.robot.commands.roller.RollerTimedRoll;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter.ShooterTimedShootHighNoStop;
import frc.robot.subsystems.*;

public class JustShootNote extends SequentialCommandGroup {

	public JustShootNote( Neck neck, Shooter shooter, Roller roller) {

		addCommands(

			new NeckHome(neck),

			new NeckMoveSubWithStallDetection(neck),

			new ShooterTimedShootHighNoStop(shooter, 0.5),

			new RollerTimedRoll(roller, .2)
						
		); 
  
	}
   
}