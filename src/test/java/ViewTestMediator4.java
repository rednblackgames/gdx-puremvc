//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Mediator;

/**
 * A Mediator class used by ViewTest.
 *
 * @see ViewTest ViewTest
 */
public class ViewTestMediator4 extends Mediator<ViewTest> {

    /**
     * The Mediator name
     */
    public static final String NAME = "ViewTestMediator4";

    /**
     * Constructor
     *
     * @param view view object
     */
    public ViewTestMediator4(ViewTest view) {
        super(NAME, view);
    }

    @Override
    public void onRegister() {
        getViewComponent().onRegisterCalled = true;
    }

    @Override
    public void onRemove() {
        getViewComponent().onRemoveCalled = true;
    }
}