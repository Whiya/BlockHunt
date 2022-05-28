package tokyo.ramune.blockhunt.player;

import org.bukkit.entity.FallingBlock;

import java.util.UUID;

public class User {

    private UUID player;
    private Role role;
    private FallingBlock hidingBlock;

    public User(UUID player, Role role) {
        this.player = player;
        this.role = role;
        hidingBlock = null;
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

    public FallingBlock getHidingBlock() {
        return hidingBlock;
    }

    public void setHidingBlock(FallingBlock hidingBlock) {
        this.hidingBlock = hidingBlock;
    }

    public boolean isBlockHiding() {
        return hidingBlock != null;
    }
}
