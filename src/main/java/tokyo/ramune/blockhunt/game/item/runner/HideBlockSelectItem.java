package tokyo.ramune.blockhunt.game.item.runner;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
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
import tokyo.ramune.blockhunt.util.Chat;

import java.util.ArrayList;
import java.util.Objects;

public class HideBlockSelectItem implements GameItem {
    @Override
    public ItemStack getGameItem() {
        ItemStack itemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GOLD + "隠れるブロックを選択" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "右クリックで選択");
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
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);

        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                && Objects.equals(e.getHand(), EquipmentSlot.HAND)
                && user.getRole().equals(Role.RUNNER)
                && !user.isHiding())) {
            return;
        }
        if (user.getTargetBlock() != null) {
            FallingBlock old_fb = user.getFallingBlock();
            old_fb.remove();
        }
        user.setTargetBlock(e.getClickedBlock());

        FallingBlock fb = player.getWorld().spawnFallingBlock(player.getLocation(),user.getTargetBlock().getBlockData());
        fb.spawnAt(player.getLocation());
        fb.setGravity(false);

        user.setFallingBlock(fb);

        Chat.sendMessage(player, ChatColor.GREEN + "隠れるブロックを選択しました!", true);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
