package net;

import client.setting.BlockSolidType;
import client.util.BaseIBlockStateEntry;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.LocalIntArrayEntry;
import client.util.McpatcherCtmDefaultManager;
import client.util.NotFoundManager;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.block.BlocksGrassTopManager;
import net.minecraft.client.model.ModelManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class zzzz47r4Customizer {
   public static final List list = ImmutableList.of();

   private static LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      LocalIntArrayEntry localIntArrayEntry, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, long value, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      LocalIntArrayEntry localIntArrayEntry2 = localIntArrayEntry;
      if (baseIBlockStateEntry.matchesLocalIntArrayEntry(localIntArrayEntry)) {
         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry);
      } else {
         if (BuildManager.isActive47()) {
            LocalIntArrayEntry[] localIntArrayEntryArray = McpatcherCtmDefaultManager.resolveLocalIntArrayEntryArray(iBlockAccess, blockState, blockPos, localIntArrayEntry, baseIBlockStateEntry);
            if (localIntArrayEntryArray.length != 1 || localIntArrayEntryArray[0] != localIntArrayEntry) {
               return localIntArrayEntryArray;
            }
         }

         if (BuildManager.isActive48()) {
            localIntArrayEntry2 = NotFoundManager.resolveLocalIntArrayEntry(blockPos, localIntArrayEntry);
            if (localIntArrayEntry2 != localIntArrayEntry) {
               return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2);
            }
         }

         return baseIBlockStateEntry.resolveLocalIntArrayEntryArray(localIntArrayEntry2);
      }
   }

   public static IBakedModel resolveIBakedModel(IBakedModel iBakedModel, IBlockState blockState, BaseIBlockStateEntry baseIBlockStateEntry) {
      IBakedModel iBakedModel2 = iBakedModel;
      if (baseIBlockStateEntry.isActiveAsFlag()) {
         iBakedModel2 = ModelManager.resolveIBakedModel(iBakedModel, blockState);
      }

      return iBakedModel2;
   }

   private static BlockSolidType resolveBlockSolidType(BlockSolidType blockSolidType) {
      return blockSolidType != null && blockSolidType != BlockSolidType.SOLID ? blockSolidType : BlockSolidType.CUTOUT_MIPPED;
   }

   public static List resolveList(
      List list2, IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing enumFacing, BlockSolidType blockSolidType, long value, BaseIBlockStateEntry baseIBlockStateEntry
   ) {
      List list3 = list2;
      if (enumFacing != null) {
         if (baseIBlockStateEntry.isActiveAsFlag() && ModelManager.matchesIBlockState(iBlockAccess.getBlockState(blockPos.offset(enumFacing)), blockState)) {
            return list;
         }

         if (!baseIBlockStateEntry.matchesList(list2) && BuildManager.isActive36()) {
            list3 = BlocksGrassTopManager.resolveListByIBlockAccess(iBlockAccess, blockState, blockPos, enumFacing, list2);
         }
      }

      List list4 = baseIBlockStateEntry.getList2();
      list4.clear();

      for (int index = 0; index < list3.size(); index++) {
         LocalIntArrayEntry localIntArrayEntry = (LocalIntArrayEntry)list3.get(index);
         LocalIntArrayEntry[] localIntArrayEntryArray = resolveLocalIntArrayEntryArray(localIntArrayEntry, iBlockAccess, blockState, blockPos, enumFacing, value, baseIBlockStateEntry);
         if (index == 0 && list3.size() == 1 && localIntArrayEntryArray.length == 1 && localIntArrayEntryArray[0] == localIntArrayEntry && localIntArrayEntry.getLocalIntArrayEntry() == null) {
            return list3;
         }

         for (int index2 = 0; index2 < localIntArrayEntryArray.length; index2++) {
            LocalIntArrayEntry localIntArrayEntry2 = localIntArrayEntryArray[index2];
            list4.add(localIntArrayEntry2);
            if (localIntArrayEntry2.getLocalIntArrayEntry() != null) {
               baseIBlockStateEntry.resolveManagedListEntry(resolveBlockSolidType(blockSolidType)).applyLocalIntArrayEntry(localIntArrayEntry2.getLocalIntArrayEntry(), blockState);
               baseIBlockStateEntry.setEnabled(true);
            }
         }
      }

      return list4;
   }
}
