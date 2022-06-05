package tokyo.ramune.blockhunt.info;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times color blue");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times style notched_6");

    }

    //みえるように（ログイン時に実行）
    public void visit(Player player){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times players "+player.getName());
    }

    //TODO残り時間実装の時についでにこいつも仕込む
    public void setbossbarTime(int now_time){
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times name \"残り時間 "+now_time+"秒\" ");
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),"bossbar set minecraft:times max "+now_time);
    }

}
