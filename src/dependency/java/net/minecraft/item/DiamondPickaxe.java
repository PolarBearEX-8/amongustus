package net.minecraft.item;

import net.minecraft.block.Block;

public class DiamondPickaxe extends ItemBlock {
   @Override
   public int processAction2(int count) {
      return 7;
   }

   public DiamondPickaxe(Block block) {
      super(block);
   }
}
