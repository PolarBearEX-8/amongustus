package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRailDetector extends BlockRailBase {
   public static final PropertyEnum SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, new BlockRailDetector.FilterPredicate());
   public static final PropertyBool POWERED = PropertyBool.create("powered");

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.byMetadata(n & 7)).withProperty(POWERED, (n & 8) > 0);
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return !(Boolean)state.getValue(POWERED) ? 0 : (facing == EnumFacing.UP ? 15 : 0);
   }

   public BlockRailDetector() {
      super(true);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE).withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
      this.setTickRandomly(true);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote && (Boolean)state.getValue(POWERED)) {
         this.updatePoweredState(world, pos, state);
      }
   }

   private AxisAlignedBB getDectectionBox(BlockPos pos) {
      float f = 0.2F;
      return new AxisAlignedBB(
         (double)((float)pos.getX() + 0.2F),
         (double)pos.getY(),
         (double)((float)pos.getZ() + 0.2F),
         (double)((float)(pos.getX() + 1) - 0.2F),
         (double)((float)(pos.getY() + 1) - 0.2F),
         (double)((float)(pos.getZ() + 1) - 0.2F)
      );
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return (Boolean)state.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public int tickRate(World world) {
      return 20;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote && !(Boolean)state.getValue(POWERED)) {
         this.updatePoweredState(world, pos, state);
      }
   }

   @Override
   public IProperty getShapeProperty() {
      return SHAPE;
   }

   private void updatePoweredState(World world, BlockPos pos, IBlockState state) {
      boolean value = (Boolean)state.getValue(POWERED);
      boolean flag = false;
      if (!this.findMinecarts(world, pos, EntityMinecart.class, new Predicate[0]).isEmpty()) {
         flag = true;
      }

      if (flag && !value) {
         world.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 3);
         world.notifyNeighborsOfStateChange(pos, this);
         world.notifyNeighborsOfStateChange(pos.down(), this);
         world.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (!flag && value) {
         world.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE), 3);
         world.notifyNeighborsOfStateChange(pos, this);
         world.notifyNeighborsOfStateChange(pos.down(), this);
         world.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (flag) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }

      world.updateComparatorOutputLevel(pos, this);
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      if ((Boolean)world.getBlockState(pos).getValue(POWERED)) {
         List list = this.findMinecarts(world, pos, EntityMinecartCommandBlock.class, new Predicate[0]);
         if (!list.isEmpty()) {
            return ((EntityMinecartCommandBlock)list.get(0)).getCommandBlockLogic().getSuccessCount();
         }

         List list2 = this.findMinecarts(world, pos, EntityMinecart.class, new Predicate[]{EntitySelectors.selectInventories});
         if (!list2.isEmpty()) {
            return Container.calcRedstoneFromInventory((IInventory)list2.get(0));
         }
      }

      return 0;
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      super.onBlockAdded(world, pos, state);
      this.updatePoweredState(world, pos, state);
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{SHAPE, POWERED});
   }

   public List findMinecarts(World world, BlockPos pos, Class clazz, Predicate[] predicates) {
      AxisAlignedBB dectectionBox = this.getDectectionBox(pos);
      return predicates.length != 1 ? world.getEntitiesWithinAABB(clazz, dectectionBox) : world.getEntitiesWithinAABB(clazz, dectectionBox, predicates[0]);
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockRailBase.EnumRailDirection)state.getValue(SHAPE)).getMetadata();
      if ((Boolean)state.getValue(POWERED)) {
         n |= 8;
      }

      return n;
   }


   public static final class FilterPredicate implements Predicate {
      public boolean apply(BlockRailBase.EnumRailDirection enumRailDirection) {
         return enumRailDirection != BlockRailBase.EnumRailDirection.NORTH_EAST
            && enumRailDirection != BlockRailBase.EnumRailDirection.NORTH_WEST
            && enumRailDirection != BlockRailBase.EnumRailDirection.SOUTH_EAST
            && enumRailDirection != BlockRailBase.EnumRailDirection.SOUTH_WEST;
      }

      public boolean apply(Object obj) {
         return this.apply((BlockRailBase.EnumRailDirection)obj);
      }
   }
}
