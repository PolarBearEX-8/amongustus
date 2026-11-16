package client.util;

import com.google.common.cache.CacheLoader;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryCacheLoaderAdapter extends CacheLoader {
   public final World world;
   public final boolean enabled;

   public Object load(Object entry) throws Exception {
      return this.resolveSecondaryWorldEntry((BlockPos)entry);
   }

   public AuxiliaryCacheLoaderAdapter(World world2, boolean flag) {
      this.world = world2;
      this.enabled = flag;
   }

   public SecondaryWorldEntry resolveSecondaryWorldEntry(BlockPos blockPos) throws Exception {
      return new SecondaryWorldEntry(this.world, blockPos, this.enabled);
   }
}
