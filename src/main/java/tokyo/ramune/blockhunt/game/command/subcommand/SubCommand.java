package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface SubCommand {
    @NonNull String getSubCommand();

    @NonNull String getDescription();

    void onCommand(@NonNull Player var1, String[] var2);
}
