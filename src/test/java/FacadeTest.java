import games.rednblack.puremvc.Facade;
import games.rednblack.puremvc.commands.MacroCommand;
import games.rednblack.puremvc.commands.SimpleCommand;
import games.rednblack.puremvc.interfaces.INotification;
import org.junit.Test;

import static org.junit.Assert.*;

public class FacadeTest {
    private int macroCommandTest;

    @Test
    public void getInstance() {
        assertNotNull(Facade.getInstance());
    }

    @Test
    public void testCommand() {
        Facade facade = Facade.getInstance();

        facade.registerCommand("test", new SimpleCommand() {
            @Override
            public void execute(INotification notification) {
                assertEquals(notification.getName(), "test");
            }
        });

        facade.sendNotification("test");

        facade.registerCommand("macroTest", new MacroCommand() {
            @Override
            protected void initializeMacroCommand() {
                for (int i = 0; i < 10; i++) {
                    addSubCommand(new SimpleCommand() {
                        @Override
                        public void execute(INotification notification) {
                            assertEquals(notification.getName(), "macroTest");
                            macroCommandTest++;
                        }
                    });
                }
            }
        });

        facade.sendNotification("macroTest");

        assertEquals(macroCommandTest, 10);
    }

    @Test
    public void sendNotification() {
    }
}