package tokyo.ramune.blockhunt.player;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;

import java.util.UUID;

public class User {

    private UUID player;
    private Role role;
    private Block targetBlock;
    private boolean isHiding;

    public User(UUID player, Role role) {
        this.player = player;
        this.role = role;
        targetBlock = null;
        isHiding = false;
    }

    public UUID getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Block getTargetBlock() {
        return targetBlock;
    }

    public void setTargetBlock(Block targetBlock) {
        this.targetBlock = targetBlock;
    }

    public void setHiding(boolean isHiding) {
        this.isHiding = isHiding;
    }

    public boolean isHiding() {
        return isHiding;
    }
}
