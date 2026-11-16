package client.util;

import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import net.minecraft.block.InternalBlockPosEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class PredicateArrayEntry {
   public final Predicate[][][] predicateArray;
   public final int count;
   public final int count2;
   public final int count3;

   public int getCount3() {
      return this.count3;
   }

   public int getCount2() {
      return this.count2;
   }

   public InternalBlockPosEntry resolveInternalBlockPosEntry(World world, BlockPos blockPos) {
      LoadingCache loadingCache = resolveLoadingCache(world, false);
      int count4 = Math.max(Math.max(this.count3, this.count2), this.count);

      for (BlockPos blockPos2 : (java.lang.Iterable<BlockPos>) (BlockPos.resolveIterable(blockPos, blockPos.add(count4 - 1, count4 - 1, count4 - 1)))) {
         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
               if (enumFacing2 != enumFacing && enumFacing2 != enumFacing.getEnumFacing5()) {
                  InternalBlockPosEntry internalBlockPosEntry = this.resolveInternalBlockPosEntry(blockPos2, enumFacing, enumFacing2, loadingCache);
                  if (internalBlockPosEntry != null) {
                     return internalBlockPosEntry;
                  }
               }
            }
         }
      }

      return null;
   }

   public static BlockPos resolveBlockPos(BlockPos blockPos, EnumFacing enumFacing, EnumFacing enumFacing2, int count4, int count5, int count6) {
      if (enumFacing != enumFacing2 && enumFacing != enumFacing2.getEnumFacing5()) {
         Vec3i vec = new Vec3i(enumFacing.getInt2(), enumFacing.getInt3(), enumFacing.getInt());
         Vec3i vec2 = new Vec3i(enumFacing2.getInt2(), enumFacing2.getInt3(), enumFacing2.getInt());
         Vec3i vec3 = vec.resolveVec3i(vec2);
         return blockPos.add(
            vec2.getX() * -count5 + vec3.getX() * count4 + vec.getX() * count6,
            vec2.getY() * -count5 + vec3.getY() * count4 + vec.getY() * count6,
            vec2.getZ() * -count5 + vec3.getZ() * count4 + vec.getZ() * count6
         );
      } else {
         throw new IllegalArgumentException("Invalid forwards & up combination");
      }
   }

   public PredicateArrayEntry(Predicate[][][] predicateMatrix) {
      this.predicateArray = predicateMatrix;
      this.count = predicateMatrix.length;
      if (this.count > 0) {
         this.count2 = predicateMatrix[0].length;
         if (this.count2 > 0) {
            this.count3 = predicateMatrix[0][0].length;
         } else {
            this.count3 = 0;
         }
      } else {
         this.count2 = 0;
         this.count3 = 0;
      }
   }

   private InternalBlockPosEntry resolveInternalBlockPosEntry(BlockPos blockPos, EnumFacing enumFacing, EnumFacing enumFacing2, LoadingCache loadingCache) {
      for (int index = 0; index < this.count3; index++) {
         for (int index2 = 0; index2 < this.count2; index2++) {
            for (int index3 = 0; index3 < this.count; index3++) {
               if (!this.predicateArray[index3][index2][index].apply(loadingCache.getUnchecked(resolveBlockPos(blockPos, enumFacing, enumFacing2, index, index2, index3)))) {
                  return null;
               }
            }
         }
      }

      return new InternalBlockPosEntry(blockPos, enumFacing, enumFacing2, loadingCache, this.count3, this.count2, this.count);
   }

   public static LoadingCache resolveLoadingCache(World world, boolean flag) {
      return CacheBuilder.newBuilder().build(new AuxiliaryCacheLoaderAdapter(world, flag));
   }
}
