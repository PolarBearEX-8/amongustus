package baritone.cache;

import baritone.utils.ChunkPos;
import baritone.Baritone;
import net.minecraft.world.chunk.Chunk;

public class CachedWorldPacker implements Runnable {
   public final CachedWorld cachedWorld;

   @Override
   public void run() {
      while (true) {
         try {
            ChunkPos chunkPos = (ChunkPos)CachedWorld.getToPackQueue(this.cachedWorld).take();
            Chunk chunk = (Chunk)CachedWorld.getChunkMap(this.cachedWorld).remove(chunkPos);
            if (CachedWorld.getToPackQueue(this.cachedWorld).size() <= (Integer)Baritone.settings().chunkPackerQueueMaxSize.value) {
               CachedChunk cachedChunk = ChunkPacker.pack(chunk);
               CachedWorld.putChunk(this.cachedWorld, cachedChunk);
            }
         } catch (InterruptedException e) {
            e.printStackTrace();
            return;
         } catch (Throwable e2) {
            e2.printStackTrace();
         }
      }
   }

   public CachedWorldPacker(CachedWorld cachedWorld2, CachedWorld.InnerEntry obj) {
      this(cachedWorld2);
   }

   private CachedWorldPacker(CachedWorld cachedWorld2) {
      this.cachedWorld = cachedWorld2;
   }
}
