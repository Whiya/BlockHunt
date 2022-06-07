package tokyo.ramune.blockhunt.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

public class RemoveDaemonCommand implements SubCommand {
    @Override
    public @NonNull String getSubCommand() {
        return "removedaemon";
    }

    @Override
    public @NonNull String getDescription() {
        return "鬼を減らします";
    }

    @Override
    public void onCommand(@NonNull Player player, String[] args) {
        if (!player.isOp()) {
            return;
        }
        if (GameHandler.getDaemonAmount() == 0) {
            Chat.sendMessage(player, ChatColor.RED + "鬼の数は0人以下には設定できません", true);
            return;
        }
        GameHandler.setDaemonAmount(GameHandler.getDaemonAmount() - 1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        Chat.sendMessage(player, ChatColor.GREEN + "鬼の数を1人減らしました。 - " + ChatColor.YELLOW.toString() + (GameHandler.getDaemonAmount() + 1) + " → " + GameHandler.getDaemonAmount(), true);
    }
}
