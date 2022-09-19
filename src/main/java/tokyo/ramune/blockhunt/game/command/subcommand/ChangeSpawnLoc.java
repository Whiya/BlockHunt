package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;


public class ChangeSpawnLoc implements SubCommand{
    public @NonNull String getSubCommand() {
        return "spawnloc";
    }

    public @NonNull String getDescription() {
        return "鯖に入ったときにTPされる場所";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            if (args.length <= 1) {
                player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt spawnloc <set , tp>");
                player.sendMessage(ChatColor.AQUA + "set: あなたが立っている場所を設定, tp: 設定している場所にtp");
                return;
            }
            switch (args[1]){
                case "set":
                    BlockHunt.getConfigFile().setSPAWN_LOCATION(player.getLocation());
                    player.sendMessage(ChatColor.BLUE + "あなたのいる場所をスポーン場所として登録しました。");
                    break;
                case "tp":
                    player.teleport(BlockHunt.getConfigFile().SPAWN_LOCATION);
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "コマンドが違うよ、以下を参考にしてみてね。");
                    player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt spawnloc <set , tp>");
                    player.sendMessage(ChatColor.AQUA + "set: あなたが立っている場所を設定, tp: 設定している場所にtp");
                    break;
            }

        }
    }
}
