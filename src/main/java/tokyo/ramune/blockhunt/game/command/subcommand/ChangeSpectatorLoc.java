package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;

public class ChangeSpectatorLoc implements SubCommand{
    public @NonNull String getSubCommand() {
        return "specloc";
    }

    public @NonNull String getDescription() {return "スペクテイターになったときにtpされる場所";}

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            if (args.length <= 1) {
                player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt specloc <set , tp>");
                player.sendMessage(ChatColor.AQUA + "set: あなたが立っている場所を設定, tp: 設定している場所にtp");
                return;
            }
            switch (args[1]){
                case "set":
                    BlockHunt.getConfigFile().setSPECTATOR_LOCATION(player.getLocation());
                    player.sendMessage(ChatColor.BLUE + "あなたのいる場所をスペクテイターになったときにtpされる場所として登録しました。");
                    break;
                case "tp":
                    player.teleport(BlockHunt.getConfigFile().SPECTATOR_LOCATION);
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
