package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClickCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("Opens click dude", "", "Usage:", "> click");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.activeListener.start();
      this.applyText("aight dude");
   }

   @Override
   public String getLabelText2() {
      return "Open click";
   }

   public ClickCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"click"});
   }
}
