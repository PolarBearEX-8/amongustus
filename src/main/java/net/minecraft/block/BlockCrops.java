package net.minecraft.block;

import java.util.Random;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops extends BlockBush implements IGrowable {
   public static final PropertyInteger AGE;

   public BlockCrops() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      float f = 0.5F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
      this.setCreativeTab((CreativeTabs)null);
      this.setHardness(0.0F);
      this.setStepSound(soundTypeGrass);
      this.disableStats();
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return (world.getLightFromNeighbors(pos.getX(), pos.getY(), pos.getZ()) >= 8 || world.canSeeSky(pos.getX(), pos.getY(), pos.getZ()))
         && this.canPlaceBlockOn(world.getBlockState2(pos.getX(), pos.getY() - 1, pos.getZ()).getBlock());
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }

   public Item getCarrot() {
      return Items.wheat_seeds;
   }

   public void grow(World world, BlockPos pos, IBlockState state) {
      int n = (Integer)state.getValue(AGE) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
      if (n > 7) {
         n = 7;
      }

      world.setBlockState(pos, state.withProperty(AGE, n), 2);
   }

   public static float getGrowthChance(Block block, World world, BlockPos pos) {
      float f = 1.0F;
      BlockPos pos2 = pos.down();

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            float f2 = 0.0F;
            IBlockState blockState2 = world.getBlockState(pos2.add(i, 0, j));
            if (blockState2.getBlock() == Blocks.farmland) {
               f2 = 1.0F;
               if ((Integer)blockState2.getValue(BlockFarmland.MOISTURE) > 0) {
                  f2 = 3.0F;
               }
            }

            if (i != 0 || j != 0) {
               f2 /= 4.0F;
            }

            f += f2;
         }
      }

      BlockPos pos3 = pos.north();
      BlockPos pos4 = pos.south();
      BlockPos pos5 = pos.west();
      BlockPos pos6 = pos.east();
      boolean flag = block == world.getBlockState(pos5).getBlock() || block == world.getBlockState(pos6).getBlock();
      boolean flag2 = block == world.getBlockState(pos3).getBlock() || block == world.getBlockState(pos4).getBlock();
      if (flag && flag2) {
         f /= 2.0F;
      } else {
         boolean flag3 = block == world.getBlockState(pos5.north()).getBlock()
            || block == world.getBlockState(pos6.north()).getBlock()
            || block == world.getBlockState(pos6.south()).getBlock()
            || block == world.getBlockState(pos5.south()).getBlock();
         if (flag3) {
            f /= 2.0F;
         }
      }

      return f;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, 0);
      if (!world.isRemote) {
         int value = (Integer)state.getValue(AGE);
         if (value >= 7) {
            int n2 = 3 + n;

            for (int i = 0; i < n2; i++) {
               if (world.rand.nextInt(15) <= value) {
                  spawnAsEntity(world, pos, new ItemStack(this.getCarrot(), 1, 0));
               }
            }
         }
      }
   }

   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.farmland;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return this.getCarrot();
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      this.grow(world, pos, state);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return (Integer)state.getValue(AGE) == 7 ? this.getCarrot2() : this.getCarrot();
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.updateTick(world, pos, state, rand);
      if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) >= 9) {
         int value = (Integer)state.getValue(AGE);
         if (value < 7) {
            float growthChance = getGrowthChance(this, world, pos);
            if (rand.nextInt((int)(25.0F / growthChance) + 1) == 0) {
               world.setBlockState(pos, state.withProperty(AGE, value + 1), 2);
            }
         }
      }
   }

   public Item getCarrot2() {
      return Items.wheat;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE});
   }

   static {
      AGE = PropertyInteger.create("age", 0, 7);
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return (Integer)state.getValue(AGE) < 7;
   }
}
