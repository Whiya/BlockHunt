package tokyo.ramune.blockhunt.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Chat {
    public Chat() {
    }

    public static void sendMessage(@NonNull Player player, String message, boolean addPrefix) {
        if (addPrefix) {
            player.sendMessage(ChatColor.AQUA + "[" + ChatColor.GREEN + "ブロックハント" + ChatColor.AQUA + "] " + ChatColor.RESET + message);
        } else {
            player.sendMessage(message);
        }

    }
}