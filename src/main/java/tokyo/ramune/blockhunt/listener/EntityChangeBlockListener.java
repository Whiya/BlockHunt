package tokyo.ramune.blockhunt.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlockListener implements Listener {

    @EventHandler
    public void onBlockPlace(EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }
}
