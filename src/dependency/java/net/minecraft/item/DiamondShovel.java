package net.minecraft.item;

import net.minecraft.block.Block;

public class DiamondShovel extends ItemBlock {
   public final Block block2;
   public String[] stringArray;

   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public String getText(ItemStack stack) {
      if (this.stringArray == null) {
         return super.getText(stack);
      } else {
         int indexOffset = stack.getIndexOffset();
         return indexOffset >= 0 && indexOffset < this.stringArray.length ? super.getText(stack) + "." + this.stringArray[indexOffset] : super.getText(stack);
      }
   }

   public DiamondShovel withStringArray(String[] textArray) {
      this.stringArray = textArray;
      return this;
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      return this.block2.processAction2(this.block2.processAction(stack.getIndexOffset()));
   }

   public DiamondShovel(Block block, boolean flag) {
      super(block);
      this.block2 = block;
      if (flag) {
         this.processAction3(0);
         this.processAction(true);
      }
   }
}
