package tokyo.ramune.blockhunt.game;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.blockhunt.game.item.runner.HideBlockSelectItem;
import tokyo.ramune.blockhunt.game.item.runner.HoldBlockItem;

public class GameItemHandler {

    private static final GameItem[] gameItems = {new HideBlockSelectItem(), new HoldBlockItem()};

    public static GameItem[] getGameItems() {
        return gameItems;
    }

    public static void onItemUse(PlayerInteractEvent e) {
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        for (GameItem item : getGameItems()) {
            if (item.getGameItem().equals(itemStack)) {
                item.onInteract(e);
            }
        }
    }
}
