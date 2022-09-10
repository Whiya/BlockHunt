package tokyo.ramune.blockhunt.game.item;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class GameItemListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack handItem = Objects.requireNonNull(event.getItem());
        if (handItem.equals(GameItemResource.getHide().getItemStack())) {
            //隠れる
            GameItemResource.getHide().onClick(event);
        } else if (handItem.equals(GameItemResource.getSelectHideBlock().getItemStack())) {
            //ブロックの選択
            GameItemResource.getSelectHideBlock().onClick(event);
        } else if (handItem.equals(GameItemResource.getSelectDaemonStick().getItemStack())) {
            //お前鬼な棒
            GameItemResource.getSelectDaemonStick().onClick(event);
        } else if (handItem.equals(GameItemResource.getGoldStick().getItemStack())) {
            //おまえ殺す
            GameItemResource.getGoldStick().onClick(event);
        } else if (handItem.equals(GameItemResource.getSelectRunnerStick().getItemStack())) {
            //お前逃げな棒
            GameItemResource.getSelectRunnerStick().onClick(event);
        }else if(handItem.equals(GameItemResource.template().getItemStack())){
            //テンプレート
            GameItemResource.template().onClick(event);
        }
    }
}
