package net.minecraft.world.chunk.storage;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.NibbleArray;

public class ExtendedBlockStorage {
   private final int yBase;
   private int blockRefCount;
   private int tickRefCount;
   private char[] data;
   private NibbleArray blocklightArray;
   private NibbleArray skylightArray;

   public void setExtSkylightValue(int n, int n2, int n3, int n4) {
      this.skylightArray.set(n, n2, n3, n4);
   }

   public void setExtBlocklightValue(int n, int n2, int n3, int n4) {
      this.blocklightArray.set(n, n2, n3, n4);
   }

   public void setData(char[] chars) {
      this.data = chars;
   }

   public NibbleArray getBlocklightArray() {
      return this.blocklightArray;
   }

   public int getExtBlockMetadata(int n, int n2, int n3) {
      IBlockState state = this.get(n, n2, n3);
      return state.getBlock().getMetaFromState(state);
   }

   public void removeInvalidBlocks() {
      IBlockState defaultState = Blocks.air.getDefaultState();
      int n = 0;
      int n2 = 0;

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            for (int k = 0; k < 16; k++) {
               Block blockByExtId = this.getBlockByExtId(k, i, j);
               if (blockByExtId != Blocks.air) {
                  n++;
                  if (blockByExtId.getTickRandomly()) {
                     n2++;
                  }
               }
            }
         }
      }

      this.blockRefCount = n;
      this.tickRefCount = n2;
   }

   public boolean isEmpty() {
      return this.blockRefCount == 0;
   }

   public int getBlockRefCount() {
      return this.blockRefCount;
   }

   public int getExtSkylightValue(int n, int n2, int n3) {
      return this.skylightArray.get(n, n2, n3);
   }

   public void set(int n, int n2, int n3, IBlockState state) {
      Block block = this.get(n, n2, n3).getBlock();
      Block block2 = state.getBlock();
      if (block != Blocks.air) {
         this.blockRefCount--;
         if (block.getTickRandomly()) {
            this.tickRefCount--;
         }
      }

      if (block2 != Blocks.air) {
         this.blockRefCount++;
         if (block2.getTickRandomly()) {
            this.tickRefCount++;
         }
      }

      this.data[n2 << 8 | n3 << 4 | n] = (char)Block.BLOCK_STATE_IDS.get(state);
   }

   public NibbleArray getSkylightArray() {
      return this.skylightArray;
   }

   public char[] getData() {
      return this.data;
   }

   public int getYLocation() {
      return this.yBase;
   }

   public void setBlocklightArray(NibbleArray nibbleArray) {
      this.blocklightArray = nibbleArray;
   }

   public ExtendedBlockStorage(int n, boolean flag) {
      this.yBase = n;
      this.data = new char[4096];
      this.blocklightArray = new NibbleArray();
      if (flag) {
         this.skylightArray = new NibbleArray();
      }
   }

   public Block getBlockByExtId(int n, int n2, int n3) {
      return this.get(n, n2, n3).getBlock();
   }

   public boolean getNeedsRandomTick() {
      return this.tickRefCount > 0;
   }

   public int getExtBlocklightValue(int n, int n2, int n3) {
      return this.blocklightArray.get(n, n2, n3);
   }

   public void setSkylightArray(NibbleArray nibbleArray) {
      this.skylightArray = nibbleArray;
   }

   public IBlockState get(int n, int n2, int n3) {
      IBlockState byValue = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(this.data[n2 << 8 | n3 << 4 | n]);
      return byValue != null ? byValue : Blocks.air.getDefaultState();
   }
}
