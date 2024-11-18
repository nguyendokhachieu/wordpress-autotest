package actions.utilities;

public class JavaHelper {
    public static void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
