//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Facade;
import games.rednblack.puremvc.Mediator;
import games.rednblack.puremvc.interfaces.IMediator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test the PureMVC View class.
 */
public class ViewTest {

    public String lastNotification;
    public boolean onRegisterCalled = false;
    public boolean onRemoveCalled = false;
    public int counter = 0;

    public static final String NOTE1 = "Notification1";
    public static final String NOTE2 = "Notification2";
    public static final String NOTE3 = "Notification3";
    public static final String NOTE4 = "Notification4";
    public static final String NOTE5 = "Notification5";
    public static final String NOTE6 = "Notification6";

    /**
     * Tests the View Singleton Factory Method
     */
    @Test
    public void testGetInstance() {
        // Test Factory Method
        Facade facade = Facade.getInstance();

        // test assertions
        Assertions.assertNotNull(facade, "Expecting instance not null");
    }

    /**
     * Tests registering and retrieving a mediator with
     * the View.
     */
    @Test
    public void testRegisterAndRetrieveMediator() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator
        ViewTestMediator viewTestMediator = new ViewTestMediator(this);
        facade.registerMediator(viewTestMediator);

        // Retrieve the component
        IMediator mediator = facade.retrieveMediator(ViewTestMediator.NAME);

        // test assertions
        Assertions.assertNotNull(mediator, "Expecting mediator is ViewTestMediator");
    }

    /**
     * Tests the hasMediator Method
     */
    @Test
    public void testHasMediator() {
        // register a Mediator
        Facade facade = Facade.getInstance();

        // Create and register the test mediator
        Mediator<ViewTest> mediator = new Mediator<>("hasMediatorTest", this);
        facade.registerMediator(mediator);

        // assert that the view.hasMediator method returns true
        // for that mediator name
        Assertions.assertTrue(facade.hasMediator("hasMediatorTest"), "Expecting view.hasMediator('hasMediatorTest') == true");

        facade.removeMediator("hasMediatorTest");

        // assert that the view.hasMediator method returns false
        // for that mediator name
        Assertions.assertEquals(false, facade.hasMediator("hasMediatorTest"), "Expecting view.hasMediator('hasMediatorTest') == false");
    }

    /**
     * Tests registering and removing a mediator
     */
    @Test
    public void testRegisterAndRemoveMediator() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator
        IMediator mediator = new Mediator<>("testing", this);
        facade.registerMediator(mediator);

        // Remove the component
        IMediator removedMediator = facade.removeMediator("testing");

        // assert that we have removed the appropriate mediator
        Assertions.assertSame("testing", removedMediator.getName(), "Expecting removedMediator.getMediatorName() == 'testing'");

        // assert that the mediator is no longer retrievable
        Assertions.assertNull(facade.retrieveMediator("testing"), "Expecting view.retrieveMediator('testing') == null");
    }

    /**
     * Tests that the View callse the onRegister and onRemove methods
     */
    @Test
    public void testOnRegisterAndOnRemove() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator
        IMediator mediator = new ViewTestMediator4(this);
        facade.registerMediator(mediator);

        // assert that onRegsiter was called, and the mediator responded by setting our boolean
        Assertions.assertTrue(onRegisterCalled, "Expecting onRegisterCalled == true");

        // Remove the component
        facade.removeMediator(ViewTestMediator4.NAME);

        // assert that the mediator is no longer retrievable
        Assertions.assertTrue(onRemoveCalled, "Expecting onRemoveCalled == true");
    }

    /**
     * Tests successive regster and remove of same mediator.
     */
    @Test
    public void testSuccessiveRegisterAndRemoveMediator() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator,
        // but not so we have a reference to it
        facade.registerMediator(new ViewTestMediator(this));

        // test that we can retrieve it
        Assertions.assertNotNull(facade.retrieveMediator(ViewTestMediator.NAME), "Expecting view.retrieveMediator(ViewTestMediator.NAME) is ViewTestMediator");

        // Remove the Mediator
        facade.removeMediator(ViewTestMediator.NAME);

        // test that retrieving it now returns null
        Assertions.assertNull(facade.retrieveMediator(ViewTestMediator.NAME), "Expecting view.retrieveMediator(ViewTestMediator.NAME) == null");

        // test that removing the mediator again once its gone doesn't cause crash
        Assertions.assertNull(facade.removeMediator(ViewTestMediator.NAME), "Expecting view.removeMediator(ViewTestMediator.NAME) doesn't crash");

        // Create and register another instance of the test mediator,
        facade.registerMediator(new ViewTestMediator(this));

        Assertions.assertNotNull(facade.retrieveMediator(ViewTestMediator.NAME), "Expecting view.retrieveMediator(ViewTestMediator.NAME) is ViewTestMediator");

        // Remove the Mediator
        facade.removeMediator(ViewTestMediator.NAME);

        // test that retrieving it now returns null
        Assertions.assertNull(facade.retrieveMediator(ViewTestMediator.NAME), "Expecting view.retrieveMediator( ViewTestMediator.NAME ) == null");
    }

    /**
     * Tests registering a Mediator for 2 different notifications, removing the
     * Mediator from the View, and seeing that neither notification causes the
     * Mediator to be notified.
     */
    @Test
    public void testRemoveMediatorAndSubsequentNotify() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator to be removed.
        facade.registerMediator(new ViewTestMediator2(this));

        // test that notifications work
        facade.sendNotification(NOTE1);
        Assertions.assertSame(NOTE1, lastNotification, "Expecting lastNotification == NOTE1");

        facade.sendNotification(NOTE2);
        Assertions.assertSame(NOTE2, lastNotification, "Expecting lastNotification == NOTE2");

        // Remove the Mediator
        facade.removeMediator(ViewTestMediator2.NAME);

        // test that retrieving it now returns null
        Assertions.assertNull(facade.retrieveMediator(ViewTestMediator2.NAME), "Expecting view.retrieveMediator((ViewTestMediator2.NAME) == null)");

        // test that notifications no longer work
        // (ViewTestMediator2 is the one that sets lastNotification
        // on this component, and ViewTestMediator)
        lastNotification = null;

        facade.sendNotification(NOTE1);
        Assertions.assertNotSame(NOTE1, lastNotification, "Expecting lastNotification != NOTE1");

        facade.sendNotification(NOTE2);
        Assertions.assertNotSame(NOTE2, lastNotification, "Expecting lastNotification != NOTE2");
    }

    /**
     * Tests registering one of two registered Mediators and seeing
     * that the remaining one still responds.
     */
    @Test
    public void testRemoveOneOfTwoMediatorsAndSubsequentNotify() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register that responds to notifications 1 and 2
        facade.registerMediator(new ViewTestMediator2(this));

        // Create and register that responds to notification 3
        facade.registerMediator(new ViewTestMediator3(this));

        // test that all notifications work
        facade.sendNotification(NOTE1);
        Assertions.assertSame(NOTE1, lastNotification, "Expecting lastNotification == NOTE1");

        facade.sendNotification(NOTE2);
        Assertions.assertSame(NOTE2, lastNotification, "Expecting lastNotification == NOTE2");

        facade.sendNotification(NOTE3);
        Assertions.assertSame(NOTE3, lastNotification, "Expecting lastNotification == NOTE3");

        // Remove the Mediator that responds to 1 and 2
        facade.removeMediator(ViewTestMediator2.NAME);

        // test that retrieving it now returns null
        Assertions.assertNull(facade.retrieveMediator(ViewTestMediator2.NAME), "Expecting view.retrieveMediator(ViewTestMediator2.NAME) == null");

        // test that notifications no longer work
        // for notifications 1 and 2, but still work for 3
        lastNotification = null;

        facade.sendNotification(NOTE1);
        Assertions.assertNotSame(NOTE1, lastNotification, "Expecting lastNotification != NOTE1");

        facade.sendNotification(NOTE2);
        Assertions.assertNotSame(NOTE2, lastNotification, "Expecting lastNotification != NOTE2");

        facade.sendNotification(NOTE3);
        Assertions.assertSame(NOTE3, lastNotification, "Expecting lastNotification != NOTE3");
    }

    /**
     * Tests registering the same mediator twice.
     * A subsequent notification should only illicit
     * one response. Also, since reregistration
     * was causing 2 observers to be created, ensure
     * that after removal of the mediator there will
     * be no further response.
     *
     */
    @Test
    public void testMediatorRegistration() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register that responds to notification 5
        facade.registerMediator(new ViewTestMediator5(this));

        // try to register another instance of that mediator (uses the same NAME constant).
        facade.registerMediator(new ViewTestMediator5(this));

        // test that the counter is only incremented once (mediator 5's response)
        counter = 0;
        facade.sendNotification(NOTE5);
        Assertions.assertEquals(1, counter, "Expecting counter == 1");

        // Remove the Mediator
        facade.removeMediator(ViewTestMediator5.NAME);

        // test that retrieving it now returns null
        Assertions.assertNull(facade.retrieveMediator(ViewTestMediator5.NAME), "Expecting view.retrieveMediator(ViewTestMediato5.NAME) == null");

        // test that the counter is no longer incremented
        counter = 0;
        facade.sendNotification(NOTE5);
        Assertions.assertEquals(0, counter, "Expecting counter == 1");
    }

    /**
     * Tests the ability for the observer list to
     * be modified during the process of notification,
     * and all observers be properly notified. This
     * happens most often when multiple Mediators
     * respond to the same notification by removing
     * themselves.
     *
     */
    @Test
    public void testModifyObserverListDuringNotification() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register several mediator instances that respond to notification 6
        // by removing themselves, which will cause the observer list for that notification
        // to change.
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/1", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/2", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/3", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/4", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/5", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/6", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/7", this));
        facade.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/8", this));

        // clear the counter
        counter = 0;

        // send the notification. each of the above mediators will respond by removing
        // themselves and incrementing the counter by 1. This should leave us with a
        // count of 8, since 8 mediators will respond.
        facade.sendNotification(NOTE6);

        // verify the count is correct
        Assertions.assertEquals(8, counter, "Expecting counter == 8");

        // clear the counter
        counter = 0;
        facade.sendNotification(NOTE6);
        // verify the count is 0
        Assertions.assertEquals(0, counter, "Expecting counter == 0");
    }
}