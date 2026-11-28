package net.minecraft.block;

import com.google.common.base.MoreObjects;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripWireHook extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyBool POWERED = PropertyBool.create("powered");
   public static final PropertyBool ATTACHED = PropertyBool.create("attached");
   public static final PropertyBool SUSPENDED = PropertyBool.create("suspended");

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.calculateState(world, pos, state, false, true, -1, (IBlockState)null);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      boolean value = (Boolean)state.getValue(ATTACHED);
      boolean value2 = (Boolean)state.getValue(POWERED);
      if (value || value2) {
         this.calculateState(world, pos, state, true, false, -1, (IBlockState)null);
      }

      if (value2) {
         world.notifyNeighborsOfStateChange(pos, this);
         world.notifyNeighborsOfStateChange(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite()), this);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
      return facing.getAxis().isHorizontal() && world.getBlockState(pos.offset(facing.getOpposite())).getBlock().isNormalCube();
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   public void calculateState(World world, BlockPos pos, IBlockState state, boolean flag, boolean flag2, int n, IBlockState state2) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      boolean value2 = (Boolean)state.getValue(ATTACHED);
      boolean value3 = (Boolean)state.getValue(POWERED);
      boolean flag3 = !World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
      boolean flag4 = !flag;
      boolean flag5 = false;
      int n2 = 0;
      IBlockState[] states = new IBlockState[42];

      for (int i = 1; i < 42; i++) {
         IBlockState blockState2 = world.getBlockState(pos.offset(value, i));
         if (blockState2.getBlock() == Blocks.tripwire_hook) {
            if (blockState2.getValue(FACING) == value.getOpposite()) {
               n2 = i;
            }
            break;
         }

         if (blockState2.getBlock() != Blocks.tripwire && i != n) {
            states[i] = null;
            flag4 = false;
         } else {
            if (i == n) {
               blockState2 = (IBlockState)MoreObjects.firstNonNull(state2, blockState2);
            }

            boolean flag6 = !(Boolean)blockState2.getValue(BlockTripWire.DISARMED);
            boolean value4 = (Boolean)blockState2.getValue(BlockTripWire.POWERED);
            boolean value5 = (Boolean)blockState2.getValue(BlockTripWire.SUSPENDED);
            flag4 &= value5 == flag3;
            flag5 |= flag6 && value4;
            states[i] = blockState2;
            if (i == n) {
               world.scheduleUpdate(pos, this, this.tickRate(world));
               flag4 &= flag6;
            }
         }
      }

      flag4 &= n2 > 1;
      flag5 &= flag4;
      IBlockState state3 = this.getDefaultState().withProperty(ATTACHED, flag4).withProperty(POWERED, flag5);
      if (n2 > 0) {
         BlockPos pos3 = pos.offset(value, n2);
         EnumFacing opposite = value.getOpposite();
         world.setBlockState(pos3, state3.withProperty(FACING, opposite), 3);
         this.notifyNeighbors(world, pos3, opposite);
         this.playSound(world, pos3, flag4, flag5, value2, value3);
      }

      this.playSound(world, pos, flag4, flag5, value2, value3);
      if (!flag) {
         world.setBlockState(pos, state3.withProperty(FACING, value), 3);
         if (flag2) {
            this.notifyNeighbors(world, pos, value);
         }
      }

      if (value2 != flag4) {
         for (int j = 1; j < n2; j++) {
            BlockPos pos4 = pos.offset(value, j);
            IBlockState state4 = states[j];
            if (state4 != null && world.getBlockState(pos4).getBlock() != Blocks.air) {
               world.setBlockState(pos4, state4.withProperty(ATTACHED, flag4), 3);
            }
         }
      }
   }

   private void notifyNeighbors(World world, BlockPos pos, EnumFacing facing) {
      world.notifyNeighborsOfStateChange(pos, this);
      world.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         if (world.getBlockState(pos.offset(facing)).getBlock().isNormalCube()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, POWERED, ATTACHED, SUSPENDED});
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      this.calculateState(world, pos, state, false, false, -1, (IBlockState)null);
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(SUSPENDED, !World.doesBlockHaveSolidTopSurface(world, n, n2 - 1, n3));
   }

   private boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
      if (!this.canPlaceBlockAt(world, pos)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return (Boolean)state.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if ((Boolean)state.getValue(POWERED)) {
         n |= 8;
      }

      if ((Boolean)state.getValue(ATTACHED)) {
         n |= 4;
      }

      return n;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, EnumFacing.getHorizontal(n & 3))
         .withProperty(POWERED, (n & 8) > 0)
         .withProperty(ATTACHED, (n & 4) > 0);
   }

   private void playSound(World world, BlockPos pos, boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      if (flag2 && !flag4) {
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5, "random.click", 0.4F, 0.6F);
      } else if (!flag2 && flag4) {
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5, "random.click", 0.4F, 0.5F);
      } else if (flag && !flag3) {
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5, "random.click", 0.4F, 0.7F);
      } else if (!flag && flag3) {
         world.playSoundEffect(
            (double)pos.getX() + 0.5,
            (double)pos.getY() + 0.1,
            (double)pos.getZ() + 0.5,
            "random.bowhit",
            0.4F,
            1.2F / (world.rand.nextFloat() * 0.2F + 0.9F)
         );
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      IBlockState state = this.getDefaultState()
         .withProperty(POWERED, Boolean.FALSE)
         .withProperty(ATTACHED, Boolean.FALSE)
         .withProperty(SUSPENDED, Boolean.FALSE);
      if (facing.getAxis().isHorizontal()) {
         state = state.withProperty(FACING, facing);
      }

      return state;
   }

   public BlockTripWireHook() {
      super(Material.circuits);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(POWERED, Boolean.FALSE)
            .withProperty(ATTACHED, Boolean.FALSE)
            .withProperty(SUSPENDED, Boolean.FALSE)
      );
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setTickRandomly(true);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      float f = 0.1875F;
      switch (((EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING))) {
         case EAST:
            this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
            break;
         case WEST:
            this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
            break;
         case SOUTH:
            this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
            break;
         case NORTH:
            this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
      }
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (block != this && this.checkForDrop(world, pos, state)) {
         if (!world.getBlockState(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite())).getBlock().isNormalCube()) {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         }
      }
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return !(Boolean)state.getValue(POWERED) ? 0 : (state.getValue(FACING) == facing ? 15 : 0);
   }

}
