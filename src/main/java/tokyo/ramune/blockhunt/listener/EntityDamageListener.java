package tokyo.ramune.blockhunt.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import tokyo.ramune.blockhunt.player.PlayerManager;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (!e.getEntityType().equals(EntityType.FALLING_BLOCK)) {
            return;
        }
        FallingBlock fb = (FallingBlock) e.getEntity();
        try {
            Bukkit.getPlayer(PlayerManager.getPlayerFromFallingBlock(fb).getPlayer()).damage(e.getDamage());
        } catch (Exception ignored) {
        }
    }
}
