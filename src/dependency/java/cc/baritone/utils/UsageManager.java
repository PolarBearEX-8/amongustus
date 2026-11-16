package cc.baritone.utils;

import cc.baritone.api.ExtendedItemEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UsageManager extends ExtendedItemEntry {
   public final boolean[] enabledArray;
   public final PauseManager pauseManager;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The paused command tells you if Baritone is currently paused by use of the pause command.", "", "Usage:", "> paused");
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.applyText(String.format("Baritone is %spaused", this.enabledArray[0] ? "" : "not "));
   }

   @Override
   public String getLabelText2() {
      return "Tells you if Baritone is paused";
   }

   public UsageManager(PauseManager pauseManager2, ActiveListener activeListener, String[] textArray, boolean[] flagArray) {
      super(activeListener, textArray);
      this.pauseManager = pauseManager2;
      this.enabledArray = flagArray;
   }
}
