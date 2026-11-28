package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.behavior.IPathingBehavior;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ForceCancelCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Force cancel";
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("Like cancel, but more forceful.", "", "Usage:", "> forcecancel");
   }

   public ForceCancelCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"forcecancel"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      IPathingBehavior pathingBehavior = this.baritone.getPathingBehavior();
      pathingBehavior.cancelEverything();
      pathingBehavior.forceCancel();
      this.logDirect2("ok force canceled");
   }
}
