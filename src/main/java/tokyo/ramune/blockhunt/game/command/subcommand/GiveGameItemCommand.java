package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.item.GameItemResource;
import tokyo.ramune.blockhunt.util.Chat;

public class GiveGameItemCommand implements SubCommand {
    public GiveGameItemCommand() {
    }

    public @NonNull String getSubCommand() {
        return "give";
    }

    public @NonNull String getDescription() {
        return "必要なアイテムを付与するよ！";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            try {
                switch (args[1]) {
                    case "omaeoni":
                        player.getInventory().addItem(new ItemStack[]{GameItemResource.getSelectDaemonStick().getItemStack()});
                        break;
                    case "omaenige":
                        player.getInventory().addItem(new ItemStack[]{GameItemResource.getSelectRunnerStick().getItemStack()});
                        break;
                    case "block_select":
                        player.getInventory().addItem(new ItemStack[]{GameItemResource.getSelectHideBlock().getItemStack()});
                        break;
                    case "kakureru":
                        player.getInventory().addItem(new ItemStack[]{GameItemResource.getHide().getItemStack()});
                        break;
                    default:
                        this.sendHelp(player);
                }
            } catch (Exception var5) {
                this.sendHelp(player);
            }

        }
    }

    private void sendHelp(Player player) {
        Chat.sendMessage(player, ChatColor.GREEN + "[ヘルプ]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "[アイテム]  " + ChatColor.LIGHT_PURPLE + "[説明]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "omaeoni  " + ChatColor.LIGHT_PURPLE + "鬼を選択するアイテム", true);
        Chat.sendMessage(player, ChatColor.AQUA + "omaenige  " + ChatColor.LIGHT_PURPLE + "逃げを選択するアイテム", true);
        Chat.sendMessage(player, ChatColor.AQUA + "block_select  " + ChatColor.LIGHT_PURPLE + "ブロックを選択するアイテム", true);
        Chat.sendMessage(player, ChatColor.AQUA + "omaeoni  " + ChatColor.LIGHT_PURPLE + "鬼を選択するアイテム", true);
    }
}
