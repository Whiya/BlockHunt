package tokyo.ramune.blockhunt.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class PlaceholderExpansionBuilder {

    private final String identifier;
    private final Function<Player, String> requestFunction;

    public PlaceholderExpansionBuilder(String identifier, Function<Player, String> requestConsumer) {
        this.identifier = identifier;
        this.requestFunction = requestConsumer;
    }

    public PlaceholderExpansion build() {
        return new PlaceholderExpansion() {
            @Override
            public @NotNull String getIdentifier() {
                return identifier;
            }

            @Override
            public @NotNull String getAuthor() {
                return "BlockHunt";
            }

            @Override
            public @NotNull String getVersion() {
                return "none";
            }

            @Override
            public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
                return requestFunction.apply(player.getPlayer());
            }
        };
    }
}
