package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BlockGravel extends CustomBlockAdapter {
   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor10;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      int count2 = count;
      if (count > 3) {
         count2 = 3;
      }

      return random.nextInt(10 - count2 * 3) == 0 ? Items.flint : Item.processAction2(this);
   }
}
