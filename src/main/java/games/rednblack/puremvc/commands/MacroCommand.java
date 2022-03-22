package games.rednblack.puremvc.commands;

import com.badlogic.gdx.utils.SnapshotArray;

import games.rednblack.puremvc.Command;
import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;

public class MacroCommand extends Command {
    private final SnapshotArray<ICommand> subCommands;

    public MacroCommand() {
        subCommands = new SnapshotArray<>(ICommand.class);
        initializeMacroCommand();
    }

    protected void initializeMacroCommand() {
    }

    protected final void addSubCommand(ICommand command) {
        subCommands.add(command);
    }

    @Override
    public final void execute(INotification notification) {
        ICommand[] commands = subCommands.begin();
        for (int i = 0; i < subCommands.size; i++) {
            commands[i].execute(notification);
        }
        subCommands.end();
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}
