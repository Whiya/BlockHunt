package tokyo.ramune.blockhunt.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.blockhunt.player.PlayerManager;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuitListener(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        PlayerManager.removePlayer(player);
    }
}
