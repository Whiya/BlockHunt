package tokyo.ramune.blockhunt.game;

import org.bukkit.Bukkit;
import tokyo.ramune.blockhunt.BlockHunt;

public class GameRunnable {

    public static void createCountdownTimer() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(BlockHunt.getPlugin(), () -> {
            if (!(GameHandler.getReadyTime() <= 0)) {
                GameHandler.setReadyTime(GameHandler.getReadyTime() - 1);
            } else {

            }
            if (!(GameHandler.getTime() <= 0)) {
                GameHandler.setTime(GameHandler.getTime() - 1);
            } else {
                GameHandler.stop();
            }
        }, 0, 20);
    }
}
