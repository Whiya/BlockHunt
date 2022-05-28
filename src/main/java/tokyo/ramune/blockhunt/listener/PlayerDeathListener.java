package tokyo.ramune.blockhunt.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import tokyo.ramune.blockhunt.config.ConfigHandler;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (PlayerManager.getPlayer(player).getRole().equals(Role.RUNNER)) {
            User user = PlayerManager.getPlayer(player);
            if (user.isBlockHiding()) {
                player.getLocation().getBlock().setType(Material.AIR);
            } else {
                user.getHidingBlock().remove();
                user.setHidingBlock(null);
            }
            user.setRole(Role.DAEMON);
        }
        player.spigot().respawn();
        player.teleport(ConfigHandler.START_LOCATION);
    }
}
