package client.util;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class NullManager {
   public final int count;
   public IBlockState iBlockState;
   public int count2 = 1;
   public int count3;

   private int getCount() {
      return this.iBlockState.getBlock().getIndex(this.iBlockState);
   }

   private Block getBlock() {
      return this.iBlockState.getBlock();
   }

   public int getCount3() {
      return this.count3;
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public void setCount3(int count4) {
      this.count3 = count4;
   }

   public int getCount2() {
      return this.count2;
   }

   public NullManager(int count4, int count5, Block block) {
      this.count = count4;
      this.count2 = count5;
      this.iBlockState = block.getDefaultState();
   }

   public NullManager(int count4, int count5, Block block, int count6) {
      this(count4, count5, block);
      this.iBlockState = block.processAction(count6);
   }

   public NullManager(int count4, Block block) {
      this(3, count4, block);
   }

   @Override
   public String toString() {
      String text;
      if (this.count >= 3) {
         ResourceLocation object = (ResourceLocation)Block.iterableExtensionBridge.getObject2(this.getBlock());
         text = object == null ? "null" : object.toString();
         if (this.count2 > 1) {
            text = this.count2 + "*" + text;
         }
      } else {
         text = Integer.toString(Block.getIdFromBlock(this.getBlock()));
         if (this.count2 > 1) {
            text = this.count2 + "x" + text;
         }
      }

      int count4 = this.getCount();
      if (count4 > 0) {
         text = text + ":" + count4;
      }

      return text;
   }
}
