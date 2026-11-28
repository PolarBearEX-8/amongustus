package net.augustus.events;

import net.augustus.events.listeners.ChunkLoadListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventChunkLoad extends AbstractEvent<ChunkLoadListener> {
   public static final int ID = 56;
   private int chunkZ;
   private int chunkX;


   public EventChunkLoad(int n, int n2) {
      this.chunkX = n;
      this.chunkZ = n2;
   }

   public int getChunkX() {
      return this.chunkX;
   }

   @Override
   public void call(ChunkLoadListener listener) {
      listener.onChunkLoad(this);
   }

   public int getChunkZ() {
      return this.chunkZ;
   }
}
