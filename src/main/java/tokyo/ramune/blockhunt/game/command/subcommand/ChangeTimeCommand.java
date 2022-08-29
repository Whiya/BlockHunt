package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.GameManager;

public class ChangeTimeCommand implements SubCommand {
    public ChangeTimeCommand() {
    }

    public @NonNull String getSubCommand() {
        return "time";
    }

    public @NonNull String getDescription() {
        return "時間を変更します";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        GameManager gm = BlockHunt.getGameManager();
        if (player.isOp()) {
            try {
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt time <runner, game, end> <add, sub>");
                    player.sendMessage(ChatColor.AQUA + "runner: 隠れる時間, game: ゲーム時間, end: ゲーム終了時の待機時間");
                    player.sendMessage(ChatColor.AQUA + "add: 10秒加算, sub: 10秒減算");
                    return;
                }

                switch (args[1]) {
                    case "runner":
                        if (args[2].equals("add")) {
                            gm.getWaitingRunnerHideTimer().setMaxTime(gm.getWaitingRunnerHideTimer().getMaxTime() + 5);
                        } else if (args[2].equals("sub")) {
                            if (gm.getWaitingRunnerHideTimer().getMaxTime() <= 0) {
                                player.sendMessage(ChatColor.RED + "0秒以下には設定できません");
                                return;
                            }

                            gm.getWaitingRunnerHideTimer().setMaxTime(gm.getWaitingRunnerHideTimer().getMaxTime() - 5);
                        }
                        break;
                    case "game":
                        if (args[2].equals("add")) {
                            gm.getGameTimer().setMaxTime(gm.getGameTimer().getMaxTime() + 10);
                        } else if (args[2].equals("sub")) {
                            if (gm.getGameTimer().getMaxTime() <= 0) {
                                player.sendMessage(ChatColor.RED + "0秒以下には設定できません");
                                return;
                            }

                            gm.getGameTimer().setMaxTime(gm.getGameTimer().getMaxTime() - 10);
                        }
                        break;
                    case "end":
                        if (args[2].equals("add")) {
                            gm.getEndingTimer().setMaxTime(gm.getEndingTimer().getMaxTime() + 5);
                        } else if (args[2].equals("sub")) {
                            if (gm.getEndingTimer().getMaxTime() <= 0) {
                                player.sendMessage(ChatColor.RED + "0秒以下には設定できません");
                                return;
                            }

                            gm.getEndingTimer().setMaxTime(gm.getEndingTimer().getMaxTime() - 5);
                        }
                        break;
                    default:
                        player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt time <runner, game, end> <add, sub>");
                        player.sendMessage(ChatColor.AQUA + "runner: 隠れる時間, game: ゲーム時間, end: ゲーム終了時の待機時間");
                        player.sendMessage(ChatColor.AQUA + "add: 10秒加算, sub: 10秒減算");
                        return;
                }

                player.sendMessage(ChatColor.AQUA + "変更されました!\n隠れる時間: " + gm.getWaitingRunnerHideTimer().getMaxTime() + "\nゲーム時間: " + gm.getGameTimer().getMaxTime() + "\nゲーム終了時の待機時間: " + gm.getEndingTimer().getMaxTime());
            } catch (Exception var6) {
                player.sendMessage(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "/blockhunt time <runner, game, end> <add, sub>");
                player.sendMessage(ChatColor.AQUA + "runner: 逃げる時間, game: ゲーム時間, end: ゲーム終了時の待機時間");
                player.sendMessage(ChatColor.AQUA + "add: 10秒加算, sub: 10秒減算");
            }

        }
    }
}
