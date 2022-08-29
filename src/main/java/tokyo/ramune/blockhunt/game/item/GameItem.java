package tokyo.ramune.blockhunt.game.item;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface GameItem {
    ItemStack getItemStack();

    void onClick(PlayerInteractEvent var1);
}
