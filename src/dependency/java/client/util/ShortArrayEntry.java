package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ShortArrayEntry {
   public final short[] shortCodeArray = new short[65536];
   public final IBlockState iBlockState = Blocks.air.getDefaultState();

   public void applyIBlockState(int count, int count2, int count3, IBlockState blockState) {
      int count4 = count << 12 | count3 << 8 | count2;
      this.applyIBlockState(count4, blockState);
   }

   public IBlockState resolveIBlockState(int count) {
      if (count >= 0 && count < this.shortCodeArray.length) {
         IBlockState blockState = (IBlockState)Block.identityHashMapEntry.resolveTarget(this.shortCodeArray[count]);
         return blockState != null ? blockState : this.iBlockState;
      } else {
         throw new IndexOutOfBoundsException("The coordinate is out of range");
      }
   }

   public void applyIBlockState(int count, IBlockState blockState) {
      if (count >= 0 && count < this.shortCodeArray.length) {
         this.shortCodeArray[count] = (short)Block.identityHashMapEntry.computeCount(blockState);
      } else {
         throw new IndexOutOfBoundsException("The coordinate is out of range");
      }
   }

   public IBlockState resolveIBlockState(int count, int count2, int count3) {
      int count4 = count << 12 | count3 << 8 | count2;
      return this.resolveIBlockState(count4);
   }
}
