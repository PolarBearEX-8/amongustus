package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ComeCommand extends Command {

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.baritone.getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.ctx.viewerPos()));
      this.logDirect2("Coming");
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "Start heading towards your camera";
   }

   public ComeCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"come"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The come command tells Baritone to head towards your camera.",
         "",
         "This can be useful in hacked clients where freecam doesn't move your player position.",
         "",
         "Usage:",
         "> come"
      );
   }
}
