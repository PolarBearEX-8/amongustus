package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GcCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("Calls System.gc().", "", "Usage:", "> gc");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      System.gc();
      this.applyText("ok called System.gc()");
   }

   @Override
   public String getLabelText2() {
      return "Call System.gc()";
   }

   public GcCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"gc"});
   }
}
