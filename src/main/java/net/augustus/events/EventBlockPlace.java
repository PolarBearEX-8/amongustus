package net.augustus.events;

import net.augustus.events.listeners.BlockPlaceListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventBlockPlace extends CancellableEvent<BlockPlaceListener> {
   public static final int ID = 60;
   private BlockPlaceStage stage;

   @Override
   public void call(BlockPlaceListener listener) {
      if (this.stage == BlockPlaceStage.PRE) {
         listener.onPreBlockPlace(this);
      } else {
         listener.onPostBlockPlace(this);
      }
   }


   public void reset(BlockPlaceStage blockPlaceStage) {
      this.stage = blockPlaceStage;
      this.setCancelled(false);
   }

   public BlockPlaceStage getStage() {
      return this.stage;
   }
}
