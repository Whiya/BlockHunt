package tokyo.ramune.blockhunt.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamManager {

    private static Team runner, daemon, spectator;
    private final static Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();

    public static void initializeTeams() {
        // runner
        if (!isExistsTeam("runner")) {
            sb.registerNewTeam("runner");
        }
        runner = sb.getTeam("runner");
        daemon.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        runner.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        runner.setColor(ChatColor.AQUA);
        daemon.setPrefix(ChatColor.WHITE + "[" + ChatColor.AQUA + "逃げ" + ChatColor.WHITE + "] ");

        // daemon
        if (!isExistsTeam("daemon")) {
            sb.registerNewTeam("daemon");
        }
        daemon = sb.getTeam("daemon");
        daemon.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        daemon.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        daemon.setColor(ChatColor.RED);
        daemon.setPrefix(ChatColor.WHITE + "[" + ChatColor.RED + "鬼" + ChatColor.WHITE + "] ");

        // spectator
        if (!isExistsTeam("spectator")) {
            sb.registerNewTeam("spectator");
        }
        spectator = sb.getTeam("spectator");
        spectator.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        spectator.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        spectator.setColor(ChatColor.RED);
        spectator.setPrefix(ChatColor.WHITE + "[" + ChatColor.GRAY + "スペクテイター" + ChatColor.WHITE + "] ");
    }

    public static Team getRunner() {
        return runner;
    }

    public static Team getDaemon() {
        return daemon;
    }

    public static void addPlayer(Player player, Role role) {
        switch (role) {
            case RUNNER:
                TeamManager.getRunner().addPlayer((OfflinePlayer) player);
                break;
            case DAEMON:
                TeamManager.getDaemon().addPlayer((OfflinePlayer) player);
                break;
            case SPECTATOR:
                TeamManager.getSpectator().addPlayer((OfflinePlayer) player);
                break;
            case NONE:
                TeamManager.getSpectator().addPlayer((OfflinePlayer) player);
                break;
        }
    }

    public static void removePlayer(Player player, Role role) {
        switch (role) {
            case RUNNER:
                TeamManager.getRunner().removePlayer((OfflinePlayer) player);
                break;
            case DAEMON:
                TeamManager.getDaemon().removePlayer((OfflinePlayer) player);
                break;
            case SPECTATOR:
                TeamManager.getSpectator().removePlayer((OfflinePlayer) player);
                break;
            case NONE:
                TeamManager.getSpectator().removePlayer((OfflinePlayer) player);
                break;
        }
    }

    public static Team getSpectator() {
        return spectator;
    }

    private static boolean isExistsTeam(String name) {
        for (Team team : sb.getTeams()) {
            if (team.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
