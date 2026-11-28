package baritone.api.command.exception;

public abstract class CommandErrorMessageException extends CommandException {
   public CommandErrorMessageException(String str, Throwable t) {
      super(str, t);
   }

   public CommandErrorMessageException(String str) {
      super(str);
   }
}
