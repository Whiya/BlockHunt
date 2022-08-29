package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.util.Chat;

public class HelpCommand implements SubCommand {
    public HelpCommand() {
    }

    public @NonNull String getSubCommand() {
        return "help";
    }

    public @NonNull String getDescription() {
        return "ヘルプを表示します";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            Chat.sendMessage(player, "", false);
            Chat.sendMessage(player, ChatColor.GREEN + "[ヘルプ]", true);
            Chat.sendMessage(player, ChatColor.AQUA + "[サブコマンド] " + ChatColor.LIGHT_PURPLE + "[説明]", true);
            SubCommand[] var3 = BlockHunt.getGameCommandManager().getSubCommands();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                SubCommand command = var3[var5];
                Chat.sendMessage(player, ChatColor.AQUA + ChatColor.BOLD.toString() + command.getSubCommand() + "   " + ChatColor.LIGHT_PURPLE + command.getDescription(), true);
            }

        }
    }
}
