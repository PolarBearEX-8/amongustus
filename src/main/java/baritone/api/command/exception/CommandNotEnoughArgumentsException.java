package baritone.api.command.exception;

public class CommandNotEnoughArgumentsException extends CommandErrorMessageException {
   public CommandNotEnoughArgumentsException(int n) {
      super(String.format("Not enough arguments (expected at least %d)", n));
   }
}
