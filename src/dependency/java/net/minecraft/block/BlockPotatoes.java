package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPotatoes extends BlockWheat {
   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, count);
      if (!world.state && (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) >= 7 && world.random.nextInt(50) == 0) {
         applyWorld(world, blockPos, new ItemStack(Items.poisonousPotato));
      }
   }

   @Override
   public Item getWheat() {
      return Items.potato;
   }

   @Override
   public Item getWheatSeeds() {
      return Items.potato;
   }
}
