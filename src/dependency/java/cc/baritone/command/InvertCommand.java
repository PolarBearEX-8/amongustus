package cc.baritone.command;

import cc.baritone.api.ExtendedItemEntry;
import cc.baritone.api.INACTIVEHandlerExtension;
import cc.baritone.utils.ActiveListener;
import cc.baritone.utils.BreadthFirstEstimator;
import cc.baritone.utils.DepthFirstManager;
import cc.baritone.utils.ExceptionAdapter;
import cc.baritone.utils.InternalClientManager;
import cc.baritone.utils.SecondaryListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InvertCommand extends ExtendedItemEntry {
   @Override
   public Stream empty(String text, DepthFirstManager depthFirstManager) {
      return Stream.empty();
   }

   @Override
   public List getList() {
      return Arrays.asList(
         "The invert command tells Baritone to head away from the current goal rather than towards it.", "", "Usage:", "> invert - Invert the current goal."
      );
   }

   @Override
   public String getLabelText2() {
      return "Run away from the current goal";
   }

   @Override
   public void applyText(String text, DepthFirstManager depthFirstManager) throws ExceptionAdapter {
      depthFirstManager.applyIndexByIndex(0);
      INACTIVEHandlerExtension iNACTIVEHandlerExtension = this.activeListener.getINACTIVEHandlerExtension();
      SecondaryListener secondaryListener;
      if ((secondaryListener = iNACTIVEHandlerExtension.getSecondaryListener()) == null) {
         throw new InternalClientManager("No goal");
      } else {
         Object entry;
         if (secondaryListener instanceof BreadthFirstEstimator) {
            entry = ((BreadthFirstEstimator)secondaryListener).secondaryListener;
         } else {
            entry = new BreadthFirstEstimator(secondaryListener);
         }

         iNACTIVEHandlerExtension.applySecondaryListenerBySecondaryListene((SecondaryListener)entry);
         this.applyText(String.format("Goal: %s", entry.toString()));
      }
   }

   public InvertCommand(ActiveListener activeListener) {
      super(activeListener, new String[]{"invert"});
   }
}
