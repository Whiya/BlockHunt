package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class StopCommand implements SubCommand {

    @Override
    public @NonNull String getSubCommand() {
        return "stop";
    }

    @Override
    public @NonNull String getDescription() {
        return null;
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {

    }
}
