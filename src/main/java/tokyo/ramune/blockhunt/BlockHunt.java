package tokyo.ramune.blockhunt;


import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.blockhunt.command.CommandHandler;
import tokyo.ramune.blockhunt.config.ConfigHandler;
import tokyo.ramune.blockhunt.info.Bossbar;
import tokyo.ramune.blockhunt.info.Sidebar;
import tokyo.ramune.blockhunt.listener.ListenerHandler;
import tokyo.ramune.blockhunt.player.PlayerManager;
import tokyo.ramune.blockhunt.player.TeamManager;

/**
 * Bukkit JavaPlugin メインクラス
 */
public final class BlockHunt extends JavaPlugin {

    public static Bossbar bossbar;
    public static Sidebar sidebar;
    private static JavaPlugin plugin;

    /**
     * プラグイン起動時に実行
     */
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        ConfigHandler.load(getConfig());

        new ListenerHandler(this);
        new CommandHandler(this);

        TeamManager.initializeTeams();
        PlayerManager.initializePlayers();

        bossbar = new Bossbar(plugin);
        sidebar = new Sidebar(plugin);

        getLogger().info("プラグインが有効になりました");
    }

    /**
     * プラグイン停止時に実行
     */
    @Override
    public void onDisable() {
        getLogger().info("プラグインが無効になりました");
    }

    /**
     * インスタンスを返します
     * @retur`n JavaPlugin
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * ボスバーを返します
     * @return Bossbar
     */
    public static Bossbar getBossbar(){return bossbar;}

    /**
     * サイドバーを返します
     * @return Sidebar
     */
    public static Sidebar getSidebar(){return sidebar;}

}
