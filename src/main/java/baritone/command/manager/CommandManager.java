package baritone.command.manager;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.command.ICommand;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.command.manager.ICommandManager;
import baritone.api.command.registry.Registry;
import baritone.command.argument.ArgConsumer;
import baritone.command.argument.CommandArguments;
import baritone.command.defaults.DefaultCommands;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.util.Tuple;

public class CommandManager implements ICommandManager {
   private final Registry registry = new Registry();
   private final Baritone baritone;

   @Override
   public boolean execute2(Tuple tuple) {
      ExecutionWrapper executionWrapper = this.from(tuple);
      if (executionWrapper != null) {
         ExecutionWrapper.execute(executionWrapper);
      }

      return executionWrapper != null;
   }

   @Override
   public Registry getRegistry() {
      return this.registry;
   }

   @Override
   public boolean execute(String str) {
      return this.execute2(expand(str));
   }

   private ExecutionWrapper from(Tuple tuple) {
      String first = (String)tuple.getFirst();
      ArgConsumer argConsumer = new ArgConsumer(this, (List)tuple.getSecond());
      ICommand command = this.getCommand(first);
      return command == null ? null : new ExecutionWrapper(command, first, argConsumer, null);
   }

   @Override
   public IBaritone getBaritone() {
      return this.baritone;
   }

   public CommandManager(Baritone baritone2) {
      this.baritone = baritone2;
      DefaultCommands.createAll(baritone2).forEach(this.registry::register);
   }

   @Override
   public Stream tabComplete(Tuple tuple) {
      ExecutionWrapper executionWrapper = this.from(tuple);
      return executionWrapper == null ? Stream.empty() : ExecutionWrapper.tabComplete(executionWrapper);
   }

   @Override
   public Stream tabComplete2(String str) {
      Tuple tuple = expand(str, true);
      String first = (String)tuple.getFirst();
      return ((List)tuple.getSecond()).isEmpty() ? new TabCompleteHelper().addCommands(this.baritone.getCommandManager2()).filterPrefix(first).getStream() : this.tabComplete(tuple);
   }

   public static Tuple expand(String str) {
      return expand(str, false);
   }

   private static Tuple expand(String str, boolean flag) {
      String str2 = str.split("\\s", 2)[0];
      List list = CommandArguments.from(str.substring(str2.length()), flag);
      return new Tuple(str2, list);
   }

   @Override
   public ICommand getCommand(String str) {
      for (ICommand command : (Iterable<ICommand>)(this.registry.registry)) {
         if (command.getNames().contains(str.toLowerCase(Locale.US))) {
            return command;
         }
      }

      return null;
   }


   public static class InnerEntry {
   }
}
