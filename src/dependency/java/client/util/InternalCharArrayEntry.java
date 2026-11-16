package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class InternalCharArrayEntry {
   public final int count3;
   public int count2;
   public int count;
   public char[] charCodeArray;
   public SpecialByteArrayEntry specialByteArrayEntry2;
   public SpecialByteArrayEntry specialByteArrayEntry;

   public void applyIndexByIndex(int count4, int count5, int count6, int count7) {
      this.specialByteArrayEntry2.applyIndex(count4, count5, count6, count7);
   }

   public int computeCount2(int count4, int count5, int count6) {
      return this.specialByteArrayEntry.computeCountByIndex(count4, count5, count6);
   }

   public SpecialByteArrayEntry getSpecialByteArrayEntry() {
      return this.specialByteArrayEntry;
   }

   public void updateState() {
      Blocks.air.getDefaultState();
      int count4 = 0;
      int count5 = 0;

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            for (int index3 = 0; index3 < 16; index3++) {
               Block block = this.resolveBlock(index3, index, index2);
               if (block != Blocks.air) {
                  count4++;
                  if (block.isState23()) {
                     count5++;
                  }
               }
            }
         }
      }

      this.count2 = count4;
      this.count = count5;
   }

   public int getCount2() {
      return this.count2;
   }

   public IBlockState resolveIBlockState(int count4, int count5, int count6) {
      IBlockState blockState = (IBlockState)Block.identityHashMapEntry.resolveTarget(this.charCodeArray[count5 << 8 | count6 << 4 | count4]);
      return blockState != null ? blockState : Blocks.air.getDefaultState();
   }

   public int computeCount(int count4, int count5, int count6) {
      return this.specialByteArrayEntry2.computeCountByIndex(count4, count5, count6);
   }

   public char[] getCharCodeArray() {
      return this.charCodeArray;
   }

   public int computeCountByIndex(int count4, int count5, int count6) {
      IBlockState blockState = this.resolveIBlockState(count4, count5, count6);
      return blockState.getBlock().getIndex(blockState);
   }

   public void applyIndex(int count4, int count5, int count6, int count7) {
      this.specialByteArrayEntry.applyIndex(count4, count5, count6, count7);
   }

   public void applyIBlockState(int count4, int count5, int count6, IBlockState blockState) {
      Block block = this.resolveIBlockState(count4, count5, count6).getBlock();
      Block block2 = blockState.getBlock();
      if (block != Blocks.air) {
         this.count2--;
         if (block.isState23()) {
            this.count--;
         }
      }

      if (block2 != Blocks.air) {
         this.count2++;
         if (block2.isState23()) {
            this.count++;
         }
      }

      this.charCodeArray[count5 << 8 | count6 << 4 | count4] = (char)Block.identityHashMapEntry.computeCount(blockState);
   }

   public boolean isActiveAsFlag() {
      return this.count2 == 0;
   }

   public void setSpecialByteArrayEntry(SpecialByteArrayEntry specialByteArrayEntry3) {
      this.specialByteArrayEntry = specialByteArrayEntry3;
   }

   public SpecialByteArrayEntry getSpecialByteArrayEntry2() {
      return this.specialByteArrayEntry2;
   }

   public Block resolveBlock(int count4, int count5, int count6) {
      return this.resolveIBlockState(count4, count5, count6).getBlock();
   }

   public boolean isActive() {
      return this.count > 0;
   }

   public void setCharCodeArray(char[] charCodeArray2) {
      this.charCodeArray = charCodeArray2;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setSpecialByteArrayEntry2(SpecialByteArrayEntry specialByteArrayEntry3) {
      this.specialByteArrayEntry2 = specialByteArrayEntry3;
   }

   public InternalCharArrayEntry(int count4, boolean flag) {
      this.count3 = count4;
      this.charCodeArray = new char[4096];
      this.specialByteArrayEntry2 = new SpecialByteArrayEntry();
      if (flag) {
         this.specialByteArrayEntry = new SpecialByteArrayEntry();
      }
   }
}
