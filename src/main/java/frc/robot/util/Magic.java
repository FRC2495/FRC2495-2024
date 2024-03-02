package frc.robot.util;

public class Magic {
    
    public static double getEncoderCounts(double distance)
    {
        return -(869 * distance - 6359);
    }
}
