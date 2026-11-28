package baritone.api.command.exception;

import baritone.api.command.argument.ICommandArgument;

public class CommandInvalidTypeException extends CommandInvalidArgumentException {

   public CommandInvalidTypeException(ICommandArgument commandArgument, String str, String str2, Throwable t) {
      super(commandArgument, String.format("Expected %s, but got %s instead", str, str2), t);
   }

   public CommandInvalidTypeException(ICommandArgument commandArgument, String str, Throwable t) {
      super(commandArgument, String.format("Expected %s", str), t);
   }

   public CommandInvalidTypeException(ICommandArgument commandArgument, String str) {
      super(commandArgument, String.format("Expected %s", str));
   }

   public CommandInvalidTypeException(ICommandArgument commandArgument, String str, String str2) {
      super(commandArgument, String.format("Expected %s, but got %s instead", str, str2));
   }
}
