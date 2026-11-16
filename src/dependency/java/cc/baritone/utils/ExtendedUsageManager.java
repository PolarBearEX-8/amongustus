package cc.baritone.utils;

import cc.baritone.api.ExtendedItemEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExtendedUsageManager extends ExtendedItemEntry {
   public final boolean[] enabledArray;
   public final PauseManager pauseManager;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The cancel command tells Baritone to stop whatever it's currently doing.", "", "Usage:", "> cancel");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      if (this.enabledArray[0]) {
         this.enabledArray[0] = false;
      }

      this.activeListener.getCoreListener().isActive2();
      this.applyText("ok canceled");
   }

   @Override
   public String getLabelText2() {
      return "Cancel what Baritone is currently doing";
   }

   public ExtendedUsageManager(PauseManager pauseManager2, ActiveListener activeListener, String[] textArray, boolean[] flagArray) {
      super(activeListener, textArray);
      this.pauseManager = pauseManager2;
      this.enabledArray = flagArray;
   }
}
