package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.datatypes.RelativeGoalXZ;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExploreCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Explore things";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return argConsumer.hasAtMost(2) ? argConsumer.tabCompleteDatatype(RelativeGoalXZ.INSTANCE) : Stream.empty();
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "Tell Baritone to explore randomly. If you used explorefilter before this, it will be applied.",
         "",
         "Usage:",
         "> explore - Explore from your current position.",
         "> explore <x> <z> - Explore from the specified X and Z position."
      );
   }

   public ExploreCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"explore"});
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      if (argConsumer.hasAny()) {
         argConsumer.requireExactly(2);
      } else {
         argConsumer.requireMax(0);
      }

      GoalXZ goalXZ = argConsumer.hasAny() ? (GoalXZ)argConsumer.getDatatypePost(RelativeGoalXZ.INSTANCE, this.ctx.playerFeet()) : new GoalXZ(this.ctx.playerFeet());
      this.baritone.getExploreProcess().explore(goalXZ.getX(), goalXZ.getZ());
      this.logDirect2(String.format("Exploring from %s", goalXZ.toString()));
   }
}
