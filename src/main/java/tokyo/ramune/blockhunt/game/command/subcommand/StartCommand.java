package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.util.Chat;

public class StartCommand implements SubCommand {
    public StartCommand() {
    }

    public @NonNull String getSubCommand() {
        return "start";
    }

    public @NonNull String getDescription() {
        return "ゲームを開始します";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            if (BlockHunt.getGameManager().isStarted()) {
                Chat.sendMessage(player, ChatColor.RED + "すでに開始済みです。 ばーか", true);
            } else {
                BlockHunt.getGameManager().start();
            }
        }
    }
}
