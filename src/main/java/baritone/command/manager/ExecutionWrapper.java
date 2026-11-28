package baritone.command.manager;

import baritone.api.command.ICommand;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandUnhandledException;
import baritone.api.command.exception.ICommandException;
import baritone.command.argument.ArgConsumer;
import java.util.stream.Stream;

public final class ExecutionWrapper {
   private ICommand command;
   private String label;
   private ArgConsumer args;

   public static void execute(ExecutionWrapper executionWrapper) {
      executionWrapper.execute();
   }

   public static Stream tabComplete(ExecutionWrapper executionWrapper) {
      return executionWrapper.tabComplete();
   }

   private ExecutionWrapper(ICommand command2, String str, ArgConsumer argConsumer) {
      this.command = command2;
      this.label = str;
      this.args = argConsumer;
   }

   public ExecutionWrapper(ICommand command2, String str, ArgConsumer argConsumer, CommandManager.InnerEntry obj) {
      this(command2, str, argConsumer);
   }

   private void execute() {
      try {
         this.command.execute(this.label, this.args);
      } catch (Throwable e) {
         ((ICommandException)(e instanceof ICommandException ? (ICommandException)e : new CommandUnhandledException(e))).handle(this.command, this.args.getArgs());
      }
   }

   private Stream tabComplete() {
      try {
         return this.command.tabComplete(this.label, this.args);
      } catch (CommandException e) {
      } catch (Throwable e2) {
         e2.printStackTrace();
      }

      return Stream.empty();
   }
}
