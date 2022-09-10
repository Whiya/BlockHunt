package tokyo.ramune.blockhunt.game.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;

public class PlayerManager {
    private final ArrayList<HuntPlayer> huntPlayers = new ArrayList();

    public PlayerManager() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerManagerListener(), BlockHunt.getPlugin());
        Bukkit.getOnlinePlayers().forEach(this::initializePlayer);
    }

    public void detectRoles() {
        try {
            if (this.huntPlayers.size() <= BlockHunt.getGameManager().getDaemonCount()) {
                Bukkit.getServer().sendMessage(Component.text(ChatColor.RED + "設定されている鬼の数が現在の人数よりも大きすぎます! \n現在の人数: " + this.huntPlayers.size() + "  設定されている鬼の数: " + BlockHunt.getGameManager().getDaemonCount()));
                return;
            }

            this.huntPlayers.forEach((huntPlayer) -> {
                huntPlayer.setRole(HuntPlayerRole.RUNNER);
            });

            for(int i = 0; i < BlockHunt.getGameManager().getDaemonCount(); ++i) {
                ((HuntPlayer)this.getRunnerPlayers().get((new Random()).nextInt(this.getRunnerPlayers().size()))).setRole(HuntPlayerRole.DAEMON);
            }

            Bukkit.getServer().sendMessage(Component.text(ChatColor.AQUA + "ランダムで鬼が決まりました! 鬼: "));
            this.getDaemonPlayers().forEach((daemonPlayer) -> {
                Bukkit.getServer().sendMessage(Component.text(daemonPlayer.getPlayer().getName()));
            });
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public HuntPlayer getHuntPlayer(Player player) {
        if (!this.isInitializedPlayer(player)) {
            this.initializePlayer(player);
        }

        Iterator var2 = this.huntPlayers.iterator();

        HuntPlayer huntPlayer;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            huntPlayer = (HuntPlayer)var2.next();
        } while(!huntPlayer.getPlayer().equals(player));

        return huntPlayer;
    }

    public void removeHuntPlayer(Player player) {
        if (this.isInitializedPlayer(player)) {
            this.huntPlayers.remove(this.getHuntPlayer(player));
        }
    }

    public ArrayList<HuntPlayer> getHuntPlayers() {
        return this.huntPlayers;
    }

    public void initializePlayer(Player player) {
        if (!this.isInitializedPlayer(player)) {
            this.huntPlayers.add(new HuntPlayer(player));
        }
    }

    private boolean isInitializedPlayer(Player player) {
        Iterator var2 = this.huntPlayers.iterator();

        HuntPlayer huntPlayer;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            huntPlayer = (HuntPlayer)var2.next();
        } while(!huntPlayer.getPlayer().equals(player));

        return true;
    }

    public ArrayList<HuntPlayer> getDaemonPlayers() {
        ArrayList<HuntPlayer> daemonPlayers = new ArrayList();
        this.getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.DAEMON)) {
                daemonPlayers.add(huntPlayer);
            }

        });
        return daemonPlayers;
    }

    public ArrayList<HuntPlayer> getRunnerPlayers() {
        ArrayList<HuntPlayer> runnerPlayers = new ArrayList();
        this.getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.RUNNER)) {
                runnerPlayers.add(huntPlayer);
            }

        });
        return runnerPlayers;
    }

    public ArrayList<HuntPlayer> getSpectatorPlayers() {
        ArrayList<HuntPlayer> spectatorPlayers = new ArrayList();
        this.getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.SPECTATOR)) {
                spectatorPlayers.add(huntPlayer);
            }

        });
        return spectatorPlayers;
    }

    public ArrayList<HuntPlayer> getAwaitPlayers() {
        ArrayList<HuntPlayer> awaitPlayers = new ArrayList();
        this.getHuntPlayers().forEach((huntPlayer) -> {
            if (huntPlayer.getRole().equals(HuntPlayerRole.AWAIT)) {
                awaitPlayers.add(huntPlayer);
            }

        });
        return awaitPlayers;
    }
}
