/*******************************************************************************
 * Copyright 2022 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package games.rednblack.puremvc.commands;

import com.badlogic.gdx.utils.SnapshotArray;

import games.rednblack.puremvc.Command;
import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;

/**
 * Basic implementation of a {@link Command} that executes other Commands in Batch
 *
 * @author fgnm
 */
public class MacroCommand extends Command {
    private final SnapshotArray<ICommand> subCommands;

    public MacroCommand() {
        subCommands = new SnapshotArray<>(ICommand.class);
        initializeMacroCommand();
    }

    protected void initializeMacroCommand() { }

    protected final void addSubCommand(ICommand command) {
        subCommands.add(command);
    }

    @Override
    public final void execute(INotification notification) {
        ICommand[] commands = subCommands.begin();
        for (int i = 0, n = subCommands.size; i < n; i++) {
            commands[i].execute(notification);
        }
        subCommands.end();
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}
