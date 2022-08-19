
//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.commands.SimpleCommand;
import games.rednblack.puremvc.interfaces.INotification;

/**
 * A SimpleCommand subclass used by ControllerTest.
 *
 * @see ControllerTest ControllerTest
 * @see ControllerTestVO ControllerTestVO
 */
public class ControllerTestCommand2 extends SimpleCommand {
    /*
    Use a multiplier to check command state. Commands not instantiated with reflection keep the state between executions
    while reflection commands have their own state
     */
    int mul = 1;

    /**
     * Fabricate a result by multiplying the input by 2 and adding to the existing result
     *
     * <P>This tests accumulation effect that would show if the command were executed more than once.</P>
     * @param notification the note carrying the ControllerTestVO
     */
    public void execute(INotification notification) {
        ControllerTestVO vo = notification.getBody();

        // Fabricate a result
        vo.result = vo.result + (2 * vo.input * mul);
        mul++;
    }
}