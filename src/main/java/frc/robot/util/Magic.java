package frc.robot.util;

public class Magic {
	
	public static double getEncoderCounts(double distance)
	{
		return -(-6.8622*distance*distance+1542.1*distance-17642);
	}
}
