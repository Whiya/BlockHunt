package tokyo.ramune.blockhunt.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import tokyo.ramune.blockhunt.game.GameItemHandler;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        GameItemHandler.onItemUse(e);
    }
}
