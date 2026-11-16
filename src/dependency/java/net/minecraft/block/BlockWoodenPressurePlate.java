package net.minecraft.block;

import client.setting.ExtendedOptionType;
import client.util.CurveQueue;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWoodenPressurePlate extends TertiaryBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("powered");
   public final ExtendedOptionType extendedOptionType;

   @Override
   public int computeCount(IBlockState blockState) {
      return ((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(internalImmutableSetEntry, count == 1);
   }

   @Override
   public IBlockState resolveIBlockState(IBlockState blockState, int count) {
      return blockState.resolveIBlockState(internalImmutableSetEntry, count > 0);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int computeCount2(World world, BlockPos blockPos) {
      AxisAlignedBB boundingBox = this.resolveAxisAlignedBB(blockPos);
      List list;
      switch (CurveQueue.countArray[this.extendedOptionType.ordinal()]) {
         case 1:
            list = world.resolveListByEntity((Entity)null, boundingBox);
            break;
         case 2:
            list = world.resolveList(EntityLivingBase.class, boundingBox);
            break;
         default:
            return 0;
      }

      if (!list.isEmpty()) {
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            if (!((Entity)iterator.next()).isActive10()) {
               return 15;
            }
         }
      }

      return 0;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (((Boolean)blockState.getComparable(internalImmutableSetEntry)) ? 1 : 0);
   }

   public BlockWoodenPressurePlate(Material material, ExtendedOptionType extendedOptionType2) {
      super(material);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.extendedOptionType = extendedOptionType2;
   }
}
