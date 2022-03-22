
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
public class ViewTestMediator6 extends Mediator<ViewTest> {

    /**
     * The Mediator base name
     */
    public static final String NAME = "ViewTestMediator6";

    /**
     * Constructor
     *
     * @param name mediator name
     * @param view view object
     */
    public ViewTestMediator6(String name, ViewTest view) {
        super(name, view);
    }

    @Override
    public void listNotificationInterests(Interests interests) {
        interests.add(ViewTest.NOTE6);
    }

    @Override
    public void handleNotification(INotification notification) {
        facade.removeMediator(getName());
    }

    @Override
    public void onRemove() {
        getViewComponent().counter++;
    }
}