package tokyo.ramune.blockhunt.game.item;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Material;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.util.ItemStackBuilder;

public class GameItemResource {
    public GameItemResource() {
    }

    private static GameItem template() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setAmount(1).setTitle("tatitoru").setLore(new ArrayList(Arrays.asList("説明 1行", "説明 2行"))).addLore("これで行を追加").build()).setClickItemConsumer((event) -> {
            event.getPlayer().sendMessage("テンプレートをクリックしたよ!");
        }).build();
    }

    public static GameItem getSelectDaemonStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("お前鬼な棒 - プレイヤーを右クリックで利用").build()).setClickItemConsumer((event) -> {
            if (!BlockHunt.getGameManager().isStarted()) {
                ;
            }
        }).build();
    }

    public static GameItem getSelectRunnerStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("お前逃げな棒 - プレイヤーを右クリックで利用").build()).setClickItemConsumer((event) -> {
        }).build();
    }

    public static GameItem getSelectHideBlock() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("ブロック選択 - ブロックを右クリックで利用").build()).setClickItemConsumer((event) -> {

        }).build();
    }

    public static GameItem getHide() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("ブロックに隠れる - 右クリックで利用").build()).setClickItemConsumer((event) -> {
        }).build();
    }

    public static GameItem getGoldStick() {
        return (new GameItemBuilder()).setItemStack((new ItemStackBuilder()).setMaterial(Material.STICK).setTitle("金棒 - プレイヤーを左クリックで○す").build()).setClickItemConsumer((event) -> {
        }).build();
    }
}
