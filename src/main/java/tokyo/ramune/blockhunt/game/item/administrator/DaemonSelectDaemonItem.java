package tokyo.ramune.blockhunt.game.item.administrator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.blockhunt.game.GameItem;

import java.util.ArrayList;

public class DaemonSelectDaemonItem implements GameItem {
    @Override
    public ItemStack getGameItem() {
        ItemStack itemStack = new ItemStack(Material.BONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.AQUA + "お前鬼な棒 -" + ChatColor.YELLOW + " プレイヤーを右クリックで鬼に指定");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getItemSlot() {
        return 0;
    }

    @Override
    public void onInteract(PlayerInteractEvent e) {

    }
}
