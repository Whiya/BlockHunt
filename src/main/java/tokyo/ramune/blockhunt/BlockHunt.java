package tokyo.ramune.blockhunt;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.blockhunt.bossbar.BossBarManager;
import tokyo.ramune.blockhunt.config.Config;
import tokyo.ramune.blockhunt.config.ConfigFile;
import tokyo.ramune.blockhunt.game.GameManager;
import tokyo.ramune.blockhunt.game.command.GameCommandManager;
import tokyo.ramune.blockhunt.game.item.GameItemListener;
import tokyo.ramune.blockhunt.game.player.PlayerManager;
import tokyo.ramune.blockhunt.game.prefix.PrefixManager;
import tokyo.ramune.blockhunt.sidebar.SidebarManager;

public final class BlockHunt extends JavaPlugin {
    private static JavaPlugin plugin;
    private static Config config;
    private static GameManager gameManager;
    private static GameCommandManager gameCommandManager;
    private static PlayerManager playerManager;
    private static BossBarManager bossBarManager;
    private static PrefixManager prefixManager;
    private static SidebarManager sidebarManager;

    public BlockHunt() {
    }

    public void onEnable() {
        plugin = this;
        this.initializeConfigFile();
        gameManager = new GameManager();
        gameCommandManager = new GameCommandManager();
        playerManager = new PlayerManager();
        bossBarManager = new BossBarManager();
        prefixManager = new PrefixManager();
        sidebarManager = new SidebarManager(this);
        getServer().getPluginManager().registerEvents(new GameItemListener(), this);
        this.getLogger().info("プラグインが有効になりました");
    }

    public void onDisable() {
        this.getLogger().info("プラグインが無効になりました");
    }

    private void initializeConfigFile() {
        ConfigFile configFile = new ConfigFile(this, "config.yml");
        configFile.saveDefaultConfig();
        configFile.getConfig().options().copyDefaults(true);
        configFile.saveConfig();
        config = new Config(configFile);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Config getConfigFile() {
        return config;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static BossBarManager getBossBarManager() {
        return bossBarManager;
    }

    public static PrefixManager getPrefixManager() {
        return prefixManager;
    }

    public static GameCommandManager getGameCommandManager() {
        return gameCommandManager;
    }

    public static SidebarManager getSidebarManager() {
        return sidebarManager;
    }
}
