package tokyo.ramune.blockhunt.menu.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.menu.MenuItem;

import java.util.ArrayList;

public class MenuStartGameItem implements MenuItem {
    @Override
    public ItemStack getMenuItem() {
        ItemStack itemStack;
        if (!GameHandler.isStarted()) {
            // ゲームをスタートするときのItemStack
            itemStack = new ItemStack(Material.GREEN_WOOL);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();

            lore.add(ChatColor.YELLOW.toString());
            lore.add(ChatColor.YELLOW + "右クリックで開始");
            itemMeta.setDisplayName(ChatColor.GREEN + "ゲームを開始する");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        } else {
            // ゲームをストップするときのItemStack
            itemStack = new ItemStack(Material.RED_WOOL);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();

            lore.add(ChatColor.YELLOW.toString());
            lore.add(ChatColor.YELLOW + "右クリックで停止");
            itemMeta.setDisplayName(ChatColor.RED + "ゲームを停止する");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    @Override
    public int getItemSlot() {
        return 20;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
            // ゲームスタート
            GameHandler.start();
        } else {
            // ゲームストップ
            GameHandler.stop();
        }
        player.closeInventory();
    }
}
