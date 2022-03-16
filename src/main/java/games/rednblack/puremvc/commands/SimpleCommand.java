package games.rednblack.puremvc.commands;

import games.rednblack.puremvc.Notifier;
import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;

public class SimpleCommand extends Notifier implements ICommand {

    @Override
    public void execute(INotification notification) {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}
