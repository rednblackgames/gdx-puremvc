//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Mediator;
import games.rednblack.puremvc.interfaces.INotification;
import games.rednblack.puremvc.util.Interests;

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
public class ViewTestMediator3 extends Mediator<ViewTest> {

    /**
     * The Mediator name
     */
    public static final String NAME = "ViewTestMediator3";

    /**
     * Constructor
     *
     * @param view view object
     */
    public ViewTestMediator3(ViewTest view) {
        super(NAME, view);
    }

    @Override
    public void listNotificationInterests(Interests interests) {
        interests.add(ViewTest.NOTE3);
    }

    @Override
    public void handleNotification(INotification notification) {
        getViewComponent().lastNotification = notification.getName();
    }
}