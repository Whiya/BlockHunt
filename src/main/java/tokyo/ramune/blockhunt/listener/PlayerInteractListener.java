package tokyo.ramune.blockhunt.listener;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;
import tokyo.ramune.blockhunt.util.Chat;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                && !Objects.equals(e.getHand(), EquipmentSlot.OFF_HAND)
                && user.getRole().equals(Role.RUNNER)
                && !user.isBlockHiding()) {
            Chat.sendMessage(player, ChatColor.GREEN + "隠れるブロックを選択しました!", true);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 15, 0.3, 0.3, 0.3, 0);
            user.setHidingBlock(player.getWorld().spawnFallingBlock(player.getLocation(), e.getClickedBlock().getBlockData()));
            user.getHidingBlock().setGravity(false);
            user.getHidingBlock().setFallDistance(0);
        }
    }
}
