package client.util;

import client.event.PreMotionProcessor;
import java.util.Comparator;

public class ComparatorHandler implements Comparator {
   public final ExtendedManager extendedManager;

   public int computeCount(PreMotionProcessor preMotionProcessor, PreMotionProcessor preMotionProcessor2) {
      return preMotionProcessor instanceof AuxiliaryListenerDispatcher && preMotionProcessor2 instanceof InternalListenerProvider
         ? 1
         : (
            preMotionProcessor2 instanceof AuxiliaryListenerDispatcher && preMotionProcessor instanceof InternalListenerProvider
               ? -1
               : (preMotionProcessor2.size() < preMotionProcessor.size() ? -1 : (preMotionProcessor2.size() > preMotionProcessor.size() ? 1 : 0))
         );
   }

   public ComparatorHandler(ExtendedManager extendedManager2) {
      this.extendedManager = extendedManager2;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((PreMotionProcessor)entry, (PreMotionProcessor)entry2);
   }
}
