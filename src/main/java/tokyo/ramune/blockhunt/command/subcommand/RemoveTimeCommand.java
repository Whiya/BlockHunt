package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class RemoveTimeCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "removetime";
    }

    @Override
    public @NonNull String getDescription() {
        return "時間を減らします";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.isOp()) {
            return;
        }
        if (GameHandler.getTime() == 0) {
            Chat.sendMessage(player, ChatColor.RED + "時間は0秒以下には設定できません", true);
            return;
        }
        GameHandler.setTime(GameHandler.getTime() - 10);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        Chat.sendMessage(player, ChatColor.GREEN + "時間を10秒減らしました。 - " + ChatColor.YELLOW.toString() + (GameHandler.getTime() + 10) + " → " + GameHandler.getTime(), true);
    }
}
