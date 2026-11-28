package net.augustus.events.listeners;


import net.augustus.events.EventChunkLoad;
import net.augustus.events.EventManager;
public interface ChunkLoadListener {
   void onChunkLoad(EventChunkLoad event);

   static void onChunkLoad(int n, int n2) {
      EventManager.getEventBus().post(56, new EventChunkLoad(n, n2));
   }
}
