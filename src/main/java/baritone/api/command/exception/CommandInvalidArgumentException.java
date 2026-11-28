package baritone.api.command.exception;

import baritone.api.command.argument.ICommandArgument;

public abstract class CommandInvalidArgumentException extends CommandErrorMessageException {
   public final ICommandArgument arg;

   public CommandInvalidArgumentException(ICommandArgument commandArgument, String str) {
      super(formatMessage(commandArgument, str));
      this.arg = commandArgument;
   }

   public CommandInvalidArgumentException(ICommandArgument commandArgument, String str, Throwable t) {
      super(formatMessage(commandArgument, str), t);
      this.arg = commandArgument;
   }

   private static String formatMessage(ICommandArgument commandArgument, String str) {
      return String.format("Error at argument #%s: %s", commandArgument.getIndex() == -1 ? "<unknown>" : Integer.toString(commandArgument.getIndex() + 1), str);
   }
}
