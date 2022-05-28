package tokyo.ramune.blockhunt.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.info.Bossbar;
import tokyo.ramune.blockhunt.player.PlayerManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PlayerManager.getPlayer(player);

        //ボスバーみえるか
        Bossbar bossbar = BlockHunt.getBossbar();
        bossbar.visit(player);

    }
}
