package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class VersionCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The version command prints the version of Baritone you're currently running.", "", "Usage:", "> version - View version information, if present"
      );
   }

   @Override
   public String getLabelText2() {
      return "View the Baritone version";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.applyText("You are running Baritone v1.2.19 (Hard-forked to Minecraft 1.8.9)");
      this.applyText("Please report any issues to florianmichael on discord. Thanks!");
   }

   public VersionCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"version"});
   }
}
