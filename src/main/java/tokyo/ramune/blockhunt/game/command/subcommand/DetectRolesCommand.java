package tokyo.ramune.blockhunt.game.command.subcommand;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;

public class DetectRolesCommand implements SubCommand {
    public DetectRolesCommand() {
    }

    public @NonNull String getSubCommand() {
        return "detect";
    }

    public @NonNull String getDescription() {
        return "参加してる人全員をランダムで鬼か逃げにするよ";
    }

    public void onCommand(@NonNull Player player, String[] args) {
        if (player.isOp()) {
            BlockHunt.getPlayerManager().detectRoles();
        }
    }
}
