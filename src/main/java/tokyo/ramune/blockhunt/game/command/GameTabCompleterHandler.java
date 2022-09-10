package tokyo.ramune.blockhunt.game.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.command.subcommand.SubCommand;

class GameTabCompleterHandler implements TabCompleter {
    GameTabCompleterHandler() {
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList();
        if (args.length != 1) {
            return null;
        } else {
            SubCommand[] sub = BlockHunt.getGameCommandManager().getSubCommands();
            int subs = sub.length;

            for(int i = 0; i < subs; ++i) {
                SubCommand _command = sub[i];
                StringUtil.copyPartialMatches(args[0], Collections.singleton(_command.getSubCommand()), completions);
            }

            Collections.sort(completions);
            return completions;
        }
    }
}
