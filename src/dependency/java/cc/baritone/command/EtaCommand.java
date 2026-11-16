package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.api.INACTIVEHandler;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.CoreListener;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EtaCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The ETA command provides information about the estimated time until the next segment.",
         "and the goal",
         "",
         "Be aware that the ETA to your goal is really unprecise",
         "",
         "Usage:",
         "> eta - View ETA, if present"
      );
   }

   @Override
   public String getLabelText2() {
      return "View the current ETA";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      if ((INACTIVEHandler)this.activeListener.getLocalListener().ofNullableAsOptional().orElse(null) == null) {
         throw new InternalClientManager("No process in control");
      } else {
         CoreListener coreListener = this.activeListener.getCoreListener();
         double offset = ((double)coreListener.getOptionalAsOptional().orElse(Double.NaN));
         double offset2 = ((double)coreListener.getOptional().orElse(Double.NaN));
         this.applyText(String.format("Next segment: %.1fs (%.0f ticks)\nGoal: %.1fs (%.0f ticks)", offset / 20.0, offset, offset2 / 20.0, offset2));
      }
   }

   public EtaCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"eta"});
   }
}
