package tokyo.ramune.blockhunt;


import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.blockhunt.command.CommandHandler;
import tokyo.ramune.blockhunt.config.ConfigHandler;
import tokyo.ramune.blockhunt.info.Bossbar;
import tokyo.ramune.blockhunt.info.Sidebar;
import tokyo.ramune.blockhunt.listener.ListenerHandler;
import tokyo.ramune.blockhunt.player.PlayerManager;

public final class BlockHunt extends JavaPlugin {
    public static Bossbar bossbar;
    public static Sidebar sidebar;
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        ConfigHandler.load(getConfig());

        new ListenerHandler(this);
        new CommandHandler(this);

        PlayerManager.initializePlayers();

        bossbar = new Bossbar(plugin);
        sidebar = new Sidebar(plugin);

        getLogger().info("プラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        getLogger().info("プラグインが無効になりました");
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
    public static Bossbar getBossbar(){return bossbar;}
    public static Sidebar getSidebar(){return sidebar;}

}
