package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class AddTimeCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "addtime";
    }

    @Override
    public @NonNull String getDescription() {
        return "時間を追加します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        GameHandler.setTime(GameHandler.getTime() + 10);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        Chat.sendMessage(player, ChatColor.GREEN + "時間を10秒増やしました。 - " + ChatColor.YELLOW.toString() + (GameHandler.getTime() - 10) + " → " + GameHandler.getTime(), true);
    }
}
