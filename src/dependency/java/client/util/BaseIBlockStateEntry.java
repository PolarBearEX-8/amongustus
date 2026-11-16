package client.util;

import client.render.WorldRendererArrayEntry;
import client.setting.BlockSolidType;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.item.GoldenLeggings;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BaseIBlockStateEntry {
   public IBlockState iBlockState;
   public BlockPos blockPos;
   public int count4 = -1;
   public int count = -1;
   public int count7 = -1;
   public int count2 = -1;
   public float[] factorArray = new float[EnumFacing.VALUES.length * 2];
   public BitSet bitSet = new BitSet(3);
   public AdvancedFloatArrayEntry advancedFloatArrayEntry = new AdvancedFloatArrayEntry();
   public ExtendedBlockPosAdapter extendedBlockPosAdapter = null;
   public boolean[] enabledArray = null;
   public boolean[] enabledArray2 = null;
   public boolean[] enabledArray3 = null;
   public EnumFacing[] enumFacingArray = null;
   public List list2 = new ArrayList();
   public List list = new ArrayList();
   public LocalIntArrayEntry[] localIntArrayEntryArray2 = new LocalIntArrayEntry[1];
   public LocalIntArrayEntry[] localIntArrayEntryArray3 = new LocalIntArrayEntry[2];
   public LocalIntArrayEntry[] localIntArrayEntryArray = new LocalIntArrayEntry[3];
   public LocalIntArrayEntry[] localIntArrayEntryArray4 = new LocalIntArrayEntry[4];
   public WorldRendererArrayEntry worldRendererArrayEntry = null;
   public ManagedListEntry[] managedListEntryArray = new ManagedListEntry[BlockSolidType.values().length];
   public boolean enabled = false;
   public static final int count5 = -1;
   public static final int count6 = 0;
   public static final int count3 = 1;

   public void setWorldRendererArrayEntry(WorldRendererArrayEntry worldRendererArrayEntry2) {
      this.worldRendererArrayEntry = worldRendererArrayEntry2;
   }

   public int getCountAsIndex() {
      if (this.count < 0) {
         if (this.iBlockState instanceof IBlockStateAdapter) {
            IBlockStateAdapter iBlockState2 = (IBlockStateAdapter)this.iBlockState;
            this.count = iBlockState2.getCountAsIndex();
         } else {
            this.count = this.iBlockState.getBlock().getIndex(this.iBlockState);
         }
      }

      return this.count;
   }

   public LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(LocalIntArrayEntry localIntArrayEntry) {
      this.localIntArrayEntryArray2[0] = localIntArrayEntry;
      return this.localIntArrayEntryArray2;
   }

   public LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(LocalIntArrayEntry localIntArrayEntry, LocalIntArrayEntry localIntArrayEntry2) {
      this.localIntArrayEntryArray3[0] = localIntArrayEntry;
      this.localIntArrayEntryArray3[1] = localIntArrayEntry2;
      return this.localIntArrayEntryArray3;
   }

   public ExtendedBlockPosAdapter getExtendedBlockPosAdapter() {
      if (this.extendedBlockPosAdapter == null) {
         this.extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);
      }

      return this.extendedBlockPosAdapter;
   }

   public boolean[] getFlagArray() {
      if (this.enabledArray3 == null) {
         this.enabledArray3 = new boolean[4];
      }

      return this.enabledArray3;
   }

   public WorldRendererArrayEntry getWorldRendererArrayEntry() {
      return this.worldRendererArrayEntry;
   }

   public AdvancedFloatArrayEntry getAdvancedFloatArrayEntry() {
      return this.advancedFloatArrayEntry;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public BitSet getBitSet() {
      return this.bitSet;
   }

   public void applyIBlockState(IBlockState blockState, BlockPos blockPos2) {
      if (this.iBlockState != blockState || this.blockPos != blockPos2) {
         this.iBlockState = blockState;
         this.blockPos = blockPos2;
         this.count4 = -1;
         this.count = -1;
         this.count7 = -1;
         this.count2 = -1;
         this.bitSet.clear();
      }
   }

   public List getList2() {
      return this.list2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public EnumFacing[] resolveEnumFacingArray(EnumFacing enumFacing, EnumFacing enumFacing2, EnumFacing enumFacing3, EnumFacing enumFacing4) {
      EnumFacing[] enumFacingArray2 = this.getEnumFacingArray();
      enumFacingArray2[0] = enumFacing;
      enumFacingArray2[1] = enumFacing2;
      enumFacingArray2[2] = enumFacing3;
      enumFacingArray2[3] = enumFacing4;
      return enumFacingArray2;
   }

   public LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(
      LocalIntArrayEntry localIntArrayEntry, LocalIntArrayEntry localIntArrayEntry2, LocalIntArrayEntry localIntArrayEntry3, LocalIntArrayEntry localIntArrayEntry4
   ) {
      this.localIntArrayEntryArray4[0] = localIntArrayEntry;
      this.localIntArrayEntryArray4[1] = localIntArrayEntry2;
      this.localIntArrayEntryArray4[2] = localIntArrayEntry3;
      this.localIntArrayEntryArray4[3] = localIntArrayEntry4;
      return this.localIntArrayEntryArray4;
   }

   public boolean isActiveAsFlag() {
      if (this.count2 == -1) {
         if (BuildManager.isActive71() && this.iBlockState.getBlock() instanceof DecayableManager) {
            this.count2 = 1;
         } else {
            this.count2 = 0;
         }
      }

      return this.count2 == 1;
   }

   public boolean[] getFlagArray2() {
      if (this.enabledArray2 == null) {
         this.enabledArray2 = new boolean[4];
      }

      return this.enabledArray2;
   }

   public LocalIntArrayEntry[] resolveLocalIntArrayEntryArray(LocalIntArrayEntry localIntArrayEntry, LocalIntArrayEntry localIntArrayEntry2, LocalIntArrayEntry localIntArrayEntry3) {
      this.localIntArrayEntryArray[0] = localIntArrayEntry;
      this.localIntArrayEntryArray[1] = localIntArrayEntry2;
      this.localIntArrayEntryArray[2] = localIntArrayEntry3;
      return this.localIntArrayEntryArray;
   }

   public int getCount() {
      if (this.count4 < 0) {
         if (this.iBlockState instanceof IBlockStateAdapter) {
            IBlockStateAdapter iBlockState2 = (IBlockStateAdapter)this.iBlockState;
            this.count4 = iBlockState2.getCount2();
         } else {
            this.count4 = Block.getIdFromBlock(this.iBlockState.getBlock());
         }
      }

      return this.count4;
   }

   public IBlockState getIBlockState() {
      return this.iBlockState;
   }

   public EnumFacing[] getEnumFacingArray() {
      if (this.enumFacingArray == null) {
         this.enumFacingArray = new EnumFacing[4];
      }

      return this.enumFacingArray;
   }

   public ManagedListEntry resolveManagedListEntry(BlockSolidType blockSolidType) {
      ManagedListEntry managedListEntry = this.managedListEntryArray[blockSolidType.ordinal()];
      if (managedListEntry == null) {
         managedListEntry = new ManagedListEntry();
         this.managedListEntryArray[blockSolidType.ordinal()] = managedListEntry;
      }

      return managedListEntry;
   }

   public boolean[] getFlagArrayAsFlagArray() {
      if (this.enabledArray == null) {
         this.enabledArray = new boolean[4];
      }

      return this.enabledArray;
   }

   public List resolveList(LocalIntArrayEntry[] localIntArrayEntryArray5) {
      this.list.clear();
      if (localIntArrayEntryArray5 != null) {
         for (int index = 0; index < localIntArrayEntryArray5.length; index++) {
            LocalIntArrayEntry localIntArrayEntry = localIntArrayEntryArray5[index];
            this.list.add(localIntArrayEntry);
         }
      }

      return this.list;
   }

   public boolean isActive() {
      return this.count7 == 1;
   }

   public boolean matchesLocalIntArrayEntry(LocalIntArrayEntry localIntArrayEntry) {
      if (this.count7 < 0) {
         if (localIntArrayEntry instanceof GoldenLeggings) {
            this.count7 = 1;
         } else {
            this.count7 = 0;
         }
      }

      return this.count7 == 1;
   }

   public float[] getFactorArray() {
      return this.factorArray;
   }

   public boolean matchesList(List list3) {
      if (this.count7 == -1 && list3.size() > 0) {
         if (list3.get(0) instanceof GoldenLeggings) {
            this.count7 = 1;
         } else {
            this.count7 = 0;
         }
      }

      return this.count7 == 1;
   }

   public BaseIBlockStateEntry(IBlockState blockState, BlockPos blockPos2) {
      this.iBlockState = blockState;
      this.blockPos = blockPos2;
   }
}
