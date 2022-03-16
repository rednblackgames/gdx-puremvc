package games.rednblack.puremvc.interfaces;

import games.rednblack.puremvc.util.Interests;

public interface IMediator extends INotifiable {
    String getName();

    void listNotificationInterests(Interests interests);

    void handleNotification(INotification notification);

    <T> T getViewComponent();
}
