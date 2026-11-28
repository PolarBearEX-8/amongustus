package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemAnvilBlock extends ItemMultiTexture {

   @Override
   public int getMetadata(int n) {
      return n << 2;
   }

   public ItemAnvilBlock(Block block2) {
      super(block2, block2, new String[]{"intact", "slightlyDamaged", "veryDamaged"});
   }
}
