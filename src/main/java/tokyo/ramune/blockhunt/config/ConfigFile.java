package tokyo.ramune.blockhunt.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigFile {
    private FileConfiguration config = null;
    private final File configFile;
    private final String file;
    private final Plugin plugin;

    public ConfigFile(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.file = fileName;
        this.configFile = new File(plugin.getDataFolder(), this.file);
    }

    public void saveDefaultConfig() {
        if (!this.configFile.exists()) {
            this.plugin.saveResource(this.file, false);
        }

    }

    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defConfigStream = this.plugin.getResource(this.file);
        if (defConfigStream != null) {
            this.config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) {
            this.reloadConfig();
        }

        return this.config;
    }

    public void saveConfig() {
        if (this.config != null) {
            try {
                this.getConfig().save(this.configFile);
            } catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, var2);
            }

        }
    }
}
