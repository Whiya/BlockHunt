package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DebugCommand implements SubCommand {
    public DebugCommand() {
    }

    public @NonNull String getSubCommand() {
        return "debug";
    }

    public @NonNull String getDescription() {
        return "for debug";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            ;
        }
    }
}
