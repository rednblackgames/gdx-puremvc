//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

/**
 * A utility class used by ControllerTest.
 *
 * @see ControllerTest
 * @see ControllerTestCommand
 */
public class ControllerTestVO {
    int input;
    int result;

    /**
     * Constructor.
     *
     * @param input the number to be fed to the ControllerTestCommand
     */
    ControllerTestVO(int input) {
        this.input = input;
    }
}
