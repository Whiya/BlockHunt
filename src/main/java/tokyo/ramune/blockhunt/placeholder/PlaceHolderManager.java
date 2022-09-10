package tokyo.ramune.blockhunt.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.blockhunt.BlockHunt;

public class PlaceHolderManager {

    public PlaceHolderManager() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI")
                == null) {
            BlockHunt.getPlugin().getLogger().warning("PlaceholderAPIが見つかりませんでした。 一部の機能が利用できません。");
        }
        registerPlaceholders();
    }

    public void registerPlaceholders() {
        new PlaceholderExpansionBuilder("blockhunt_daemon_count", player -> String.valueOf(BlockHunt.getPlayerManager().getDaemonPlayers().size()))
                .build()
                .register();
        new PlaceholderExpansionBuilder("blockhunt_runner_count", player -> String.valueOf(BlockHunt.getPlayerManager().getRunnerPlayers().size()))
                .build()
                .register();
        new PlaceholderExpansionBuilder("blockhunt_await_count", player -> String.valueOf(BlockHunt.getPlayerManager().getAwaitPlayers().size()))
                .build()
                .register();
        new PlaceholderExpansionBuilder("blockhunt_spectator_count", player -> String.valueOf(BlockHunt.getPlayerManager().getSpectatorPlayers().size()))
                .build()
                .register();
        new PlaceholderExpansionBuilder("blockhunt_time_now", player -> String.valueOf(BlockHunt.getPlayerManager().getSpectatorPlayers().size()))
                .build()
                .register();
    }
}
