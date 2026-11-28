package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalAxis;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AxisCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Set a goal to the axes";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      GoalAxis goalAxis = new GoalAxis();
      this.baritone.getCustomGoalProcess().setGoal(goalAxis);
      this.logDirect2(String.format("Goal: %s", goalAxis.toString()));
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   public AxisCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"axis", "highway"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("The axis command sets a goal that tells Baritone to head towards the nearest axis. That is, X=0 or Z=0.", "", "Usage:", "> axis");
   }
}
