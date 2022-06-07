package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class DebugCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "debug";
    }

    @Override
    public @NonNull String getDescription() {
        return "for debug";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.isOp()) {
            return;
        }

    }
}
