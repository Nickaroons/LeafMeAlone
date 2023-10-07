package DataTypes;

public class Time {
    public static double deltaTime = ((System.nanoTime() - Time.lastUpdateTime)/1000000000.0);;
    public static long lastUpdateTime = System.nanoTime();
}
