package tokyo.ramune.blockhunt.game.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.blockhunt.BlockHunt;

class PlayerManagerListener implements Listener {
    PlayerManagerListener() {
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        BlockHunt.getPlayerManager().initializePlayer(player);

        //ゲーム開始していなかったら参加したときは Spawn_locationに
        if (!(BlockHunt.getGameManager().isStarted())){
            player.teleport(BlockHunt.getConfigFile().SPAWN_LOCATION);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        BlockHunt.getPlayerManager().removeHuntPlayer(player);
        int huntPlayerCount = BlockHunt.getPlayerManager().getHuntPlayers().size();
        int daemonCount = BlockHunt.getGameManager().getDaemonCount();
        if (huntPlayerCount - daemonCount < 0 || daemonCount > 0) {
            BlockHunt.getGameManager().setDaemonCount(daemonCount - 1);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        HuntPlayer player = BlockHunt.getPlayerManager().getHuntPlayer(event.getPlayer());

    }
}
