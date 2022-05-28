package tokyo.ramune.blockhunt.player;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.util.Chat;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {

    private static ArrayList<User> users = new ArrayList<>();

    public static User getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public static User getPlayer(UUID player) {
        if (!isExistsUser(player)) {
            initializePlayer(player);
        }
        for (User user : users) {
            if (user.getPlayer().equals(player)) {
                return user;
            }
        }
        return null;
    }

    public static User[] getPlayers() {
        return users.toArray(new User[0]);
    }

    public static void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }

    public static void removePlayer(UUID player) {
        if (isExistsUser(player)) {
            for (User user : users) {
                if (user.getPlayer().equals(player)) {
                    users.remove(user);
                }
            }
        }
    }

    public static User getPlayerFromFallingBlock(FallingBlock fallingBlock) {
        for (User user : users) {
            if (user.getHidingBlock().equals(fallingBlock)) {
                return user;
            }
        }
        return null;
    }

    private static void initializePlayer(UUID player) {
        if (!isExistsUser(player)) {
            users.add(new User(player, Role.RUNNER));
            // TODO !!!!!!!!!!!!!!!!!!!!!!
        }
    }

    public static void initializePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            getPlayer(player);
        }
    }

    public static boolean isExistsUser(Player player) {
        return isExistsUser(player.getUniqueId());
    }

    public static boolean isExistsUser(UUID player) {
        for (User user : users) {
            if (user.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    // ゲーム
    public static void hideBlock(Player player, BlockData block) {
        hideBlock(player.getUniqueId(), block);
    }

    public static void hideBlock(UUID player, BlockData block) {
        if (getPlayer(player).getHidingBlock() == null) {
            Chat.sendMessage(Bukkit.getPlayer(player), ChatColor.RED + "まだ隠れるブロックが選択されてないみたいだよ! 隠れるブロックを右クリックして選択しよう!!", true);
            Bukkit.getPlayer(player).playSound(Bukkit.getPlayer(player).getLocation(), Sound.BLOCK_ANVIL_LAND, 1 , 1);
            return;
        }
        User user = getPlayer(player);
        Player bukkitPlayer = Bukkit.getPlayer(user.getPlayer());
        if (!Bukkit.getPlayer(player).getLocation().getBlock().getType().equals(Material.AIR)) {
            Chat.sendMessage(bukkitPlayer, ChatColor.RED + "そこには隠れられないよ!! ブロックが重ならないところに隠れよう!!", true);
            Bukkit.getPlayer(player).playSound(bukkitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1 , 1);
            return;
        }
        bukkitPlayer.getLocation().getBlock().setBlockData(block);
        user.getHidingBlock().remove();
        user.setHidingBlock(null);
        bukkitPlayer.getWorld().spawnParticle(Particle.CLOUD, bukkitPlayer.getLocation(), 15, 0.3, 0.3, 0.3, 0);
        bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 , 1);
    }
}
