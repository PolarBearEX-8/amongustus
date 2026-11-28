package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReed extends Block {
   public static final PropertyInteger AGE;

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      this.checkForDrop(world, pos, state);
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.reeds;
   }

   public BlockReed() {
      super(Material.plants);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      float f = 0.375F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
      this.setTickRandomly(true);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      Block block = world.getBlockState(pos.down()).getBlock();
      if (block == this) {
         return true;
      } else if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.sand) {
         return false;
      } else {
         for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            if (world.getBlockState(pos.offset(facing).down()).getBlock().getMaterial() == Material.water) {
               return true;
            }
         }

         return false;
      }
   }

   static {
      AGE = PropertyInteger.create("age", 0, 15);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.reeds;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if ((world.getBlockState(pos.down()).getBlock() == Blocks.reeds || this.checkForDrop(world, pos, state)) && world.isAirBlock(pos.up())) {
         int n = 1;

         while (world.getBlockState(pos.down(n)).getBlock() == this) {
            n++;
         }

         if (n < 3) {
            int value = (Integer)state.getValue(AGE);
            if (value == 15) {
               world.setBlockState(pos.up(), this.getDefaultState());
               world.setBlockState(pos, state.withProperty(AGE, 0), 4);
            } else {
               world.setBlockState(pos, state.withProperty(AGE, value + 1), 4);
            }
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE});
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      return world.getBiomeGenForCoords(pos).getGrassColorAtPos(pos);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public final boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
      if (this.canBlockStay(world, pos)) {
         return true;
      } else {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
         return false;
      }
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return this.canPlaceBlockAt(world, pos);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }
}
