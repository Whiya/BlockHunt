package tokyo.ramune.blockhunt.player;

import org.bukkit.Bukkit;
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
        runner.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        runner.add

        // daemon


        // spectator
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
