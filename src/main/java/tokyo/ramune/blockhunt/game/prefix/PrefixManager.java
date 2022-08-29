package tokyo.ramune.blockhunt.game.prefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import tokyo.ramune.blockhunt.game.player.HuntPlayer;

public class PrefixManager {
    public PrefixManager() {
    }

    public void applyPrefix(HuntPlayer huntPlayer) {
                huntPlayer.getPlayer().setDisplayName("[" + huntPlayer.getRole().getColor() + huntPlayer.getRole().toString() + ChatColor.WHITE + "] " + huntPlayer.getRole().getColor() + huntPlayer.getPlayer().getName());
                huntPlayer.getPlayer().setPlayerListName("[" + huntPlayer.getRole().getColor() + huntPlayer.getRole().toString() + ChatColor.WHITE + "] " + huntPlayer.getRole().getColor() + huntPlayer.getPlayer().getName());
    }
}
