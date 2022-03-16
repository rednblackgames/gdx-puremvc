package games.rednblack.puremvc.interfaces;

public interface ICommand extends INotifiable {
    void execute(INotification notification);
}
