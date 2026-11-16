package client.util;

import com.google.common.base.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntitySkull;

public final class SecondaryPredicateAdapter implements Predicate {
   public boolean matchesSecondaryWorldEntry(SecondaryWorldEntry secondaryWorldEntry) {
      return secondaryWorldEntry.getIBlockState() != null
         && secondaryWorldEntry.getIBlockState().getBlock() == Blocks.blockSkull
         && secondaryWorldEntry.getTileEntity() instanceof TileEntitySkull
         && ((TileEntitySkull)secondaryWorldEntry.getTileEntity()).getCount2() == 1;
   }

   public boolean apply(Object entry) {
      return this.matchesSecondaryWorldEntry((SecondaryWorldEntry)entry);
   }
}
