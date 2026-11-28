package baritone.api.cache;

import java.util.ArrayList;
import net.minecraft.world.chunk.Chunk;

public interface ICachedWorld {
   void save();

   boolean isCached(int n, int n2);

   ArrayList getLocationsOf(String str, int n, int n2, int n3, int n4);

   void reloadAllFromDisk();

   void queueForPacking(Chunk chunk);

   ICachedRegion getRegion(int n, int n2);
}
