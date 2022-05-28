package tokyo.ramune.blockhunt.game;

import org.bukkit.Bukkit;

public class GameHandler {

    private static boolean status = false;
    private static int time = 0;

    public static void start() {
        if (status) {
            return;
        }

    }

    public static void stop() {
        if (!status) {
            return;
        }

    }

    // TODO
    public static int getTime() {
        return time;
    }

    public static void setTime(int _time) {
        time = _time;
    }

    public static boolean isStarted() {
        return status;
    }
}
