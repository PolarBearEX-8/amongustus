package net.minecraft.block;

import client.setting.MovementProfileProperty;
import client.util.CommonPredicateAdapter;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import client.util.LivingEntityFilter;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCaveSpiderAdapter;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDetectorRail extends SpecialBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   public List resolveList(World world, BlockPos blockPos, Class clazz, Predicate[] predicateArray) {
      AxisAlignedBB boundingBox = this.resolveAxisAlignedBB(blockPos);
      return predicateArray.length != 1 ? world.resolveList(clazz, boundingBox) : world.resolveList(clazz, boundingBox, predicateArray[0]);
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && (Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorld5(world, blockPos, blockState);
      }
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(immutableSetEntry, MovementProfileProperty.resolveMovementProfileProperty(count & 7))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0);
   }

   @Override
   public int processAction(World world) {
      return 20;
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (!world.state && !(Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         this.applyWorld5(world, blockPos, blockState);
      }
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      if ((Boolean)world.getBlockState(blockPos).getComparable(internalImmutableSetEntry)) {
         List list = this.resolveList(world, blockPos, EntityCaveSpiderAdapter.class, new Predicate[0]);
         if (!list.isEmpty()) {
            return ((EntityCaveSpiderAdapter)list.get(0)).getVectorDispatcher().getCount();
         }

         List list2 = this.resolveList(world, blockPos, EntityMinecart.class, new Predicate[]{LivingEntityFilter.predicate3});
         if (!list2.isEmpty()) {
            return Container.computeCount((IInventory)list2.get(0));
         }
      }

      return 0;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return ((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      super.processAction3(world, blockPos, blockState);
      this.applyWorld5(world, blockPos, blockState);
   }

   private AxisAlignedBB resolveAxisAlignedBB(BlockPos blockPos) {
      return new AxisAlignedBB(
         (double)((float)blockPos.getX() + 0.2F),
         (double)blockPos.getY(),
         (double)((float)blockPos.getZ() + 0.2F),
         (double)((float)(blockPos.getX() + 1) - 0.2F),
         (double)((float)(blockPos.getY() + 1) - 0.2F),
         (double)((float)(blockPos.getZ() + 1) - 0.2F)
      );
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public IProperty getIProperty() {
      return immutableSetEntry;
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !(Boolean)blockState.getComparable(internalImmutableSetEntry) ? 0 : (enumFacing == EnumFacing.UP ? 15 : 0);
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((MovementProfileProperty)blockState.getComparable(immutableSetEntry)).getCount();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      return count;
   }

   private void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      boolean flag = false;
      if (!this.resolveList(world, blockPos, EntityMinecart.class, new Predicate[0]).isEmpty()) {
         flag = true;
      }

      if (flag && !comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 3);
         world.applyBlockPosByBlockPos(blockPos, this);
         world.applyBlockPosByBlockPos(blockPos.down(), this);
         world.applyBlockPos(blockPos, blockPos);
      }

      if (!flag && comparable) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 3);
         world.applyBlockPosByBlockPos(blockPos, this);
         world.applyBlockPosByBlockPos(blockPos.down(), this);
         world.applyBlockPos(blockPos, blockPos);
      }

      if (flag) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }

      world.applyBlockPos(blockPos, this);
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockDetectorRail() {
      super(true);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, MovementProfileProperty.NORTH_SOUTH)
      );
      this.processAction(true);
   }

   static {
      String[] textArray = new String[]{"powered", "shape"};
      immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry(textArray[1], MovementProfileProperty.class, new CommonPredicateAdapter());
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
   }
}
