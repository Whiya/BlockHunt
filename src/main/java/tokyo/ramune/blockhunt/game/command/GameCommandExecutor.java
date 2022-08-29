package tokyo.ramune.blockhunt.game.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.util.Chat;

class GameCommandExecutor implements CommandExecutor {
    GameCommandExecutor() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            Player player = (Player)sender;
            if (args.length == 0) {
                BlockHunt.getGameCommandManager().getSubCommand("help").onCommand(player, args);
                return true;
            } else {
                try {
                    BlockHunt.getGameCommandManager().getSubCommand(args[0]).onCommand(player, args);
                } catch (Exception var7) {
                    Chat.sendMessage(player, ChatColor.RED + "サブコマンドが見つかりませんでした... もしくは、サブコマンド実行中にエラーが発生しました", true);
                    BlockHunt.getGameCommandManager().getSubCommand("help").onCommand(player, args);
                }

                return true;
            }
        }
    }
}