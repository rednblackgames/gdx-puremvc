import games.rednblack.puremvc.Facade;
import games.rednblack.puremvc.commands.MacroCommand;
import games.rednblack.puremvc.commands.SimpleCommand;
import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacadeTest {
    private int macroCommandTest;

    @Test
    public void getInstance() {
        assertNotNull(Facade.getInstance());
    }

    @Test
    public void testCommand() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Facade facade = Facade.getInstance();

        facade.registerCommand("test", new SimpleCommand() {
            @Override
            public void execute(INotification notification) {
                assertEquals(notification.getName(), "test");
            }
        });

        facade.sendNotification("test");

        facade.registerCommand("macroTest", new MacroCommand() {

            private ICommand commandToRemove;

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
                commandToRemove = new SimpleCommand() {
                    @Override
                    public void execute(INotification notification) {
                        assertEquals(notification.getName(), "macroTest");
                        macroCommandTest++;
                    }
                };
                addSubCommand(commandToRemove);
            }

            public void removeTestCommand() {
                removeSubCommand(commandToRemove);
            }
        });

        facade.sendNotification("macroTest");

        assertEquals(macroCommandTest, 11);

        MacroCommand macroCommand = facade.retrieveCommand("macroTest");
        macroCommand.getClass().getDeclaredMethod("removeTestCommand").invoke(macroCommand);

        facade.sendNotification("macroTest");

        assertEquals(macroCommandTest, 21);
    }

    @Test
    public void sendNotification() {
    }
}