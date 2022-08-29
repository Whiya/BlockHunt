package tokyo.ramune.blockhunt.bossbar;

import java.util.Collection;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;

public class BossBarManager {
    private BossBar bossBar;
    private int time = 0;
    private int maxTime = 0;

    public BossBarManager() {
        if (Bukkit.getBossBar(new NamespacedKey(BlockHunt.getPlugin(), "blockhunt.bossbar")) == null) {
            Bukkit.createBossBar(new NamespacedKey(BlockHunt.getPlugin(), "blockhunt.bossbar"), "残り時間 0秒", BarColor.BLUE, BarStyle.SOLID, new BarFlag[0]);
        }
        this.bossBar = Bukkit.getBossBar(new NamespacedKey(BlockHunt.getPlugin(), "blockhunt.bossbar"));
        int time = 0;
        this.bossBar.setVisible(true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }
        this.bossBar.setVisible(true);
        this.bossBar.setColor(BarColor.BLUE);
    }

    public void setTime(int time) {
        this.time = time;
        this.bossBar.setTitle(ChatColor.AQUA + BlockHunt.getGameManager().getStatus().toString() + " - 残り時間 " + ChatColor.WHITE + time + ChatColor.AQUA + "秒");
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void updateProgress() {
        double progress = (double)this.time / (double)this.maxTime;
        if (progress > 1.0) {
            progress = 1.0;
        }

        this.bossBar.setProgress(progress);
    }

    public void show(Player player) {
        this.bossBar.addPlayer(player);
    }
}
