package baritone.api.command.helpers;

import baritone.api.command.exception.CommandException;

public interface CommandFunction {
   Object apply(Object obj) throws CommandException;
}
