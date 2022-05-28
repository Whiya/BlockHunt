package tokyo.ramune.blockhunt.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.User;

public class PlayerSneakListener implements Listener {

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);
        if (e.isSneaking()
                && user.getRole().equals(Role.RUNNER)
                && !user.isBlockHiding()) {
            PlayerManager.hideBlock(player, user.getHidingBlock().getBlockData());
        }
    }
}
