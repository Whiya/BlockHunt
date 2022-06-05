package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class AddDaemonCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "adddaemon";
    }

    @Override
    public @NonNull String getDescription() {
        return "鬼を追加します";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        GameHandler.setDaemonAmount(GameHandler.getDaemonAmount() + 1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        Chat.sendMessage(player, ChatColor.GREEN + "鬼の数を1人増やしました。 - " + ChatColor.YELLOW.toString() + (GameHandler.getDaemonAmount() - 1) + " → " + GameHandler.getDaemonAmount(), true);
    }
}
