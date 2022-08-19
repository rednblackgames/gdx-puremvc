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

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import games.rednblack.puremvc.Command;
import games.rednblack.puremvc.interfaces.ICommand;
import games.rednblack.puremvc.interfaces.INotification;

/**
 * Wrapper {@link Command} that instantiate and execute commands with reflection.
 *
 * @author fgnm
 */

public class ReflectionCommand extends Command {
    protected Class<? extends ICommand> command;

    public ReflectionCommand(Class<? extends ICommand> commandClass) {
        command = commandClass;
    }

    @Override
    public void execute(INotification notification) {
        try {
            ICommand iCommand = (ICommand) ClassReflection.getConstructor(command).newInstance();
            iCommand.execute(notification);
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}
