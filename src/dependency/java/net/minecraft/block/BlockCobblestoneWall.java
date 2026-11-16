package net.minecraft.block;

import client.setting.SpecialOptionType;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.InternalImmutableSetEntry;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCobblestoneWall extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry5 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("up");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("north");
   public static final InternalImmutableSetEntry internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("east");
   public static final InternalImmutableSetEntry internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("south");
   public static final InternalImmutableSetEntry internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("west");
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("variant", SpecialOptionType.class);

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      Block block = iBlockAccess.getIBlockState(count, count2, count3).getBlock();
      return block == Blocks.block145
         ? false
         : (
            block != this && !(block instanceof BlockAcaciaFenceGate)
               ? (block.coreItemEntry.isActive3() && block.processAction14() ? block.coreItemEntry != Material.material13 : false)
               : true
         );
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry5, !iBlockAccess.checkCondition(count, count2 + 1, count3))
         .resolveIBlockState(internalImmutableSetEntry, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 - 1))
         .resolveIBlockState(internalImmutableSetEntry4, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count + 1, count2, count3))
         .resolveIBlockState(internalImmutableSetEntry3, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 + 1))
         .resolveIBlockState(internalImmutableSetEntry2, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count - 1, count2, count3));
   }

   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      boolean flag = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 - 1);
      boolean flag2 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 + 1);
      boolean flag3 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count - 1, count2, count3);
      boolean flag4 = this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count + 1, count2, count3);
      float factor = 0.25F;
      float factor2 = 0.75F;
      float factor3 = 0.25F;
      float factor4 = 0.75F;
      float factor5 = 1.0F;
      if (flag) {
         factor3 = 0.0F;
      }

      if (flag2) {
         factor4 = 1.0F;
      }

      if (flag3) {
         factor = 0.0F;
      }

      if (flag4) {
         factor2 = 1.0F;
      }

      if (flag && flag2 && !flag3 && !flag4) {
         factor5 = 0.8125F;
         factor = 0.3125F;
         factor2 = 0.6875F;
      } else if (!flag && !flag2 && flag3 && flag4) {
         factor5 = 0.8125F;
         factor3 = 0.3125F;
         factor4 = 0.6875F;
      }

      this.setBlockBounds(factor, 0.0F, factor3, factor2, factor5, factor4);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, SpecialOptionType.resolveSpecialOptionType(count));
   }

   @Override
   public void processAction(Item item, ItemGroupManager itemGroupManager, List list) {
      for (SpecialOptionType specialOptionType : SpecialOptionType.values()) {
         list.add(new ItemStack(item, 1, specialOptionType.getCount()));
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return false;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing != EnumFacing.DOWN || super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public int getMetaFromState(IBlockState blockState) {
      return ((SpecialOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this,
         new IProperty[]{
            internalImmutableSetEntry5,
            internalImmutableSetEntry,
            internalImmutableSetEntry4,
            internalImmutableSetEntry2,
            internalImmutableSetEntry3,
            immutableSetEntry
         }
      );
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((SpecialOptionType)blockState.getComparable(immutableSetEntry)).getCount();
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      this.processAction(world, count, count2, count3);
      this.coordOffset6 = 1.5;
      return super.processAction(world, blockState, count, count2, count3);
   }

   @Override
   public String getText() {
      return I18n.getString2(this.getTextAsText() + "." + SpecialOptionType.NORMAL.getLabelText() + ".name");
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockCobblestoneWall(Block block) {
      super(block.coreItemEntry);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(internalImmutableSetEntry5, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(immutableSetEntry, SpecialOptionType.NORMAL)
      );
      this.getBlock5(block.scaleFactor22);
      this.setHardness(block.scaleFactor21 / 3.0F);
      this.processAction(block.stepManager20);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
