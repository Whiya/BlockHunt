package tokyo.ramune.blockhunt.info;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.GameHandler;

public class Bossbar {


    private JavaPlugin plugin;


    //initialize
    public Bossbar(JavaPlugin plugin){
        this.plugin = plugin;
        //TODO GameHandler.getTime()
        int time = 300;
        //TODO 残り時間をどっかから取得
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar add times \"残り時間 "+time+"秒\" ");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times max "+time);
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times visible true");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times players @a");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times value "+time);
    }

    //みえるように（ログイン時に実行）
    public void visit(Player player){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times players "+player.getName());
    }

    public void setbossbarTime(int now_time){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times value "+now_time);
    }


}
