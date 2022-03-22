package games.rednblack.puremvc;

import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;

public abstract class Command extends Notifier implements ICommand {

    @Override
    public final void notify(INotification notification) {
        execute(notification);
    }

}
