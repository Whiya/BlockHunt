package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.util.Chat;

public class StopCommand implements SubCommand {
    public StopCommand() {
    }

    public @NonNull String getSubCommand() {
        return "stop";
    }

    public @NonNull String getDescription() {
        return "ゲームをストップ";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            if (!BlockHunt.getGameManager().isStarted()) {
                Chat.sendMessage(player, ChatColor.RED + "すでに停止済みです。 ばーか", true);
            } else {
                BlockHunt.getGameManager().stop();
            }
        }
    }
}
