package tokyo.ramune.blockhunt.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.config.ConfigHandler;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;
import tokyo.ramune.blockhunt.util.Chat;

public class GameHandler {

    private static boolean status = false;
    private static GameMode selectedGameMode = GameMode.RANDOM;
    private static int readyTime = 30;
    private static int time = 120;
    private static int daemonAmount = 1;

    public static void start() {
        if (status) {
            return;
        }
        status = true;
        Bukkit.getScheduler().runTaskAsynchronously(BlockHunt.getPlugin(), () -> {
           try {
               // ---counting start---
               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "ゲーム開始まで", true);
                   Chat.sendMessage(player, ChatColor.GREEN + "3...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "3...");
               }
               Thread.sleep(1000);

               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "2...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "2...");
               }
               Thread.sleep(1000);

               for (Player player : Bukkit.getOnlinePlayers()) {
                   player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                   Chat.sendMessage(player, ChatColor.GREEN + "1...", true);
                   player.sendTitle(" ", ChatColor.GREEN + "1...");
               }
               Thread.sleep(1000);

               // ---counting end---

               // initialize players
               for (Player player : Bukkit.getOnlinePlayers()) {
                   User user = PlayerManager.getPlayer(player);

                   Bukkit.getScheduler().runTask(BlockHunt.getPlugin(), () -> PlayerManager.initializeGameMode(player));
                   // runner init
                   if (user.getRole().equals(Role.NONE)) {
                       user.setRole(Role.RUNNER);
                       Bukkit.getScheduler().runTask(BlockHunt.getPlugin(), () -> player.teleport(ConfigHandler.START_LOCATION));
                   }
                   player.setHealth(player.getHealthScale());
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        });
    }

    public static void stop() {
        if (!status) {
            return;
        }
        status = false;
        for (Player player : Bukkit.getOnlinePlayers()) {
            User user = PlayerManager.getPlayer(player);
            player.teleport(ConfigHandler.SPAWN_LOCATION);
            user.setRole(Role.NONE);
        }
    }

    public static GameMode getSelectedGameMode() {
        return selectedGameMode;
    }

    public static void setSelectedGameMode(GameMode gamemode) {
        selectedGameMode = gamemode;
    }

    public static int getReadyTime() {
        return readyTime;
    }

    public static void setReadyTime(int readyTime) {
        GameHandler.readyTime = readyTime;
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
