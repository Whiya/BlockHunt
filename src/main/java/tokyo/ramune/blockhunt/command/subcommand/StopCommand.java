package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

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
        if (!player.isOp()) {
            return;
        }
        if (!GameHandler.isStarted()) {
            Chat.sendMessage(player, ChatColor.RED + "すでに停止済みです。 ばーか", true);
            return;
        }
        GameHandler.stop();
    }
}
