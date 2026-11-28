package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.pathing.calc.IPathingControlManager;
import baritone.api.process.IBaritoneProcess;
import baritone.api.process.PathingCommand;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProcCommand extends Command {

   public ProcCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"proc"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The proc command provides miscellaneous information about the process currently controlling Baritone.",
         "",
         "You are not expected to understand this if you aren't familiar with how Baritone works.",
         "",
         "Usage:",
         "> proc - View process information, if present"
      );
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      IPathingControlManager pathingControlManager = this.baritone.getPathingControlManager();
      IBaritoneProcess baritoneProcess = (IBaritoneProcess)pathingControlManager.mostRecentInControl().orElse(null);
      if (baritoneProcess == null) {
         throw new CommandInvalidStateException("No process in control");
      } else {
         this.logDirect2(
            String.format(
               "Class: %s\nPriority: %f\nTemporary: %b\nDisplay name: %s\nLast command: %s",
               baritoneProcess.getClass().getTypeName(),
               baritoneProcess.priority(),
               baritoneProcess.isTemporary(),
               baritoneProcess.displayName(),
               pathingControlManager.mostRecentCommand().map(obj -> ((PathingCommand)obj).toString()).orElse("None")
            )
         );
      }
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "View process state information";
   }
}
