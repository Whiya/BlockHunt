package tokyo.ramune.blockhunt.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {

    ItemStack getMenuItem();

    int getItemSlot();

    void onClick(InventoryClickEvent event);
}
