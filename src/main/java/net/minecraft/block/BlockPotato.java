package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPotato extends BlockCrops {
   @Override
   public Item getCarrot() {
      return Items.potato;
   }

   @Override
   public Item getCarrot2() {
      return Items.potato;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, n);
      if (!world.isRemote && (Integer)state.getValue(AGE) >= 7 && world.rand.nextInt(50) == 0) {
         spawnAsEntity(world, pos, new ItemStack(Items.poisonous_potato));
      }
   }
}
