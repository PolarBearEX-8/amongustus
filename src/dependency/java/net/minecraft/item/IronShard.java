package net.minecraft.item;

import client.setting.BlockSolidType;
import client.util.BridgeIntArrayEntry;
import client.util.PrimaryLongEntry;
import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class IronShard {
   public static final IronShard ironShard = new ItemEntryProcessor();
   public final boolean[] enabledArray2 = new boolean[RenderChunk.blockSolidTypeArray2.length];
   public final boolean[] enabledArray = new boolean[RenderChunk.blockSolidTypeArray2.length];
   public boolean enabled = true;
   public final List list = Lists.newArrayList();
   public PrimaryLongEntry primaryLongEntry = new PrimaryLongEntry();
   public BridgeIntArrayEntry bridgeIntArrayEntry;
   public BitSet[] bitSetArray = new BitSet[RenderChunk.blockSolidTypeArray2.length];

   public void add(TileEntity tileEntity) {
      this.list.add(tileEntity);
   }

   public BitSet ordinal2(BlockSolidType blockSolidType) {
      return this.bitSetArray[blockSolidType.ordinal()];
   }

   public void setPrimaryLongEntry(PrimaryLongEntry primaryLongEntry2) {
      this.primaryLongEntry = primaryLongEntry2;
   }

   public boolean ordinalByBlockSolidType(BlockSolidType blockSolidType) {
      return this.enabledArray[blockSolidType.ordinal()];
   }

   public void ordinal(BlockSolidType blockSolidType) {
      this.enabled = false;
      this.enabledArray2[blockSolidType.ordinal()] = true;
   }

   public List getList() {
      return this.list;
   }

   public boolean ordinal3(BlockSolidType blockSolidType) {
      return !this.enabledArray2[blockSolidType.ordinal()];
   }

   public BridgeIntArrayEntry getBridgeIntArrayEntry() {
      return this.bridgeIntArrayEntry;
   }

   public void ordinal4(BlockSolidType blockSolidType) {
      this.enabledArray[blockSolidType.ordinal()] = true;
   }

   public boolean matchesEnumFacing(EnumFacing enumFacing, EnumFacing enumFacing2) {
      return this.primaryLongEntry.matchesEnumFacing(enumFacing, enumFacing2);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setBridgeIntArrayEntry(BridgeIntArrayEntry bridgeIntArrayEntry2) {
      this.bridgeIntArrayEntry = bridgeIntArrayEntry2;
   }

   public void ordinal(BlockSolidType blockSolidType, BitSet bitSet) {
      this.bitSetArray[blockSolidType.ordinal()] = bitSet;
   }
}
