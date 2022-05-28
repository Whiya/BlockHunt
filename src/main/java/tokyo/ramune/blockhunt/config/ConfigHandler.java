package tokyo.ramune.blockhunt.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import tokyo.ramune.blockhunt.BlockHunt;

public class ConfigHandler {

    private final static FileConfiguration config = BlockHunt.getPlugin().getConfig();

    public static Location SPAWN_LOCATION, START_LOCATION, SPECTATOR_LOCATION;

    public static void load(FileConfiguration config) {
        SPAWN_LOCATION = new Location(Bukkit.getWorld(config.getString("config.spawn-location.world", "world"))
                , config.getDouble("config.spawn-location.x", 0.0)
                , config.getDouble("config.spawn-location.y", 0.0)
                , config.getDouble("config.spawn-location.z", 0.0)
                , config.getInt("config.spawn-location.yaw", 0)
                , config.getInt("config.spawn-location.pitch", 0));
        START_LOCATION = new Location(Bukkit.getWorld(config.getString("config.start-location.world", "world"))
                , config.getDouble("config.start-location.x", 0.0)
                , config.getDouble("config.start-location.y", 0.0)
                , config.getDouble("config.start-location.z", 0.0)
                , config.getInt("config.start-location.yaw", 0)
                , config.getInt("config.start-location.pitch", 0));
        SPECTATOR_LOCATION = new Location(Bukkit.getWorld(config.getString("config.spectator-location.world", "world"))
                , config.getDouble("config.spectator-location.x", 0.0)
                , config.getDouble("config.spectator-location.y", 0.0)
                , config.getDouble("config.spectator-location.z", 0.0)
                , config.getInt("config.spectator-location.yaw", 0)
                , config.getInt("config.spectator-location.pitch", 0));
    }
}
