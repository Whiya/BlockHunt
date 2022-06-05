package tokyo.ramune.blockhunt.menu.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.blockhunt.menu.MenuItem;

import java.util.ArrayList;

public class MenuCloseItem implements MenuItem {


    @Override
    public ItemStack getMenuItem() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.YELLOW.toString());
        lore.add(ChatColor.YELLOW + "右クリックで閉じる");
        itemMeta.setDisplayName(ChatColor.RED + "メニューを閉じる");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getItemSlot() {
        return 22;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        player.closeInventory();
    }
}
