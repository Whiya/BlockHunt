package tokyo.ramune.blockhunt.game.item.runner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.blockhunt.game.GameItem;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;

import java.util.ArrayList;
import java.util.Objects;

public class HoldBlockItem implements GameItem {
    @Override
    public ItemStack getGameItem() {
        ItemStack itemStack = new ItemStack(Material.BONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        itemMeta.setDisplayName(ChatColor.AQUA + "ブロックを固定化  -" + ChatColor.YELLOW + "  右クリックで現在の位置に固定/解除");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getItemSlot() {
        return 1;
    }

    @Override
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);

        if (!((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))
                && Objects.equals(e.getHand(), EquipmentSlot.HAND)
                && user.getRole().equals(Role.RUNNER))) {
            return;
        }
        if (user.isHiding()){
            PlayerManager.holdBlock(player,user.getTargetBlock());
        }else{
            PlayerManager.removeHoldBlock(player,user.getHoldBlockLocation());
        }
    }
}
