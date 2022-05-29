package tokyo.ramune.blockhunt.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;

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
        this.role = role;
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

    public void setHiding(boolean isHiding) {
        this.isHiding = isHiding;
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
