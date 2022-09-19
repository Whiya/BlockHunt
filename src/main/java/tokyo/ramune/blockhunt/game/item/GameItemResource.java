package tokyo.ramune.blockhunt.game.item;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.player.HuntPlayer;
import tokyo.ramune.blockhunt.game.player.HuntPlayerRole;
import tokyo.ramune.blockhunt.util.ItemStackBuilder;

public class GameItemResource {
    public GameItemResource() {
    }

    public static GameItem template() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setAmount(1).setTitle("tatitoru").setLore(new ArrayList(Arrays.asList("説明 1行", "説明 2行"))).addLore("これで行を追加").build()).setClickItemConsumer((event) -> {
            event.getPlayer().sendMessage("テンプレートをクリックしたよ!");
        }).build();
    }

    public static GameItem getSelectDaemonStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("お前鬼な棒 - プレイヤーを右クリックで利用").build()).setClickItemConsumer((event) -> {
            Player targetPlayer = (Player) event.getPlayer().getTargetEntity(10);
            if (targetPlayer == null) {return;}
            event.getPlayer().sendMessage(targetPlayer.getName());
            if (BlockHunt.getGameManager().isStarted()) {
                event.getPlayer().sendMessage("お前鬼な棒だよ、ゲーム中だよ");
            }else{
                if (BlockHunt.getPlayerManager().getRole(targetPlayer).equals(HuntPlayerRole.DAEMON)){
                    event.getPlayer().sendMessage("既に鬼になってます");
                }else{
                    BlockHunt.getPlayerManager().setRole(targetPlayer, HuntPlayerRole.DAEMON);
                    event.getPlayer().sendMessage(targetPlayer.getName()+"を鬼にしました。");
                }
            }
        }).build();
    }

    public static GameItem getSelectRunnerStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("お前逃げな棒 - プレイヤーを右クリックで利用").build()).setClickItemConsumer((event) -> {
            Player targetPlayer = (Player) event.getPlayer().getTargetEntity(10);
            if (targetPlayer == null) {return;}
            if (BlockHunt.getGameManager().isStarted()) {
                event.getPlayer().sendMessage("お前逃げな棒だよ、ゲーム中だよ");
            }else{
                if (BlockHunt.getPlayerManager().getRole(targetPlayer).equals(HuntPlayerRole.RUNNER)){
                    event.getPlayer().sendMessage(ChatColor.RED+"既に逃げになってます");
                }else{
                    BlockHunt.getPlayerManager().setRole(targetPlayer, HuntPlayerRole.RUNNER);
                    event.getPlayer().sendMessage(ChatColor.AQUA+targetPlayer.getName()+"さんを"+ChatColor.BLUE+"逃げ"+ChatColor.WHITE+"にしました。");
                }
            }
        }).build();
    }

    public static GameItem getSelectHideBlock() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("ブロック選択 - ブロックを右クリックで利用").build()).setClickItemConsumer((event) -> {
            if (BlockHunt.getGameManager().isStarted()) {
                event.getPlayer().sendMessage("ブロック選択だよ、ゲーム中だよ");
            }else{
                event.getPlayer().sendMessage("ブロック選択だよ、ゲーム中じゃないよ");
            }
        }).build();
    }

    public static GameItem getHide() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("ブロックに隠れる - 右クリックで利用").build()).setClickItemConsumer((event) -> {
            if (BlockHunt.getGameManager().isStarted()) {
                event.getPlayer().sendMessage("ブロックに隠れるだよ、ゲーム中だよ");
            }else{
                event.getPlayer().sendMessage("ブロックに隠れるだよ、ゲーム中じゃないよ");
            }
        }).build();
    }

    public static GameItem getGoldStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("金棒 - プレイヤーを左クリックで○す").build()).setClickItemConsumer((event) -> {
            Player targetPlayer = (Player) event.getPlayer().getTargetEntity(10);
            if (targetPlayer == null) {return;}
            if (BlockHunt.getGameManager().isStarted()) {

                //鬼同士にはきかないように
                if (BlockHunt.getPlayerManager().getRole(targetPlayer).equals(HuntPlayerRole.DAEMON)){
                    return;
                }
                //つかまったら
                BlockHunt.getPlayerManager().setRole(targetPlayer, HuntPlayerRole.DAEMON);
                targetPlayer.remove();
                Bukkit.broadcastMessage(ChatColor.RED+event.getPlayer().getName()+"さんが"+ChatColor.BLUE+targetPlayer.getName()+"さんを捕まえました。");
            }else{
                event.getPlayer().sendMessage("金棒だよ、ゲーム中じゃないよ");
            }
        }).build();
    }
}
