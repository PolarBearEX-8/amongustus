package baritone.command.defaults;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RepackCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Re-cache chunks";
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("Repack chunks around you. This basically re-caches them.", "", "Usage:", "> repack - Repack chunks.");
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.logDirect2(String.format("Queued %d chunks for repacking", BaritoneAPI.getProvider().getInstance3().repack(this.ctx)));
   }

   public RepackCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"repack", "rescan"});
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }
}
