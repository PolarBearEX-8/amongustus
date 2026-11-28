package baritone.command.defaults;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.process.ICustomGoalProcess;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PathCommand extends Command {

   @Override
   public List getLongDesc() {
      return Arrays.asList("The path command tells Baritone to head towards the current goal.", "", "Usage:", "> path - Start the pathing.");
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      ICustomGoalProcess customGoalProcess = this.baritone.getCustomGoalProcess();
      argConsumer.requireMax(0);
      BaritoneAPI.getProvider().getInstance3().repack(this.ctx);
      customGoalProcess.path();
      this.logDirect2("Now pathing");
   }

   @Override
   public String getShortDesc() {
      return "Start heading towards the goal";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) throws CommandException {
      return Stream.empty();
   }

   public PathCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"path"});
   }
}
