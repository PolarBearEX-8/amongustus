package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemPiston extends ItemBlock {
   public ItemPiston(Block block2) {
      super(block2);
   }

   @Override
   public int getMetadata(int n) {
      return 7;
   }
}
