package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockNetherWart extends BlockBush {
   public static final PropertyInteger AGE;

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      if (!world.isRemote) {
         int n2 = 1;
         if ((Integer)state.getValue(AGE) >= 3) {
            n2 = 2 + world.rand.nextInt(3);
            if (n > 0) {
               n2 += world.rand.nextInt(n + 1);
            }
         }

         for (int i = 0; i < n2; i++) {
            spawnAsEntity(world, pos, new ItemStack(Items.nether_wart));
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE});
   }

   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.soul_sand;
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   public BlockNetherWart() {
      super(Material.plants, MapColor.redColor);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      float f = 0.5F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
      this.setCreativeTab((CreativeTabs)null);
   }

   static {
      AGE = PropertyInteger.create("age", 0, 3);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      int value = (Integer)state.getValue(AGE);
      if (value < 3 && rand.nextInt(10) == 0) {
         state = state.withProperty(AGE, value + 1);
         world.setBlockState(pos, state, 2);
      }

      super.updateTick(world, pos, state, rand);
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.nether_wart;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }
}
