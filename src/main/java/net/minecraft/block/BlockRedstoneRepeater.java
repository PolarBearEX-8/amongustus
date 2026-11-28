package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneRepeater extends BlockRedstoneDiode {
   public static final PropertyBool LOCKED = PropertyBool.create("locked");
   public static final PropertyInteger DELAY = PropertyInteger.create("delay", 1, 4);

   @Override
   public boolean isLocked(IBlockAccess world, int n, int n2, int n3, IBlockState state) {
      return this.getPowerOnSides(world, n, n2, n3, state) > 0;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      this.notifyNeighbors(world, pos, state);
   }

   @Override
   public IBlockState getPoweredState(IBlockState state) {
      Integer value = (Integer)state.getValue(DELAY);
      Boolean value2 = (Boolean)state.getValue(LOCKED);
      EnumFacing value3 = (EnumFacing)state.getValue(FACING);
      return Blocks.powered_repeater.getDefaultState().withProperty(FACING, value3).withProperty(DELAY, value).withProperty(LOCKED, value2);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.repeater;
   }

   @Override
   public int getDelay(IBlockState state) {
      return (Integer)state.getValue(DELAY) * 2;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (!player.capabilities.allowEdit) {
         return false;
      } else {
         world.setBlockState(pos, state.cycleProperty(DELAY), 3);
         return true;
      }
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return player.capabilities.allowEdit;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState()
         .withProperty(FACING, EnumFacing.getHorizontal(n))
         .withProperty(LOCKED, Boolean.FALSE)
         .withProperty(DELAY, 1 + (n >> 2));
   }

   @Override
   public boolean canPowerSide(Block block) {
      return isRedstoneRepeaterBlockID(block);
   }

   @Override
   public IBlockState getUnpoweredState(IBlockState state) {
      Integer value = (Integer)state.getValue(DELAY);
      Boolean value2 = (Boolean)state.getValue(LOCKED);
      EnumFacing value3 = (EnumFacing)state.getValue(FACING);
      return Blocks.unpowered_repeater.getDefaultState().withProperty(FACING, value3).withProperty(DELAY, value).withProperty(LOCKED, value2);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      return n | (Integer)state.getValue(DELAY) - 1 << 2;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.repeater;
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.diode.name");
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, DELAY, LOCKED});
   }

   @Override
   public IBlockState getActualState(IBlockState state, IBlockAccess world, int n, int n2, int n3) {
      return state.withProperty(LOCKED, this.isLocked(world, n, n2, n3, state));
   }

   public BlockRedstoneRepeater(boolean flag) {
      super(flag);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DELAY, 1).withProperty(LOCKED, Boolean.FALSE));
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.isRepeaterPowered) {
         EnumFacing value = (EnumFacing)state.getValue(FACING);
         double d = (double)((float)pos.getX() + 0.5F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         double d2 = (double)((float)pos.getY() + 0.4F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         double d3 = (double)((float)pos.getZ() + 0.5F) + (double)(rand.nextFloat() - 0.5F) * 0.2;
         float f = -5.0F;
         if (rand.nextBoolean()) {
            f = (float)((Integer)state.getValue(DELAY) * 2 - 1);
         }

         f /= 16.0F;
         double d4 = (double)(f * (float)value.getFrontOffsetX());
         double d5 = (double)(f * (float)value.getFrontOffsetZ());
         world.spawnParticle(EnumParticleTypes.REDSTONE, d + d4, d2, d3 + d5, 0.0, 0.0, 0.0, new int[0]);
      }
   }
}
