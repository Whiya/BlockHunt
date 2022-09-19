package tokyo.ramune.blockhunt.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private final ConfigFile configFile;
    private final FileConfiguration config;
    public Location SPAWN_LOCATION;
    public Location START_LOCATION;
    public Location SPECTATOR_LOCATION;

    public Config(ConfigFile configFile) {
        this.configFile = configFile;
        this.config = configFile.getConfig();
        this.load();
    }

    private void load() {
        this.SPAWN_LOCATION = new Location(Bukkit.getWorld(this.config.getString("config.spawn-location.world", "world")), this.config.getDouble("config.spawn-location.x", 0.0), this.config.getDouble("config.spawn-location.y", 0.0), this.config.getDouble("config.spawn-location.z", 0.0), (float)this.config.getInt("config.spawn-location.yaw", 0), (float)this.config.getInt("config.spawn-location.pitch", 0));
        this.START_LOCATION = new Location(Bukkit.getWorld(this.config.getString("config.start-location.world", "world")), this.config.getDouble("config.start-location.x", 0.0), this.config.getDouble("config.start-location.y", 0.0), this.config.getDouble("config.start-location.z", 0.0), (float)this.config.getInt("config.start-location.yaw", 0), (float)this.config.getInt("config.start-location.pitch", 0));
        this.SPECTATOR_LOCATION = new Location(Bukkit.getWorld(this.config.getString("config.spectator-location.world", "world")), this.config.getDouble("config.spectator-location.x", 0.0), this.config.getDouble("config.spectator-location.y", 0.0), this.config.getDouble("config.spectator-location.z", 0.0), (float)this.config.getInt("config.spectator-location.yaw", 0), (float)this.config.getInt("config.spectator-location.pitch", 0));
    }

    public void reload() {
        this.configFile.reloadConfig();
        this.load();
    }

    public void setSPAWN_LOCATION(Location loc){
        this.config.set("config.spawn-location.world",loc.getWorld().getName());
        this.config.set("config.spawn-location.x",loc.getX());
        this.config.set("config.spawn-location.y",loc.getY());
        this.config.set("config.spawn-location.z",loc.getZ());
        this.config.set("config.spawn-location.yaw",loc.getYaw());
        this.config.set("config.spawn-location.pitch",loc.getPitch());
        this.configFile.saveConfig();
        reload();
    }
    public void setSTART_LOCATION(Location loc){
        this.config.set("config.start-location.world",loc.getWorld().getName());
        this.config.set("config.start-location.x",loc.getX());
        this.config.set("config.start-location.y",loc.getY());
        this.config.set("config.start-location.z",loc.getZ());
        this.config.set("config.start-location.yaw",loc.getYaw());
        this.config.set("config.start-location.pitch",loc.getPitch());
        this.configFile.saveConfig();
        reload();
    }
    public void setSPECTATOR_LOCATION(Location loc){
        this.config.set("config.spectator-location.world",loc.getWorld().getName());
        this.config.set("config.spectator-location.x",loc.getX());
        this.config.set("config.spectator-location.y",loc.getY());
        this.config.set("config.spectator-location.z",loc.getZ());
        this.config.set("config.spectator-location.yaw",loc.getYaw());
        this.config.set("config.spectator-location.pitch",loc.getPitch());
        this.configFile.saveConfig();
        reload();
    }




}
