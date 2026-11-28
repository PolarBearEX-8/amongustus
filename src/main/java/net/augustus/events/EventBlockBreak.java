package net.augustus.events;

import net.augustus.events.listeners.BlockBreakListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventBlockBreak extends CancellableEvent<BlockBreakListener> {
   public static final int ID = 59;


   @Override
   public void call(BlockBreakListener listener) {
      listener.onBlockBreak(this);
   }
}
