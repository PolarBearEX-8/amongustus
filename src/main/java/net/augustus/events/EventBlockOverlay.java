package net.augustus.events;

import net.augustus.events.listeners.BlockOverlayListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventBlockOverlay extends CancellableEvent<BlockOverlayListener> {
   public static final int ID = 4;

   @Override
   public void call(BlockOverlayListener listener) {
      listener.onBlockOverlay(this);
   }

}
