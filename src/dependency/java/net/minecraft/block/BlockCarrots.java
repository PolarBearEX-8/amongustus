package net.minecraft.block;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BlockCarrots extends BlockWheat {
   @Override
   public Item getWheat() {
      return Items.carrot;
   }

   @Override
   public Item getWheatSeeds() {
      return Items.carrot;
   }
}
