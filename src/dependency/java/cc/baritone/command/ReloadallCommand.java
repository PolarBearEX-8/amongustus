package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReloadallCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The reloadall command reloads Baritone's world cache.", "", "Usage:", "> reloadall");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.staticListener.getNativeListener().getBreadthFirstPipeline().updateState2();
      this.applyText("Reloaded");
   }

   @Override
   public String getLabelText2() {
      return "Reloads Baritone's cache for this world";
   }

   public ReloadallCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"reloadall"});
   }
}
