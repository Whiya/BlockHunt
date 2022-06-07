package tokyo.ramune.blockhunt.info;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.blockhunt.BlockHunt;

public class Sidebar {

    private JavaPlugin plugin;

    public Sidebar(JavaPlugin plugin){
        this.plugin = BlockHunt.getPlugin();
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"scoreboard objectives add info dummy \"§9情報\"");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"scoreboard objectives setdisplay sidebar info");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"scoreboard players add 開発中 info 0");

    }



}
