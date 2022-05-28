package tokyo.ramune.blockhunt.listener;

import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerHandler {

    public ListenerHandler(JavaPlugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerInteractListener(), plugin);
        pm.registerEvents(new PlayerMoveListener(), plugin);
        pm.registerEvents(new PlayerSneakListener(), plugin);
        pm.registerEvents(new EntityDamageListener(), plugin);
        pm.registerEvents(new PlayerDeathListener(), plugin);
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new PlayerQuitListener(), plugin);
        pm.registerEvents(new EntityChangeBlockListener(), plugin);
    }
}