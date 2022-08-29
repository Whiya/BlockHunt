package tokyo.ramune.blockhunt.game.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import tokyo.ramune.blockhunt.BlockHunt;

public class HuntPlayer {
    private final Player player;
    private HuntPlayerRole role;
    private Block selectedBlock;
    private boolean isHiding;

    public HuntPlayer(Player player) {
        this.role = HuntPlayerRole.AWAIT;
        this.selectedBlock = null;
        this.isHiding = false;
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public HuntPlayerRole getRole() {
        return this.role;
    }

    public void setRole(HuntPlayerRole role) {
        this.role = role;
        this.player.sendMessage("デバッグ: " + role.name() + "のRoleになりました");
        BlockHunt.getPrefixManager().applyPrefix(this);
    }

    public boolean isHiding() {
        return this.isHiding;
    }

    public void setHide(boolean enable) {
        if (this.selectedBlock != null) {
            this.isHiding = enable;
        }
    }

    public Block getSelectedBlock() {
        return this.selectedBlock;
    }

    public void setSelectedBlock(Block selectedBlock) {
        this.selectedBlock = selectedBlock;
    }
}
