package net.augustus.events;

import net.augustus.events.listeners.ChunkUnloadListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventChunkUnload extends AbstractEvent<ChunkUnloadListener> {
   public static final int ID = 64;
   private long chunkKey;


   private void setChunkKey(long l) {
      this.chunkKey = l;
   }

   public static void reset(EventChunkUnload event, long l) {
      event.setChunkKey(l);
   }

   @Override
   public void call(ChunkUnloadListener listener) {
      listener.onChunkUnload(this.chunkKey);
   }
}
