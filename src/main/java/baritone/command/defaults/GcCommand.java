package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GcCommand extends Command {

   public GcCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"gc"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   @Override
   public String getShortDesc() {
      return "Call System.gc()";
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      System.gc();
      this.logDirect2("ok called System.gc()");
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("Calls System.gc().", "", "Usage:", "> gc");
   }
}
