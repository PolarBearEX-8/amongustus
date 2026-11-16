package net.minecraft.item;

import client.setting.BlockSolidType;
import java.util.BitSet;
import net.minecraft.util.EnumFacing;

public final class ItemEntryProcessor extends IronShard {
   @Override
   public void ordinal(BlockSolidType blockSolidType) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean matchesEnumFacing(EnumFacing enumFacing, EnumFacing enumFacing2) {
      return false;
   }

   @Override
   public void ordinal4(BlockSolidType blockSolidType) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void ordinal(BlockSolidType blockSolidType, BitSet bitSet) {
      throw new UnsupportedOperationException();
   }
}
