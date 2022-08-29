package tokyo.ramune.blockhunt.game.player;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.function.Consumer;

public enum HuntPlayerRole {
    DAEMON,
    RUNNER,
    SPECTATOR,
    AWAIT;

    private HuntPlayerRole() {
    }

    public String toString() {
        if (super.equals(DAEMON)) {
            return ChatColor.RED + "鬼" + ChatColor.RESET;
        } else if (super.equals(RUNNER)) {
            return ChatColor.AQUA + "逃げ" + ChatColor.RESET;
        } else if (super.equals(SPECTATOR)) {
            return ChatColor.GRAY + "スペクテイター" + ChatColor.RESET;
        } else {
            return super.equals(AWAIT) ? ChatColor.YELLOW + "待機中" + ChatColor.RESET : "invalid Role";
        }
    }

    public ChatColor getColor() {
        if (super.equals(DAEMON)) {
            return ChatColor.RED;
        } else if (super.equals(RUNNER)) {
            return ChatColor.AQUA;
        } else if (super.equals(SPECTATOR)) {
            return ChatColor.GRAY;
        } else {
            return super.equals(AWAIT) ? ChatColor.YELLOW : ChatColor.LIGHT_PURPLE;
        }
    }

    public Consumer<PlayerDeathEvent> getDeathEvent() {
        if (super.equals(RUNNER)) {
            return playerDeathEvent -> {

            };
        } else if (super.equals(DAEMON)) {
            return playerDeathEvent -> {

            };
        } else if (super.equals(SPECTATOR)) {
            return playerDeathEvent -> {

            };
        } else if (super.equals(AWAIT)) {
            return playerDeathEvent -> {

            };
        }
        return playerDeathEvent -> {};
    }
}
