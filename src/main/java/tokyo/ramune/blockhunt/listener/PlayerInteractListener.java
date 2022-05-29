package tokyo.ramune.blockhunt.listener;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.Role;
import tokyo.ramune.blockhunt.player.RunnerItem;
import tokyo.ramune.blockhunt.player.User;
import tokyo.ramune.blockhunt.util.Chat;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        User user = PlayerManager.getPlayer(player);


        /*TODO
               右クリック時の条件まとめ




         */

        //右クリック　かつ　ロールがランナー　かつ　
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                && !Objects.equals(e.getHand(), EquipmentSlot.HAND)
                && user.getRole().equals(Role.RUNNER)
                && !user.isHiding()) {
            //右クリックしたときの手持ちが RunnerItemのだったら
            if (player.getInventory().getItemInHand().equals(RunnerItem.getHoldBlock())){
                if (user.isHiding()){
                    PlayerManager.holdBlock(player,user.getTargetBlock());
                }else{
                    PlayerManager.removeHoldBlock(player,user.getHoldBlockLocation());
                }

            }

            Chat.sendMessage(player, ChatColor.GREEN + "隠れるブロックを選択しました!", true);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

            if (user.getTargetBlock() == null){
                user.setTargetBlock(e.getClickedBlock());

                FallingBlock fb = player.getWorld().spawnFallingBlock(player.getLocation(),user.getTargetBlock().getBlockData());
                fb.spawnAt(player.getLocation());
                fb.setGravity(false);

                user.setFallingBlock(fb);
            }else {
                FallingBlock old_fb = user.getFallingBlock();
                old_fb.remove();

                user.setTargetBlock(e.getClickedBlock());

                FallingBlock fb = player.getWorld().spawnFallingBlock(player.getLocation(),user.getTargetBlock().getBlockData());
                fb.spawnAt(player.getLocation());
                fb.setGravity(false);

                user.setFallingBlock(fb);
            }
        }
    }
}
