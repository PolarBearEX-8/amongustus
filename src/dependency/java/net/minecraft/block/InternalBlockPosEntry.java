package net.minecraft.block;

import client.util.PredicateArrayEntry;
import client.util.SecondaryWorldEntry;
import com.google.common.base.MoreObjects;
import com.google.common.cache.LoadingCache;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class InternalBlockPosEntry {
   public final BlockPos blockPos;
   public final EnumFacing enumFacing;
   public final EnumFacing enumFacing2;
   public final LoadingCache loadingCache;
   public final int count;
   public final int count2;
   public final int count3;

   public SecondaryWorldEntry resolveSecondaryWorldEntry(int count4, int count5, int count6) {
      return (SecondaryWorldEntry)this.loadingCache
         .getUnchecked(PredicateArrayEntry.resolveBlockPos(this.blockPos, this.getEnumFacing(), this.getEnumFacing2(), count4, count5, count6));
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this).add("up", this.enumFacing2).add("forwards", this.enumFacing).add("frontTopLeft", this.blockPos).toString();
   }

   public int getCount2() {
      return this.count2;
   }

   public EnumFacing getEnumFacing() {
      return this.enumFacing;
   }

   public EnumFacing getEnumFacing2() {
      return this.enumFacing2;
   }

   public InternalBlockPosEntry(BlockPos blockPos2, EnumFacing enumFacing3, EnumFacing enumFacing4, LoadingCache loadingCache2, int count4, int count5, int count6) {
      this.blockPos = blockPos2;
      this.enumFacing = enumFacing3;
      this.enumFacing2 = enumFacing4;
      this.loadingCache = loadingCache2;
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }
}
