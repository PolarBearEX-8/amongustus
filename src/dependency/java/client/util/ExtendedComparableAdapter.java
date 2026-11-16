package client.util;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class ExtendedComparableAdapter implements Comparable {
   public static long timestamp2;
   public final Block block;
   public final BlockPos blockPos;
   public long timestamp;
   public int count;
   public long timestamp3;

   public void setCount(int count2) {
      this.count = count2;
   }

   @Override
   public int compareTo(Object entry) {
      return this.computeCount((ExtendedComparableAdapter)entry);
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof ExtendedComparableAdapter)) {
         return false;
      } else {
         ExtendedComparableAdapter extendedComparableAdapter = (ExtendedComparableAdapter)other;
         return this.blockPos.equals(extendedComparableAdapter.blockPos) && Block.matchesBlock(this.block, extendedComparableAdapter.block);
      }
   }

   @Override
   public String toString() {
      return Block.getIdFromBlock(this.block) + ": " + this.blockPos + ", " + this.timestamp + ", " + this.count + ", " + this.timestamp3;
   }

   @Override
   public int hashCode() {
      return this.blockPos.hashCode();
   }

   public Block getBlock() {
      return this.block;
   }

   public int computeCount(ExtendedComparableAdapter extendedComparableAdapter) {
      return this.timestamp < extendedComparableAdapter.timestamp
         ? -1
         : (
            this.timestamp > extendedComparableAdapter.timestamp
               ? 1
               : (this.count != extendedComparableAdapter.count ? this.count - extendedComparableAdapter.count : (this.timestamp3 < extendedComparableAdapter.timestamp3 ? -1 : (this.timestamp3 > extendedComparableAdapter.timestamp3 ? 1 : 0)))
         );
   }

   public ExtendedComparableAdapter(BlockPos blockPos2, Block block2) {
      this.timestamp3 = timestamp2++;
      this.blockPos = blockPos2;
      this.block = block2;
   }

   public ExtendedComparableAdapter withTimestamp(long value) {
      this.timestamp = value;
      return this;
   }
}
