package tokyo.ramune.blockhunt.sidebar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class SidebarBuilder {
    private final String title;
    private final String displayName;
    private final DisplaySlot displaySlot;
    private final ArrayList<String> lines = new ArrayList();

    public SidebarBuilder(String title, DisplaySlot displaySlot, String displayName) {
        this.title = title;
        this.displaySlot = displaySlot;
        this.displayName = displayName;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public void addBlankLine() {
        this.lines.add("{BLANK_LINE}");
    }

    public Scoreboard build() {
        Collections.reverse(this.lines);
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewObjective(this.title, "dummy");
        Objective ob = sb.getObjective(this.title);
        ob.setDisplayName(this.displayName);
        ob.setDisplaySlot(this.displaySlot);
        int blankCount = 1;
        int i = 0;

        for(Iterator var5 = this.lines.iterator(); var5.hasNext(); ++i) {
            String line = (String)var5.next();
            if (!line.equals("{BLANK_LINE}")) {
                ob.getScore(line).setScore(i);
            } else {
                String blank = "";

                for(int n = 0; n < blankCount; ++n) {
                    blank = blank + ChatColor.RESET.toString();
                }

                ++blankCount;
                ob.getScore(blank).setScore(i);
            }
        }

        return sb;
    }
}
