package baritone.api.command.exception;

public abstract class CommandException extends Exception implements ICommandException {
   public CommandException(String str, Throwable t) {
      super(str, t);
   }

   public CommandException(String str) {
      super(str);
   }
}
