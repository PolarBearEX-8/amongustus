package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGravel extends BlockFalling {
   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.stoneColor;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      if (n > 3) {
         n = 3;
      }

      return rand.nextInt(10 - n * 3) == 0 ? Items.flint : Item.getItemFromBlock(this);
   }
}
