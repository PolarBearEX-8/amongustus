package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.exception.CommandInvalidStateException;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalInverted;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InvertCommand extends Command {

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "Run away from the current goal";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      ICustomGoalProcess customGoalProcess = this.baritone.getCustomGoalProcess();
      Goal goal;
      if ((goal = customGoalProcess.getGoal()) == null) {
         throw new CommandInvalidStateException("No goal");
      } else {
         if (goal instanceof GoalInverted) {
            goal = ((GoalInverted)goal).origin;
         } else {
            goal = new GoalInverted(goal);
         }

         customGoalProcess.setGoalAndPath(goal);
         this.logDirect2(String.format("Goal: %s", goal.toString()));
      }
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The invert command tells Baritone to head away from the current goal rather than towards it.", "", "Usage:", "> invert - Invert the current goal."
      );
   }

   public InvertCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"invert"});
   }
}
