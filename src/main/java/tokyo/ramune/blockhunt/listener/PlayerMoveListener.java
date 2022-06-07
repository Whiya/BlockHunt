package tokyo.ramune.blockhunt.listener;

import org.bukkit.Location;
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

        // ブロックに隠れていたらうげけなくする(x, y, z)
        if (user.isHiding()) {
            e.setCancelled(true);
        }
        
        //隠れ中じゃなければ
        if (!user.isHiding()){
            //FallingBlock が指定されてなかったら
            if (user.getFallingBlock() == null ){
                return;
            }
            
            //追尾
            FallingBlock old_fb = user.getFallingBlock();
            old_fb.remove();

            FallingBlock fb = player.getWorld().spawnFallingBlock(player.getLocation(),user.getTargetBlock().getBlockData());
            fb.spawnAt(player.getLocation());
            fb.setGravity(false);

            user.setFallingBlock(fb);
        }
    }
}
