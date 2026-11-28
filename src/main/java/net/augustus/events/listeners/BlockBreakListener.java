package net.augustus.events.listeners;


import net.augustus.events.EventBlockBreak;
import net.augustus.events.EventManager;
public interface BlockBreakListener {
   EventBlockBreak BLOCK_BREAK_EVENT = new EventBlockBreak();

   void onBlockBreak(EventBlockBreak event);

   static EventBlockBreak onBlockBreak() {
      BLOCK_BREAK_EVENT.setCancelled(false);
      EventManager.getEventBus().post(59, BLOCK_BREAK_EVENT);
      return BLOCK_BREAK_EVENT;
   }
}
