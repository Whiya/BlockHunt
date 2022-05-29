package tokyo.ramune.blockhunt.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.User;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuitListener(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);

        // 隠れブロック & プレイヤーの削除
        if (user.isHiding()) {
            player.getLocation().getBlock().setType(Material.AIR);
        }
        PlayerManager.removePlayer(player);
    }
}
