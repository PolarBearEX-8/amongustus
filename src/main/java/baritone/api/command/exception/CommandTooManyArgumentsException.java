package baritone.api.command.exception;

public class CommandTooManyArgumentsException extends CommandErrorMessageException {
   public CommandTooManyArgumentsException(int n) {
      super(String.format("Too many arguments (expected at most %d)", n));
   }
}
