package cc.baritone.utils;

import cc.baritone.api.ExtendedItemEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AuxiliaryUsageManager extends ExtendedItemEntry {
   public final boolean[] enabledArray;
   public final PauseManager pauseManager;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList("The resume command tells Baritone to resume whatever it was doing when you last used pause.", "", "Usage:", "> resume");
   }

   @Override
   public String getLabelText2() {
      return "Resumes Baritone after a pause";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      this.activeListener.getSchematicsHandler().updateState2();
      if (!this.enabledArray[0]) {
         throw new InternalClientManager("Not paused");
      } else {
         this.enabledArray[0] = false;
         this.applyText("Resumed");
      }
   }

   public AuxiliaryUsageManager(PauseManager pauseManager2, ActiveListener activeListener, String[] textArray, boolean[] flagArray) {
      super(activeListener, textArray);
      this.pauseManager = pauseManager2;
      this.enabledArray = flagArray;
   }
}
