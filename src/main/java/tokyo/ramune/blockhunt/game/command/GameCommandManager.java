package tokyo.ramune.blockhunt.game.command;

import java.util.ArrayList;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.NonNull;
import tokyo.ramune.blockhunt.BlockHunt;
import tokyo.ramune.blockhunt.game.command.subcommand.*;

public class GameCommandManager {
    private static final ArrayList<SubCommand> subCommands = new ArrayList();

    public GameCommandManager() {
        BlockHunt.getPlugin().getCommand("blockhunt").setExecutor(new GameCommandExecutor());
        BlockHunt.getPlugin().getCommand("blockhunt").setTabCompleter(new GameTabCompleterHandler());
        this.registerSubCommand(new HelpCommand());
        this.registerSubCommand(new StartCommand());
        this.registerSubCommand(new StopCommand());
        this.registerSubCommand(new ChangeTimeCommand());
        this.registerSubCommand(new ChangeDaemonCommand());
        this.registerSubCommand(new DetectRolesCommand());
        this.registerSubCommand(new GiveGameItemCommand());
        this.registerSubCommand(new ChangeSpawnLoc());
        this.registerSubCommand(new ChangeStartLoc());
        this.registerSubCommand(new ChangeSpectatorLoc());
    }

    public SubCommand[] getSubCommands() {
        return (SubCommand[])subCommands.toArray(new SubCommand[0]);
    }

    public SubCommand getSubCommand(@NonNull String subCommand) {
        Iterator var2 = subCommands.iterator();

        SubCommand command;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            command = (SubCommand)var2.next();
        } while(!command.getSubCommand().equals(subCommand));

        return command;
    }

    private void registerSubCommand(SubCommand command) {
        subCommands.add(command);
    }
}
