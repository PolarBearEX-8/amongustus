package baritone.command.defaults;

import baritone.api.command.exception.CommandErrorMessageException;

public class NoEntitiesException extends CommandErrorMessageException {
   public NoEntitiesException() {
      super("No valid entities in range!");
   }
}
