package tokyo.ramune.blockhunt.game;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface GameItem {

    ItemStack getGameItem();

    int getItemSlot();

    void onInteract(PlayerInteractEvent e);
}
