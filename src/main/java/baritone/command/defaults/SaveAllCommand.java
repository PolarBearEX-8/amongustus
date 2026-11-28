package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SaveAllCommand extends Command {

   @Override
   public String getShortDesc() {
      return "Saves Baritone's cache for this world";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   public SaveAllCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"saveall"});
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList("The saveall command saves Baritone's world cache.", "", "Usage:", "> saveall");
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.ctx.worldData().getCachedWorld().save();
      this.logDirect2("Saved");
   }
}
