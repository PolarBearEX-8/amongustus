package net.augustus.events.listeners;


import net.augustus.events.EventBlockOverlay;
import net.augustus.events.EventManager;
public interface BlockOverlayListener {
   EventBlockOverlay BLOCK_OVERLAY_EVENT = new EventBlockOverlay();

   static EventBlockOverlay onBlockOverlay() {
      BLOCK_OVERLAY_EVENT.setCancelled(false);
      EventManager.getEventBus().post(4, BLOCK_OVERLAY_EVENT);
      return BLOCK_OVERLAY_EVENT;
   }

   void onBlockOverlay(EventBlockOverlay event);
}
