package baritone.command.defaults;

import baritone.api.IBaritone;
import baritone.api.command.Command;
import baritone.api.command.exception.CommandException;
import baritone.api.command.argument.IArgConsumer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class VersionCommand extends Command {

   @Override
   public String getShortDesc() {
      return "View the Baritone version";
   }

   @Override
   public Stream tabComplete(String str, IArgConsumer argConsumer) {
      return Stream.empty();
   }

   public VersionCommand(IBaritone baritone2) {
      super(baritone2, new String[]{"version"});
   }

   @Override
   public void execute(String str, IArgConsumer argConsumer) throws CommandException {
      argConsumer.requireMax(0);
      this.logDirect2("You are running Baritone v1.2.19 (Hard-forked to Minecraft 1.8.9)");
      this.logDirect2("Please report any issues to florianmichael on discord. Thanks!");
   }

   @Override
   public List getLongDesc() {
      return Arrays.asList(
         "The version command prints the version of Baritone you're currently running.", "", "Usage:", "> version - View version information, if present"
      );
   }
}
