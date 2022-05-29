package tokyo.ramune.blockhunt.player;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.blockhunt.util.Chat;

import java.util.ArrayList;
import java.util.Objects;
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

    public static User getPlayerFromHoldBlock(Location holdBlockLocation) {
        for (User user : users) {
            if (Objects.equals(holdBlockLocation, user.getHoldBlockLocation())) {
                return user;
            }
        }
        return null;
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

    private static void initializePlayer(UUID player) {
        if (!isExistsUser(player)) {
            users.add(new User(player, Role.RUNNER));
        }
    }

    public static void initializePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            getPlayer(player);
        }
    }

    public static void initializeInventory(Player player) {
        Inventory inv = player.getInventory();
        switch (getPlayer(player).getRole()) {
            case RUNNER:
                inv.setItem(26, RunnerItem.getTargetingBlock());
                inv.setItem(26, RunnerItem.getTargetingBlock());
                break;
            case DAEMON:
                break;
            case SPECTATOR:
                break;
            case NONE:
                break;
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
    public static void holdBlock(Player player, Block block) {
        holdBlock(player.getUniqueId(), block);
    }

    public static void holdBlock(UUID player, Block block) {
        Player bukkitPlayer = Bukkit.getPlayer(player);
        User user = getPlayer(player);
        if (getPlayer(player).getTargetBlock() == null) {
            Chat.sendMessage(bukkitPlayer, ChatColor.RED + "まだ隠れるブロックが選択されてないみたいだよ! 隠れるブロックを右クリックして選択しよう!!", true);
            bukkitPlayer.playSound(Bukkit.getPlayer(player).getLocation(), Sound.BLOCK_ANVIL_LAND, 1 , 1);
            return;
        }
        if (!bukkitPlayer.getLocation().getBlock().getType().equals(Material.AIR)) {
            Chat.sendMessage(bukkitPlayer, ChatColor.RED + "そこには隠れられないよ!! ブロックが重ならないところに隠れよう!!", true);
            bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1 , 1);
            return;
        }
        Location underBlock = bukkitPlayer.getLocation().clone().add(0, -1, 0);
        if (underBlock.getBlock().getType().equals(Material.AIR)) {
            Chat.sendMessage(bukkitPlayer, ChatColor.RED + "そこには隠れられないよ!! 隠れる下のブロックがありません!! ブロックの上に隠れてね", true);
            bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.BLOCK_ANVIL_LAND, 1 , 1);
            return;
        }
        bukkitPlayer.getWorld().setBlockData(bukkitPlayer.getLocation(), block.getBlockData());
        bukkitPlayer.getWorld().spawnParticle(Particle.CLOUD, bukkitPlayer.getLocation(), 15, 0.3, 0.3, 0.3, 0);
        bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 , 1);
        user.setHiding(true);
    }

    public static void removeHoldBlock(Player player, Location hidingBlockLocation) {
        removeHoldBlock(player.getUniqueId(), hidingBlockLocation);
    }

    public static void removeHoldBlock(UUID player, Location hidingBlockLocation) {
        Player bukkitPlayer = Bukkit.getPlayer(player);
        User user = getPlayer(player);
        if (user.isHiding()) {
            hidingBlockLocation.getBlock().setType(Material.AIR);
            user.setHiding(false);
            Chat.sendMessage(bukkitPlayer, ChatColor.RED + "隠れブロックから動いたため、隠れモードではなくなりました!!", true);
            bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
        }
    }
}
