package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.CoreListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ForcecancelCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("Like cancel, but more forceful.", "", "Usage:", "> forcecancel");
   }

   @Override
   public String getLabelText2() {
      return "Force cancel";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      CoreListener coreListener = this.activeListener.getCoreListener();
      coreListener.isActive2();
      coreListener.updateState();
      this.applyText("ok force canceled");
   }

   public ForcecancelCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"forcecancel"});
   }
}
