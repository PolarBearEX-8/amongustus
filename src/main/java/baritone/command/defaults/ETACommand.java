package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.pathing.calc.IPathingControlManager;
import baritone.api.process.IBaritoneProcess;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ETACommand extends Command {

   @Override
   public String getShortDesc() {
      return "View the current ETA";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      if (((IBaritoneProcess)this.baritone.getPathingControlManager().mostRecentInControl().orElse(null)) == null) {
         throw new CommandInvalidStateException("No process in control");
      } else {
         IPathingBehavior pathingBehavior = this.baritone.getPathingBehavior();
         double d = (Double)(pathingBehavior.ticksRemainingInSegment().orElse(Double.NaN));
         double d2 = (Double)(pathingBehavior.estimatedTicksToGoal().orElse(Double.NaN));
         this.logDirect2(String.format("Next segment: %.1fs (%.0f ticks)\nGoal: %.1fs (%.0f ticks)", d / 20.0, d, d2 / 20.0, d2));
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The ETA command provides information about the estimated time until the next segment.",
         "and the goal",
         "",
         "Be aware that the ETA to your goal is really unprecise",
         "",
         "Usage:",
         "> eta - View ETA, if present"
      );
   }

   public ETACommand(IBaritone baritone2) {
      super(baritone2, new String[]{"eta"});
   }
}
