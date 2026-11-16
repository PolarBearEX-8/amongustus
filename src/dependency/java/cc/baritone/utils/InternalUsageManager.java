package cc.baritone.utils;

import cc.baritone.api.ExtendedItemEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InternalUsageManager extends ExtendedItemEntry {
   public final boolean[] enabledArray;
   public final PauseManager pauseManager;

   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The pause command tells Baritone to temporarily stop whatever it's doing.",
         "",
         "This can be used to pause pathing, building, following, whatever. A single use of the resume command will start it right back up again!",
         "",
         "Usage:",
         "> pause"
      );
   }

   @Override
   public String getLabelText2() {
      return "Pauses Baritone until you use resume";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      if (this.enabledArray[0]) {
         throw new InternalClientManager("Already paused");
      } else {
         this.enabledArray[0] = true;
         this.applyText("Paused");
      }
   }

   public InternalUsageManager(PauseManager pauseManager2, ActiveListener activeListener, String[] textArray, boolean[] flagArray) {
      super(activeListener, textArray);
      this.pauseManager = pauseManager2;
      this.enabledArray = flagArray;
   }
}
