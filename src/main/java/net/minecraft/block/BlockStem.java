package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStem extends BlockBush implements IGrowable {
   public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
   public static final PropertyDirection FACING = PropertyDirection.create("facing", new BlockStem.FilterPredicate());
   private final Block crop;

   public BlockStem(Block block) {
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(FACING, EnumFacing.UP));
      this.crop = block;
      this.setTickRandomly(true);
      float f = 0.125F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
      this.setCreativeTab(null);
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return (Integer)state.getValue(AGE) != 7;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      state = state.withProperty(FACING, EnumFacing.UP);

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         if (world.getBlockState2(n + facing.getFrontOffsetX(), n2 + facing.getFrontOffsetY(), n3 + facing.getFrontOffsetZ()).getBlock() == this.crop) {
            state = state.withProperty(FACING, facing);
            break;
         }
      }

      return state;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      this.growStem(world, pos, state);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.maxY = (double)((float)((Integer)world.getBlockState2(n, n2, n3).getValue(AGE) * 2 + 2) / 16.0F);
      float f = 0.125F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
   }

   public void growStem(World world, BlockPos pos, IBlockState state) {
      int n = (Integer)state.getValue(AGE) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
      world.setBlockState(pos, state.withProperty(AGE, Math.min(7, n)), 2);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      super.updateTick(world, pos, state, rand);
      if (world.getLight(pos.getX(), pos.getY() + 1, pos.getZ()) >= 9) {
         float growthChance = BlockCrops.getGrowthChance(this, world, pos);
         if (rand.nextInt((int)(25.0F / growthChance) + 1) == 0) {
            int value = (Integer)state.getValue(AGE);
            if (value < 7) {
               state = state.withProperty(AGE, value + 1);
               world.setBlockState(pos, state, 2);
            } else {
               for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
                  if (world.getBlockState(pos.offset(facing)).getBlock() == this.crop) {
                     return;
                  }
               }

               pos = pos.offset(EnumFacing.Plane.HORIZONTAL.random(rand));
               Block block = world.getBlockState(pos.down()).getBlock();
               if (world.getBlockState(pos).getBlock().blockMaterial == Material.air
                  && (block == Blocks.farmland || block == Blocks.dirt || block == Blocks.grass)) {
                  world.setBlockState(pos, this.crop.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.farmland;
   }

   public Item getSeedItem() {
      return this.crop == Blocks.pumpkin ? Items.pumpkin_seeds : (this.crop == Blocks.melon_block ? Items.melon_seeds : null);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return this.getSeedItem();
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return this.getRenderColor(world.getBlockState(pos));
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.125F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
   }

   @Override
   public int getRenderColor(IBlockState state) {
      if (state.getBlock() != this) {
         return super.getRenderColor(state);
      } else {
         int value = (Integer)state.getValue(AGE);
         return (value * 32) << 16 | (255 - value * 8) << 8 | (value * 4);
      }
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE, FACING});
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, n);
      if (!world.isRemote) {
         Item seedItem = this.getSeedItem();
         if (seedItem != null) {
            int value = (Integer)state.getValue(AGE);

            for (int i = 0; i < 3; i++) {
               if (world.rand.nextInt(15) <= value) {
                  spawnAsEntity(world, pos, new ItemStack(seedItem));
               }
            }
         }
      }
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(EnumFacing facing) {
         return facing != EnumFacing.DOWN;
      }

      public boolean apply(Object obj) {
         return this.apply((EnumFacing)obj);
      }
   }
}
