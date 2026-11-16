package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SaveallCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The saveall command saves Baritone's world cache.", "", "Usage:", "> saveall");
   }

   @Override
   public String getLabelText2() {
      return "Saves Baritone's cache for this world";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.staticListener.getNativeListener().getBreadthFirstPipeline().updateState();
      this.applyText("Saved");
   }

   public SaveallCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"saveall"});
   }
}
