package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.command.CommandHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class HelpCommand implements SubCommand {

    @NonNull
    @Override
    public String getSubCommand() {
        return "help";
    }

    @NonNull
    @Override
    public String getDescription() {
        return "ヘルプを表示します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        Chat.sendMessage(player, "", false);
        Chat.sendMessage(player, ChatColor.GREEN + "[ヘルプ]", true);
        Chat.sendMessage(player, ChatColor.AQUA + "[サブコマンド] " + ChatColor.LIGHT_PURPLE + "[説明]", true);

        for (SubCommand command : CommandHandler.getSubCommands()) {
            Chat.sendMessage(player, ChatColor.AQUA + ChatColor.BOLD.toString() + command.getSubCommand() + "   " + ChatColor.LIGHT_PURPLE + command.getDescription(), true);
        }
    }
}
