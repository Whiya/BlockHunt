package tokyo.ramune.blockhunt.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();
        User user = PlayerManager.getPlayer(player);
        // 隠れブロック(ブロック)の削除
        if (user.getRole().equals(Role.RUNNER)
                && !(from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ())
                && user.isBlockHiding()) {
            user.setHidingBlock(player.getWorld().spawnFallingBlock(player.getLocation(), from.getBlock().getBlockData()));
            from.getBlock().setType(Material.AIR);
        }
        // 隠れブロック(エンティティ)の追尾
        try {
            FallingBlock fb = user.getHidingBlock();
            user.getHidingBlock().remove();
            user.setHidingBlock(null);
            user.setHidingBlock(player.getWorld().spawnFallingBlock(player.getLocation(), fb.getBlockData()));
            user.getHidingBlock().setGravity(false);
        } catch (Exception ignored) {
        }
    }
}
