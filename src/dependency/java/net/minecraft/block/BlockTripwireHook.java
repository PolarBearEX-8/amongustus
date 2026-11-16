package net.minecraft.block;

import client.setting.BlockSolidType;
import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.SmoothFormatter;
import com.google.common.base.MoreObjects;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTripwireHook extends Block {
   public static final AutoWalkPath autoWalkPath = AutoWalkPath.resolveAutoWalkPath("facing", MovementFactorProperty.HORIZONTAL);
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");
   public static final InternalImmutableSetEntry internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("attached");
   public static final InternalImmutableSetEntry internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("suspended");

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.applyWorldByWorld(world, blockPos, blockState, false, true, -1, (IBlockState)null);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT_MIPPED;
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag, boolean flag2, int count, IBlockState blockState2) {
      EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
      boolean comparable2 = (Boolean)blockState.getComparable(internalImmutableSetEntry2);
      boolean comparable3 = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      boolean flag3 = !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
      boolean flag4 = !flag;
      boolean flag5 = false;
      int count2 = 0;
      IBlockState[] blockStateArray = new IBlockState[42];

      for (int index = 1; index < 42; index++) {
         BlockPos blockPos2 = blockPos.getBlockPos(comparable, index);
         IBlockState blockState3 = world.getBlockState(blockPos2);
         if (blockState3.getBlock() == Blocks.blockTripwireHook) {
            if (blockState3.getComparable(autoWalkPath) == comparable.getEnumFacing5()) {
               count2 = index;
            }
            break;
         }

         if (blockState3.getBlock() != Blocks.block6 && index != count) {
            blockStateArray[index] = null;
            flag4 = false;
         } else {
            if (index == count) {
               blockState3 = (IBlockState)MoreObjects.firstNonNull(blockState2, blockState3);
            }

            boolean flag6 = !(Boolean)blockState3.getComparable(BlockTripwire.internalImmutableSetEntry4);
            boolean comparable4 = (Boolean)blockState3.getComparable(BlockTripwire.internalImmutableSetEntry);
            boolean comparable5 = (Boolean)blockState3.getComparable(BlockTripwire.internalImmutableSetEntry7);
            flag4 &= comparable5 == flag3;
            flag5 |= flag6 && comparable4;
            blockStateArray[index] = blockState3;
            if (index == count) {
               world.applyBlockPos(blockPos, this, this.processAction(world));
               flag4 &= flag6;
            }
         }
      }

      boolean flag7 = flag4 & count2 > 1;
      boolean flag8 = flag5 & flag7;
      IBlockState blockState4 = this.getDefaultState().resolveIBlockState(internalImmutableSetEntry2, flag7).resolveIBlockState(internalImmutableSetEntry, flag8);
      if (count2 > 0) {
         BlockPos blockPos3 = blockPos.getBlockPos(comparable, count2);
         EnumFacing enumFacing = comparable.getEnumFacing5();
         world.matchesBlockPos(blockPos3, blockState4.resolveIBlockState(autoWalkPath, enumFacing), 3);
         this.applyWorldByWorld(world, blockPos3, enumFacing);
         this.applyWorldByWorld(world, blockPos3, flag7, flag8, comparable2, comparable3);
      }

      this.applyWorldByWorld(world, blockPos, flag7, flag8, comparable2, comparable3);
      if (!flag) {
         world.matchesBlockPos(blockPos, blockState4.resolveIBlockState(autoWalkPath, comparable), 3);
         if (flag2) {
            this.applyWorldByWorld(world, blockPos, comparable);
         }
      }

      if (comparable2 != flag7) {
         for (int index2 = 1; index2 < count2; index2++) {
            BlockPos blockPos4 = blockPos.getBlockPos(comparable, index2);
            IBlockState blockState5 = blockStateArray[index2];
            if (blockState5 != null && world.getBlockState(blockPos4).getBlock() != Blocks.air) {
               world.matchesBlockPos(blockPos4, blockState5.resolveIBlockState(internalImmutableSetEntry2, flag7), 3);
            }
         }
      }
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase livingEntity, ItemStack stack) {
      this.applyWorldByWorld(world, blockPos, blockState, false, false, -1, (IBlockState)null);
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      float factor = 0.1875F;
      switch (SmoothFormatter.countArray[((EnumFacing)iBlockAccess.getIBlockState(count, count2, count3).getComparable(autoWalkPath)).ordinal()]) {
         case 1:
            this.setBlockBounds(0.0F, 0.2F, 0.5F - factor, factor * 2.0F, 0.8F, 0.5F + factor);
            break;
         case 2:
            this.setBlockBounds(1.0F - factor * 2.0F, 0.2F, 0.5F - factor, 1.0F, 0.8F, 0.5F + factor);
            break;
         case 3:
            this.setBlockBounds(0.5F - factor, 0.2F, 0.0F, 0.5F + factor, 0.8F, factor * 2.0F);
            break;
         case 4:
            this.setBlockBounds(0.5F - factor, 0.2F, 1.0F - factor * 2.0F, 0.5F + factor, 0.8F, 1.0F);
      }
   }

   @Override
   public void processAction2(World world, BlockPos blockPos, IBlockState blockState, Random random) {
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry3, !World.matchesIBlockAccess(iBlockAccess, count, count2 - 1, count3));
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, EnumFacing enumFacing) {
      world.applyBlockPosByBlockPos(blockPos, this);
      world.applyBlockPosByBlockPos(blockPos.offset(enumFacing.getEnumFacing5()), this);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count & 3))
         .resolveIBlockState(internalImmutableSetEntry, (count & 8) > 0)
         .resolveIBlockState(internalImmutableSetEntry2, (count & 4) > 0);
   }

   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      boolean comparable = (Boolean)blockState.getComparable(internalImmutableSetEntry2);
      boolean comparable2 = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      if (comparable || comparable2) {
         this.applyWorldByWorld(world, blockPos, blockState, true, false, -1, (IBlockState)null);
      }

      if (comparable2) {
         world.applyBlockPosByBlockPos(blockPos, this);
         world.applyBlockPosByBlockPos(blockPos.offset(((EnumFacing)blockState.getComparable(autoWalkPath)).getEnumFacing5()), this);
      }

      super.onBlockAdded(world, blockPos, blockState);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (block != this && this.matchesWorld(world, blockPos, blockState)) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         if (!world.getBlockState(blockPos.offset(comparable.getEnumFacing5())).getBlock().isActive2()) {
            this.processAction(world, blockPos, blockState, 0);
            world.matchesBlockPos8(blockPos);
         }
      }
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return ((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return enumFacing.getEnumFacingXType().isActiveAsFlag() && world.getBlockState(blockPos.offset(enumFacing.getEnumFacing5())).getBlock().isActive2();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      IBlockState blockState = this.getDefaultState()
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
         .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE);
      if (enumFacing.getEnumFacingXType().isActiveAsFlag()) {
         blockState = blockState.resolveIBlockState(autoWalkPath, enumFacing);
      }

      return blockState;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : (Iterable<EnumFacing>)(Iterable<?>)MovementFactorProperty.HORIZONTAL) {
         if (world.getBlockState(blockPos.offset(enumFacing)).getBlock().isActive2()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry, internalImmutableSetEntry2, internalImmutableSetEntry3});
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      if (flag2 && !flag4) {
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5, "random.click", 0.4F, 0.6F);
      } else if (!flag2 && flag4) {
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5, "random.click", 0.4F, 0.5F);
      } else if (flag && !flag3) {
         world.applyText((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.1, (double)blockPos.getZ() + 0.5, "random.click", 0.4F, 0.7F);
      } else if (!flag && flag3) {
         world.applyText(
            (double)blockPos.getX() + 0.5,
            (double)blockPos.getY() + 0.1,
            (double)blockPos.getZ() + 0.5,
            "random.bowhit",
            0.4F,
            1.2F / (world.random.nextFloat() * 0.2F + 0.9F)
         );
      }
   }

   @Override
   public int processAction2(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return !(Boolean)blockState.getComparable(internalImmutableSetEntry) ? 0 : (blockState.getComparable(autoWalkPath) == enumFacing ? 15 : 0);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   private boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.processAction7(world, blockPos)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 8;
      }

      if ((Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         count |= 4;
      }

      return count;
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockTripwireHook() {
      super(Material.material22);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
      );
      this.processAction(ItemGroupManager.itemGroupManager9);
      this.processAction(true);
   }
}
