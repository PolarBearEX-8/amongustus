package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalXZ;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ThisWayCommand extends Command {

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "Travel in your current direction";
   }

   public ThisWayCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"thisway", "forward"});
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireExactly(1);
      GoalXZ goalXZ = GoalXZ.fromDirection(this.ctx.playerFeetAsVec(), this.ctx.player().rotationYawHead, (Double)argConsumer.getAs(Double.class));
      this.baritone.getCustomGoalProcess().setGoal(goalXZ);
      this.logDirect2(String.format("Goal: %s", goalXZ));
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "Creates a GoalXZ some amount of blocks in the direction you're currently looking",
         "",
         "Usage:",
         "> thisway <distance> - makes a GoalXZ distance blocks in front of you"
      );
   }
}
