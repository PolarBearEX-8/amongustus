package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSnowBlock extends Block {
   @Override
   public int quantityDropped(Random rand) {
      return 4;
   }

   public BlockSnowBlock() {
      super(Material.craftedSnow);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.snowball;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (world.getLightFor(EnumSkyBlock.BLOCK, pos.getX(), pos.getY(), pos.getZ()) > 11) {
         this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
         world.setBlockToAir(pos);
      }
   }
}
