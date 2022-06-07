package tokyo.ramune.blockhunt.player;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.GameHandler;
import tokyo.ramune.blockhunt.util.Chat;

import java.util.UUID;

public class User {

    private UUID player;
    private Role role;
    private Block targetBlock;
    private Location holdBlockLocation;
    private FallingBlock fallingBlock;
    private boolean isHiding;

    public User(UUID player, Role role) {
        this.player = player;
        this.role = role;
        this.targetBlock = null;
        this.holdBlockLocation = null;
        this.fallingBlock = null;
        this.isHiding = false;
    }

    public UUID getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        TeamManager.removePlayer(Bukkit.getPlayer(player), role);
        setShow(Bukkit.getPlayer(player).getLocation());
        this.role = role;
        PlayerManager.initializeGameMode(Bukkit.getPlayer(player));
        PlayerManager.initializeInventory(Bukkit.getPlayer(player));
        TeamManager.addPlayer(Bukkit.getPlayer(player), role);
    }

    public Block getTargetBlock() {
        return targetBlock;
    }

    public void setTargetBlock(Block targetBlock) {
        this.targetBlock = targetBlock;
    }

    public Location getHoldBlockLocation() {
        return holdBlockLocation;
    }

    public void setHoldBlockLocation(Location holdBlockLocation) {
        this.holdBlockLocation = holdBlockLocation;
    }

    public void setHide(Block block) {
        Player bukkitPlayer = Bukkit.getPlayer(player);

        bukkitPlayer.setGameMode(GameMode.SPECTATOR);

        //パーティクル、サウンド、隠れ中を有効に
        bukkitPlayer.getWorld().spawnParticle(Particle.CLOUD, bukkitPlayer.getLocation(), 15, 0.3, 0.3, 0.3, 0);
        bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 , 1);
        for (Player bukkitOnlinePlayer : Bukkit.getOnlinePlayers()) {
            Bukkit.getPlayer(player).hidePlayer(BlockHunt.getPlugin(), bukkitOnlinePlayer);
        }
        this.isHiding = true;
    }

    public void setShow(Location hidingBlockLocation) {
        Player bukkitPlayer = Bukkit.getPlayer(player);

        if (!isHiding) {
            return;
        }
        bukkitPlayer.setGameMode(GameMode.ADVENTURE);
        for (Player bukkitOnlinePlayer : Bukkit.getOnlinePlayers()) {
            Bukkit.getPlayer(player).showPlayer(BlockHunt.getPlugin(), bukkitOnlinePlayer);
        }
        this.isHiding = false;
    }

    public FallingBlock getFallingBlock() {
        return fallingBlock;
    }

    public void setFallingBlock(FallingBlock fallingBlock) {
        this.fallingBlock = fallingBlock;
    }

    public boolean isHiding() {
        return isHiding;
    }
}
