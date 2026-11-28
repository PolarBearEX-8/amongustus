package net.minecraft.block.state.pattern;

import com.google.common.base.MoreObjects;
import net.minecraft.block.state.BlockWorldState;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class BlockPattern {
   private final Predicate[][][] blockMatches;
   private final int fingerLength;
   private final int thumbLength;
   private final int palmLength;

   public static LoadingCache createLoadingCache(World world, boolean flag) {
      return CacheBuilder.newBuilder().build(new BlockPattern.CacheLoader(world, flag));
   }

   public BlockPattern.PatternHelper match(World world, BlockPos pos) {
      LoadingCache loadingCache = createLoadingCache(world, false);
      int n = Math.max(Math.max(this.palmLength, this.thumbLength), this.fingerLength);

      for (BlockPos pos2 : (Iterable<BlockPos>)(BlockPos.getAllInBox(pos, pos.add(n - 1, n - 1, n - 1)))) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            for (EnumFacing facing2 : EnumFacing.getValues()) {
               if (facing2 != facing && facing2 != facing.getOpposite()) {
                  BlockPattern.PatternHelper patternHelper = this.checkPatternAt(pos2, facing, facing2, loadingCache);
                  if (patternHelper != null) {
                     return patternHelper;
                  }
               }
            }
         }
      }

      return null;
   }

   private BlockPattern.PatternHelper checkPatternAt(BlockPos pos, EnumFacing facing, EnumFacing facing2, LoadingCache loadingCache) {
      for (int i = 0; i < this.palmLength; i++) {
         for (int j = 0; j < this.thumbLength; j++) {
            for (int k = 0; k < this.fingerLength; k++) {
               if (!this.blockMatches[k][j][i].apply(loadingCache.getUnchecked(translateOffset(pos, facing, facing2, i, j, k)))) {
                  return null;
               }
            }
         }
      }

      return new BlockPattern.PatternHelper(pos, facing, facing2, loadingCache, this.palmLength, this.thumbLength, this.fingerLength);
   }

   public static BlockPos translateOffset(BlockPos pos, EnumFacing facing, EnumFacing facing2, int n, int n2, int n3) {
      if (facing != facing2 && facing != facing2.getOpposite()) {
         Vec3i vec = new Vec3i(facing.getFrontOffsetX(), facing.getFrontOffsetY(), facing.getFrontOffsetZ());
         Vec3i vec2 = new Vec3i(facing2.getFrontOffsetX(), facing2.getFrontOffsetY(), facing2.getFrontOffsetZ());
         Vec3i vec3 = vec.crossProductVec(vec2);
         return pos.add(
            vec2.getX() * -n2 + vec3.getX() * n + vec.getX() * n3,
            vec2.getY() * -n2 + vec3.getY() * n + vec.getY() * n3,
            vec2.getZ() * -n2 + vec3.getZ() * n + vec.getZ() * n3
         );
      } else {
         throw new IllegalArgumentException("Invalid forwards & up combination");
      }
   }

   public int getThumbLength() {
      return this.thumbLength;
   }

   public int getPalmLength() {
      return this.palmLength;
   }

   public BlockPattern(Predicate[][][] predicatesArrs) {
      this.blockMatches = predicatesArrs;
      this.fingerLength = predicatesArrs.length;
      if (this.fingerLength > 0) {
         this.thumbLength = predicatesArrs[0].length;
         if (this.thumbLength > 0) {
            this.palmLength = predicatesArrs[0][0].length;
         } else {
            this.palmLength = 0;
         }
      } else {
         this.thumbLength = 0;
         this.palmLength = 0;
      }
   }


   public static class CacheLoader extends com.google.common.cache.CacheLoader {
      private final World world;
      private final boolean forceLoad;

      public Object load(Object obj) throws Exception {
         return this.load((BlockPos)obj);
      }

      public CacheLoader(World world2, boolean flag) {
         this.world = world2;
         this.forceLoad = flag;
      }

      public BlockWorldState load(BlockPos pos) throws Exception {
         return new BlockWorldState(this.world, pos, this.forceLoad);
      }
   }

   public static class PatternHelper {
      private final BlockPos pos;
      private final EnumFacing finger;
      private final EnumFacing thumb;
      private final LoadingCache lcache;
      private final int width;
      private final int height;
      private final int depth;

      public PatternHelper(BlockPos pos2, EnumFacing facing, EnumFacing facing2, LoadingCache loadingCache, int n, int n2, int n3) {
         this.pos = pos2;
         this.finger = facing;
         this.thumb = facing2;
         this.lcache = loadingCache;
         this.width = n;
         this.height = n2;
         this.depth = n3;
      }

      public BlockWorldState translateOffset(int n, int n2, int n3) {
         return (BlockWorldState)this.lcache.getUnchecked(BlockPattern.translateOffset(this.pos, this.getFinger(), this.getThumb(), n, n2, n3));
      }

      public EnumFacing getThumb() {
         return this.thumb;
      }

      @Override
      public String toString() {
         return MoreObjects.toStringHelper(this).add("up", this.thumb).add("forwards", this.finger).add("frontTopLeft", this.pos).toString();
      }

      public EnumFacing getFinger() {
         return this.finger;
      }

      public int getHeight() {
         return this.height;
      }

      public int getWidth() {
         return this.width;
      }

      public BlockPos getPos() {
         return this.pos;
      }
   }
}
