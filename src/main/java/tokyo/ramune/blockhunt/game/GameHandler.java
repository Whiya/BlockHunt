package tokyo.ramune.blockhunt.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.config.ConfigHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class GameHandler {

    private static boolean status = false;
    private static GameMode selectedGameMode = GameMode.RANDOM;
    private static int time = 120;
    private static int daemonAmount = 1;

    public static void start() {
        if (status) {
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(BlockHunt.getPlugin(), () -> {
           try {
               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "ゲーム開始まで", true);
                   Chat.sendMessage(player, ChatColor.GREEN + "3...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "3...");
               }
               Thread.sleep(1);

               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "2...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "2...");
               }
               Thread.sleep(1);

               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "1...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "1...");
               }
               Thread.sleep(1);

               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.teleport(ConfigHandler.START_LOCATION);
               }
           } catch (Exception ignored) {
           }
        });
    }

    public static void stop() {
        if (!status) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(ConfigHandler.SPAWN_LOCATION);
        }
    }

    public static GameMode getSelectedGameMode() {
        return selectedGameMode;
    }

    public static void setSelectedGameMode(GameMode gamemode) {
        selectedGameMode = gamemode;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        GameHandler.time = time;
        BlockHunt.getBossbar().setbossbarTime(time);
    }

    public static int getDaemonAmount() {
        return daemonAmount;
    }

    public static void setDaemonAmount(int daemonAmount) {
        GameHandler.daemonAmount = daemonAmount;
    }

    public static boolean isStarted() {
        return status;
    }
}
