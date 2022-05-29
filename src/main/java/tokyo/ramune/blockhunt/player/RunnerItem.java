package tokyo.ramune.blockhunt.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RunnerItem {

    public static ItemStack getHoldBlock() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.AQUA + "ブロックを固定化  -" + ChatColor.YELLOW + "  右クリックで現在の位置に固定化");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getTargetingBlock() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.AQUA + "ブロックを選択  -" + ChatColor.YELLOW + "  右クリックでブロックを選択");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
