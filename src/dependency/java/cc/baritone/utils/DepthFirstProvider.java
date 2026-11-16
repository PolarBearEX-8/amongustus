package cc.baritone.utils;

import cc.baritone.api.InternalItemEntry;

public class DepthFirstProvider implements ExtendedListener {
   public final ListenerHandler listenerHandler;

   @Override
   public void applyInternalItemEntry(InternalItemEntry internalItemEntry) {
      if (internalItemEntry.getInternalOptionType() == InternalOptionType.IN) {
         ListenerHandler.applyListenerHandler(this.listenerHandler);
      }
   }

   public DepthFirstProvider(ListenerHandler listenerHandler2) {
      this.listenerHandler = listenerHandler2;
   }
}
