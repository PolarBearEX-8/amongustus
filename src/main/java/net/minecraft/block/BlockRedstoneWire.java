package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneWire extends Block {
   public static final PropertyEnum NORTH = PropertyEnum.create("north", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum EAST = PropertyEnum.create("east", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum SOUTH = PropertyEnum.create("south", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyEnum WEST = PropertyEnum.create("west", BlockRedstoneWire.EnumAttachPosition.class);
   public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
   private final Set blocksNeedingUpdate = Sets.newHashSet();
   private boolean canProvidePower = true;

   public static boolean canConnectTo(IBlockState state, EnumFacing facing) {
      Block block = state.getBlock();
      if (block == Blocks.redstone_wire) {
         return true;
      } else if (Blocks.unpowered_repeater.isAssociated(block)) {
         EnumFacing value = (EnumFacing)state.getValue(BlockRedstoneRepeater.FACING);
         return value == facing || value.getOpposite() == facing;
      } else {
         return block.canProvidePower() && facing != null;
      }
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   public BlockRedstoneWire() {
      super(Material.circuits);
      this.setDefaultState(
         this.blockState
            .getBaseState()
            .withProperty(NORTH, BlockRedstoneWire.EnumAttachPosition.NONE)
            .withProperty(EAST, BlockRedstoneWire.EnumAttachPosition.NONE)
            .withProperty(SOUTH, BlockRedstoneWire.EnumAttachPosition.NONE)
            .withProperty(WEST, BlockRedstoneWire.EnumAttachPosition.NONE)
            .withProperty(POWER, 0)
      );
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!world.isRemote) {
         this.updateSurroundingRedstone(world, pos, state);

         for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.VERTICAL)) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }

         for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            this.notifyWireNeighborsOfStateChange(world, pos.offset(facing2));
         }

         for (EnumFacing facing3 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            BlockPos pos2 = pos.offset(facing3);
            if (world.getBlockState(pos2).getBlock().isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(world, pos2.up());
            } else {
               this.notifyWireNeighborsOfStateChange(world, pos2.down());
            }
         }
      }
   }

   private IBlockState calculateCurrentChanges(World world, BlockPos pos, BlockPos pos2, IBlockState state) {
      IBlockState state2 = state;
      int value = (Integer)state.getValue(POWER);
      int n = 0;
      n = this.getMaxCurrentStrength(world, pos2, n);
      this.canProvidePower = false;
      int n2 = world.isBlockIndirectlyGettingPowered(pos);
      this.canProvidePower = true;
      if (n2 > 0 && n2 > n - 1) {
         n = n2;
      }

      int n3 = 0;

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         BlockPos pos3 = pos.offset(facing);
         boolean flag = pos3.getX() != pos2.getX() || pos3.getZ() != pos2.getZ();
         if (flag) {
            n3 = this.getMaxCurrentStrength(world, pos3, n3);
         }

         if (world.getBlockState(pos3).getBlock().isNormalCube() && !world.getBlockState(pos.up()).getBlock().isNormalCube()) {
            if (flag && pos.getY() >= pos2.getY()) {
               n3 = this.getMaxCurrentStrength(world, pos3.up(), n3);
            }
         } else if (!world.getBlockState(pos3).getBlock().isNormalCube() && flag && pos.getY() <= pos2.getY()) {
            n3 = this.getMaxCurrentStrength(world, pos3.down(), n3);
         }
      }

      if (n3 > n) {
         n = n3 - 1;
      } else if (n > 0) {
         n--;
      } else {
         n = 0;
      }

      if (n2 > n - 1) {
         n = n2;
      }

      if (value != n) {
         state = state.withProperty(POWER, n);
         if (world.getBlockState(pos) == state2) {
            world.setBlockState(pos, state, 2);
         }

         this.blocksNeedingUpdate.add(pos);

         for (EnumFacing facing2 : EnumFacing.getValues()) {
            this.blocksNeedingUpdate.add(pos.offset(facing2));
         }
      }

      return state;
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return !this.canProvidePower ? 0 : this.getWeakPower(world, pos, state, facing);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{NORTH, EAST, SOUTH, WEST, POWER});
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.isRemote) {
         if (this.canPlaceBlockAt(world, pos)) {
            this.updateSurroundingRedstone(world, pos, state);
         } else {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
         }
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      state = state.withProperty(WEST, this.getAttachPosition(world, n, n2, n3, EnumFacing.WEST));
      state = state.withProperty(EAST, this.getAttachPosition(world, n, n2, n3, EnumFacing.EAST));
      state = state.withProperty(NORTH, this.getAttachPosition(world, n, n2, n3, EnumFacing.NORTH));
      return state.withProperty(SOUTH, this.getAttachPosition(world, n, n2, n3, EnumFacing.SOUTH));
   }

   private int colorMultiplier(int n) {
      float f = (float)n / 15.0F;
      float f2 = f * 0.6F + 0.4F;
      if (n == 0) {
         f2 = 0.3F;
      }

      float f3 = f * f * 0.7F - 0.5F;
      float f4 = f * f * 0.6F - 0.7F;
      if (f3 < 0.0F) {
         f3 = 0.0F;
      }

      if (f4 < 0.0F) {
         f4 = 0.0F;
      }

      return 0xFF000000 | MathHelper.clamp_int((int)(f2 * 255.0F), 0, 255) << 16 | MathHelper.clamp_int((int)(f3 * 255.0F), 0, 255) << 8 | MathHelper.clamp_int((int)(f4 * 255.0F), 0, 255);
   }

   private void notifyWireNeighborsOfStateChange(World world, BlockPos pos) {
      if (world.getBlockState(pos).getBlock() == this) {
         world.notifyNeighborsOfStateChange(pos, this);

         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }
      }
   }

   private boolean isPowerSourceAt(IBlockAccess world, BlockPos pos, EnumFacing facing) {
      BlockPos pos2 = pos.offset(facing);
      IBlockState blockState2 = world.getBlockState(pos2);
      Block block = blockState2.getBlock();
      boolean isNormalCube = block.isNormalCube();
      return !world.getBlockState(pos.up()).getBlock().isNormalCube() && isNormalCube && canConnectUpwardsTo(world, pos2.up())
         || canConnectTo(blockState2, facing)
         || block == Blocks.powered_repeater && blockState2.getValue(BlockRedstoneDiode.FACING) == facing
         || !isNormalCube && canConnectUpwardsTo(world, pos2.down());
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      int value = (Integer)state.getValue(POWER);
      if (value != 0) {
         double d = (double)pos.getX() + 0.5 + ((double)rand.nextFloat() - 0.5) * 0.2;
         double d2 = (double)((float)pos.getY() + 0.0625F);
         double d3 = (double)pos.getZ() + 0.5 + ((double)rand.nextFloat() - 0.5) * 0.2;
         float f = (float)value / 15.0F;
         float f2 = f * 0.6F + 0.4F;
         float f3 = Math.max(0.0F, f * f * 0.7F - 0.5F);
         float f4 = Math.max(0.0F, f * f * 0.6F - 0.7F);
         world.spawnParticle(EnumParticleTypes.REDSTONE, d, d2, d3, (double)f2, (double)f3, (double)f4, new int[0]);
      }
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.redstone;
   }

   public static boolean canConnectUpwardsTo(IBlockState state) {
      return canConnectTo(state, null);
   }

   private BlockRedstoneWire.EnumAttachPosition getAttachPosition(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      int n4 = n + facing.getFrontOffsetX();
      int n5 = n2 + facing.getFrontOffsetY();
      int n6 = n3 + facing.getFrontOffsetZ();
      Block block = world.getBlockState2(n4, n5, n6).getBlock();
      if (!canConnectTo(world.getBlockState2(n4, n5, n6), facing) && (block.isBlockNormalCube() || !canConnectUpwardsTo(world.getBlockState2(n4, n5 - 1, n6)))) {
         return !world.getBlockState2(n, n2 + 1, n3).getBlock().isBlockNormalCube() && block.isBlockNormalCube() && canConnectUpwardsTo(world.getBlockState2(n4, n5 + 1, n6))
            ? BlockRedstoneWire.EnumAttachPosition.UP
            : BlockRedstoneWire.EnumAttachPosition.NONE;
      } else {
         return BlockRedstoneWire.EnumAttachPosition.SIDE;
      }
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) || world.getBlockState(pos.down()).getBlock() == Blocks.glowstone;
   }

   @Override
   public int colorMultiplier(IBlockAccess world, BlockPos pos, int n) {
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock() != this ? super.colorMultiplier(world, pos, n) : this.colorMultiplier((Integer)blockState2.getValue(POWER));
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      if (!this.canProvidePower) {
         return 0;
      } else {
         int value = (Integer)state.getValue(POWER);
         if (value == 0) {
            return 0;
         } else if (facing == EnumFacing.UP) {
            return value;
         } else {
            EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);

            for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
               if (this.isPowerSourceAt(world, pos, facing2)) {
                  enumSet.add(facing2);
               }
            }

            if (facing.getAxis().isHorizontal() && enumSet.isEmpty()) {
               return value;
            } else {
               return enumSet.contains(facing) && !enumSet.contains(facing.rotateYCCW()) && !enumSet.contains(facing.rotateY()) ? value : 0;
            }
         }
      }
   }

   public static boolean canConnectUpwardsTo(IBlockAccess world, BlockPos pos) {
      return canConnectUpwardsTo(world.getBlockState(pos));
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   private int getMaxCurrentStrength(World world, BlockPos pos, int n) {
      if (world.getBlockState(pos).getBlock() != this) {
         return n;
      } else {
         int value = (Integer)world.getBlockState(pos).getValue(POWER);
         return value > n ? value : n;
      }
   }

   private IBlockState updateSurroundingRedstone(World world, BlockPos pos, IBlockState state) {
      state = this.calculateCurrentChanges(world, pos, pos, state);
      ArrayList list = Lists.newArrayList(this.blocksNeedingUpdate);
      this.blocksNeedingUpdate.clear();

      for (BlockPos pos2 : (Iterable<BlockPos>)(list)) {
         world.notifyNeighborsOfStateChange(pos2, this);
      }

      return state;
   }

   @Override
   public boolean canProvidePower() {
      return this.canProvidePower;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      if (!world.isRemote) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }

         this.updateSurroundingRedstone(world, pos, state);

         for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            this.notifyWireNeighborsOfStateChange(world, pos.offset(facing2));
         }

         for (EnumFacing facing3 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            BlockPos pos2 = pos.offset(facing3);
            if (world.getBlockState(pos2).getBlock().isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(world, pos2.up());
            } else {
               this.notifyWireNeighborsOfStateChange(world, pos2.down());
            }
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(POWER, n);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(POWER);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.redstone;
   }


   public enum EnumAttachPosition implements IStringSerializable {
      UP("up"),
      SIDE("side"),
      NONE("none");

   private final String name;
   private EnumAttachPosition(String str) {
         this.name = str;
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Override
      public String toString() {
         return this.getName();
      }
   }
}
