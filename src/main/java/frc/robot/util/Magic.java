package frc.robot.util;

public class Magic {
    
    public static double getRpm(double verticalOffset)
    {
        return 2.79 * verticalOffset * verticalOffset - 42.07 * verticalOffset + 3374.3;
    }
}
