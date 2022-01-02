package kowale.database;
import kowale.event.Event;

public class GlobalVariables {
    public static volatile String FRAME_TYPE;
    public static volatile String USER_TYPE;

    public static volatile Event EVENT;
    public static volatile int SECTORS_NUMBER;
    public static volatile int USER_ID;

    public static volatile double CHILDREN_COEF = 0.7;
    public static volatile double VIP_COEF = 1.5;
}
