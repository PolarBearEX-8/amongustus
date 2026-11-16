package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.Items;

public class BlockClay extends Block {
   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.clayBall;
   }

   @Override
   public int processAction(Random random) {
      return 4;
   }

   public BlockClay() {
      super(Material.material27);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
