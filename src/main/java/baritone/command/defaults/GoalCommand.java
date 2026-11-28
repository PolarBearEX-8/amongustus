package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.RelativeCoordinate;
import baritone.api.command.datatypes.RelativeGoal;
import baritone.api.command.exception.CommandException;
import baritone.api.command.helpers.TabCompleteHelper;
import baritone.api.pathing.goals.Goal;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.utils.BetterBlockPos;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GoalCommand extends Command {

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      ICustomGoalProcess customGoalProcess = this.baritone.getCustomGoalProcess();
      if (argConsumer.hasAny() && Arrays.asList("reset", "clear", "none").contains(argConsumer.peekString())) {
         argConsumer.requireMax(1);
         if (customGoalProcess.getGoal() != null) {
            customGoalProcess.setGoal(null);
            this.logDirect2("Cleared goal");
         } else {
            this.logDirect2("There was no goal to clear");
         }
      } else {
         argConsumer.requireMax(3);
         BetterBlockPos betterBlockPos = this.ctx.playerFeet();
         Goal datatypePost = (Goal)argConsumer.getDatatypePost(RelativeGoal.INSTANCE, betterBlockPos);
         customGoalProcess.setGoal(datatypePost);
         this.logDirect2(String.format("Goal: %s", datatypePost.toString()));
      }
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      TabCompleteHelper tabCompleteHelper = new TabCompleteHelper();
      if (argConsumer.hasExactlyOne()) {
         tabCompleteHelper.append2(new String[]{"reset", "clear", "none", "~"});
      } else if (argConsumer.hasAtMost(3)) {
         while (argConsumer.has(2) && argConsumer.peekDatatypeOrNull2(RelativeCoordinate.INSTANCE) != null) {
            argConsumer.get();
            if (!argConsumer.has(2)) {
               tabCompleteHelper.append2(new String[]{"~"});
            }
         }
      }

      return tabCompleteHelper.filterPrefix(argConsumer.getString()).getStream();
   }

   @Override
   public String getShortDesc() {
      return "Set or clear the goal";
   }

   public GoalCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"goal"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The goal command allows you to set or clear Baritone's goal.",
         "",
         "Wherever a coordinate is expected, you can use ~ just like in regular Minecraft commands. Or, you can just use regular numbers.",
         "",
         "Usage:",
         "> goal - Set the goal to your current position",
         "> goal <reset/clear/none> - Erase the goal",
         "> goal <y> - Set the goal to a Y level",
         "> goal <x> <z> - Set the goal to an X,Z position",
         "> goal <x> <y> <z> - Set the goal to an X,Y,Z position"
      );
   }
}
