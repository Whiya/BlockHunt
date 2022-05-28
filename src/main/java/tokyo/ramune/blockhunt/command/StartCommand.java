package tokyo.ramune.blockhunt.command;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class StartCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "start";
    }

    @Override
    public @NonNull String getDescription() {
        return "ゲームを開始します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.isOp()) {
            return;
        }
        if (GameHandler.isStarted()) {
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
            Chat.sendMessage(player, ChatColor.RED + "ゲームはすでに開始済みです", true);
            return;
        }
        Chat.sendMessage(player, ChatColor.BLUE + "開始しました", true);
    }
}
