package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.GameManager;

public class ChangeDaemonCommand implements SubCommand {
    public ChangeDaemonCommand() {
    }

    public @NonNull String getSubCommand() {
        return "daemon";
    }

    public @NonNull String getDescription() {
        return "鬼の管理";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        GameManager gm = BlockHunt.getGameManager();
        if (player.isOp()) {
            try {
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt daemon <add, sub>");
                    player.sendMessage(ChatColor.AQUA + "add: 鬼を一人追加, sub: 鬼を一人削除");
                    return;
                }

                if (args[1].equals("add")) {
                    if (BlockHunt.getPlugin().getServer().getOnlinePlayers().size() > gm.getDaemonCount() + 1) {
                        gm.setDaemonCount(gm.getDaemonCount() + 1);
                        player.sendMessage(ChatColor.AQUA + "変更しました! 現在の設定されてある鬼の数: " + gm.getDaemonCount());
                    } else {
                        player.sendMessage(ChatColor.RED + "参加人数より多く鬼を設定できないか逃げのプレイヤーが最低一人は必要です");
                    }
                } else if (args[1].equals("sub")) {
                    if (gm.getDaemonPlayers().size() >= gm.getDaemonCount()) {
                        player.sendMessage(ChatColor.RED + "すでに割り当てられている鬼の数は、設定されている鬼の数よりも少なくなることはできません。");
                        player.sendMessage(ChatColor.RED + "メニューから割り当てられている鬼の数を減らしてください");
                        return;
                    }

                    gm.setDaemonCount(gm.getDaemonCount() - 1);
                    player.sendMessage(ChatColor.AQUA + "変更しました! 現在の設定されてある鬼の数: " + gm.getDaemonCount());
                }
            } catch (Exception var5) {
                player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt daemon <add, sub>");
                player.sendMessage(ChatColor.AQUA + "add: 鬼を一人追加, sub: 鬼を一人削除");
            }

        }
    }
}
