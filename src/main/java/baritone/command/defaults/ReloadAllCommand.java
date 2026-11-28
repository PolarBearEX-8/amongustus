package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReloadAllCommand extends Command {

   @Override
   public List getLongDesc() {
      return Arrays.asList("The reloadall command reloads Baritone's world cache.", "", "Usage:", "> reloadall");
   }

   @Override
   public String getShortDesc() {
      return "Reloads Baritone's cache for this world";
   }

   public ReloadAllCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"reloadall"});
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.ctx.worldData().getCachedWorld().reloadAllFromDisk();
      this.logDirect2("Reloaded");
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }
}
