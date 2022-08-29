package tokyo.ramune.blockhunt.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import tokyo.ramune.blockhunt.BlockHunt;

public class SidebarManager {
    private JavaPlugin plugin;

    public SidebarManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.sidebar();
    }

    public Scoreboard getPlayingSidebar(Player player) {
        SidebarBuilder sb = new SidebarBuilder("playing", DisplaySlot.SIDEBAR, "情報");
        sb.addLine("ゲームステータス: " + ChatColor.BLUE + "進行中");
        sb.addBlankLine();
        sb.addLine("あなたは" + BlockHunt.getPlayerManager().getHuntPlayer(player).getRole().toString() + "です");
        sb.addBlankLine();
        sb.addLine(ChatColor.AQUA + "逃げの人数: " + ChatColor.WHITE + BlockHunt.getGameManager().getRunnerPlayers().size());
        sb.addBlankLine();
        sb.addLine(ChatColor.RED + "鬼の人数: " + ChatColor.WHITE + BlockHunt.getGameManager().getDaemonPlayers().size());
        return sb.build();
    }

    public Scoreboard getWaitingSidebar(Player player) {
        SidebarBuilder sb = new SidebarBuilder("waiting", DisplaySlot.SIDEBAR, "情報");
        sb.addLine("ゲームステータス: " + ChatColor.YELLOW + "待機中");
        sb.addBlankLine();
        sb.addLine("あなたは" + BlockHunt.getPlayerManager().getHuntPlayer(player).getRole().toString() + "です");
        sb.addBlankLine();
        sb.addLine("隠れ時間: " + BlockHunt.getGameManager().getWaitingRunnerHideTimer().getMaxTime());
        sb.addBlankLine();
        sb.addLine("ゲーム時間: " + BlockHunt.getGameManager().getGameTimer().getMaxTime());
        sb.addBlankLine();
        sb.addLine(ChatColor.RED + "鬼の初期人数: " + ChatColor.WHITE + BlockHunt.getGameManager().getDaemonCount());
        return sb.build();
    }

    public Scoreboard getEndingSidebar(Player player) {
        SidebarBuilder sb = new SidebarBuilder("ending", DisplaySlot.SIDEBAR, "情報");
        sb.addLine("ゲームステータス: " + ChatColor.GOLD + "終了～");
        sb.addBlankLine();
        sb.addLine("○○の勝利");
        sb.addBlankLine();
        sb.addLine("あなたのスコア: 0");
        sb.addBlankLine();
        sb.addLine("スコアランキング 未実装");
        return sb.build();
    }

    public Scoreboard getwaiting_runner_hide(Player player) {
        SidebarBuilder sb = new SidebarBuilder("隠れ中", DisplaySlot.SIDEBAR, "情報");
        sb.addLine("ゲームステータス: " + ChatColor.LIGHT_PURPLE + "逃げが隠れています");
        sb.addBlankLine();
        sb.addLine(ChatColor.AQUA + "逃げの人数: " + ChatColor.WHITE + BlockHunt.getGameManager().getRunnerPlayers().size());
        sb.addBlankLine();
        sb.addLine(ChatColor.RED + "鬼の人数: " + ChatColor.WHITE + BlockHunt.getGameManager().getDaemonPlayers().size());
        sb.addBlankLine();
        sb.addLine("スコアランキング 未実装");
        return sb.build();
    }

    public void sidebar() {
        Bukkit.getScheduler().runTaskTimer(BlockHunt.getPlugin(), () -> {
            switch (BlockHunt.getGameManager().getStatus()) {
                case ENDING:
                    Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
                        onlinePlayer.setScoreboard(this.getEndingSidebar(onlinePlayer));
                    });
                    break;
                case PLAYING:
                    Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
                        onlinePlayer.setScoreboard(this.getPlayingSidebar(onlinePlayer));
                    });
                    break;
                case WAITING:
                    Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
                        onlinePlayer.setScoreboard(this.getWaitingSidebar(onlinePlayer));
                    });
                    break;
                case WAITING_RUNNER_HIDE:
                    Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
                        onlinePlayer.setScoreboard(this.getwaiting_runner_hide(onlinePlayer));
                    });
                    break;
                default:
                    this.plugin.getLogger().warning("サイドバーにて異常が発生しています: " + BlockHunt.getGameManager().getStatus().name());
            }

        }, 4L, 4L);
    }
}
