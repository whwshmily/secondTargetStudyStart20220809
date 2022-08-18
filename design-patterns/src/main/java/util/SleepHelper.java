package util;

public class SleepHelper {
    private SleepHelper() {
    }

    public static void sleepSecond(double seconds) {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
