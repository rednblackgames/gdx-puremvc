//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Mediator;
import games.rednblack.puremvc.util.Interests;

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
public class ViewTestMediator extends Mediator<ViewTest> {

    /**
     * The Mediator name
     */
    public static final String NAME = "ViewTestMediator";

    /**
     * Constructor
     *
     * @param view view object
     */
    public ViewTestMediator(ViewTest view) {
        super (NAME, view);
    }

    @Override
    public void listNotificationInterests(Interests interests) {
        interests.add("ABC", "DEF", "GHI");
    }
}