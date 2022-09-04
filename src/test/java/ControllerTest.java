//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Facade;
import games.rednblack.puremvc.Notification;
import games.rednblack.puremvc.interfaces.INotification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test the PureMVC Controller class.
 *
 * @see ControllerTestVO
 * @see ControllerTestCommand
 */
public class ControllerTest {

    /**
     * Tests the Controller Singleton Factory Method
     */
    @Test
    public void testGetInstance() {
        // Test Factory Method
        Facade facade = Facade.getInstance();

        // test assertions
        Assertions.assertNotNull(facade, "Expecting instance not null");
    }

    /**
     * Tests Command registration and execution.
     *
     * <P>This test gets the Singleton Controller instance
     * and registers the ControllerTestCommand class
     * to handle 'ControllerTest' Notifications.</P>
     *
     * <P>It then constructs such a Notification and tells the
     * Controller to execute the associated Command.
     * Success is determined by evaluating a property
     * on an object passed to the Command, which will
     * be modified when the Command executes.</P>
     *
     */
    @Test
    public void testRegisterAndExecuteCommand() {
        // Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
        Facade facade = Facade.getInstance();
        facade.registerCommand("ControllerTest", new ControllerTestCommand());

        // Create a 'ControllerTest' note
        ControllerTestVO vo = new ControllerTestVO(12);
        INotification note = new Notification("ControllerTest", vo);

        // Tell the controller to execute the Command associated with the note
        // the ControllerTestCommand invoked will multiply the vo.input value
        // by 2 and set the result on vo.result
        facade.executeCommand(note);

        // test assertions
        Assertions.assertEquals(24, vo.result, "Expecting vo.result == 24");
    }

    /**
     * Tests Command registration and removal.
     *
     * <P>Tests that once a Command is registered and verified
     * working, it can be removed from the Controller.</P>
     */
    @Test
    public void testRegisterAndRemoveCommand() {
        // Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
        Facade facade = Facade.getInstance();
        facade.registerCommand("ControllerRemoveTest", new ControllerTestCommand());

        // Create a 'ControllerTest' note
        ControllerTestVO vo = new ControllerTestVO(12);
        INotification note = new Notification("ControllerRemoveTest", vo);

        // Tell the controller to execute the Command associated with the note
        // the ControllerTestCommand invoked will multiply the vo.input value
        // by 2 and set the result on vo.result
        facade.executeCommand(note);

        // test assertions
        Assertions.assertEquals(24, vo.result, "Expecting vo.result == 24");

        // Reset result
        vo.result = 0;

        // Remove the Command from the Controller
        facade.removeCommand("ControllerRemoveTest");

        // Tell the controller to execute the Command associated with the
        // note. This time, it should not be registered, and our vo result
        // will not change
        facade.executeCommand(note);

        // test assertions
        Assertions.assertEquals(0, vo.result, "Expecting vo.result == 0");
    }

    /**
     * Test hasCommand method.
     */
    @Test
    public void testHasCommand() {
        // register the ControllerTestCommand to handle 'hasCommandTest' notes
        Facade facade = Facade.getInstance();
        facade.registerCommand("hasCommandTest", new ControllerTestCommand());

        // test that hasCommand returns true for hasCommandTest notifications
        Assertions.assertTrue(facade.hasCommand("hasCommandTest"), "Expecting controller.hasCommand('hasCommandTest') == true");

        // Remove the Command from the Controller
        facade.removeCommand("hasCommandTest");

        // test that hasCommand returns false for hasCommandTest notifications
        Assertions.assertFalse(facade.hasCommand("hasCommandTest"), "Expecting controller.hasCommand('hasCommandTest') == false");
    }

    /**
     * Tests Removing and Reregistering a Command
     *
     * <P>Tests that when a Command is re-registered that it isn't fired twice.
     * This involves, minimally, registration with the controller but
     * notification via the View, rather than direct execution of
     * the Controller's executeCommand method as is done above in
     * testRegisterAndRemove.</P>
     */
    @Test
    public void testReRegisterAndExecuteCommand() {
        // Fetch the controller, register the ControllerTestCommand2 to handle 'ControllerTest2' notes
        Facade facade = Facade.getInstance();
        facade.registerCommand("ControllerTest2", new ControllerTestCommand2());

        // Remove the Command from the Controller
        facade.removeCommand("ControllerTest2");

        // Re-register the Command with the Controller
        facade.registerCommand("ControllerTest2", new ControllerTestCommand2());

        // Create a 'ControllerTest2' note
        ControllerTestVO vo = new ControllerTestVO(12);
        INotification note = new Notification("ControllerTest2", vo);

        // send the Notification
        facade.sendNotification(note);

        // test assertions
        // if the command is executed once the value will be 24
        Assertions.assertEquals(24, vo.result, "Expecting vo.result == 24");

        // Prove that accumulation works in the VO by sending the notification again
        facade.sendNotification(note);

        // if the command is executed twice the value will be 72
        Assertions.assertEquals(72, vo.result, "Expecting vo.result == 72");
    }

}