package client.util;

import com.google.common.base.Predicate;
import net.minecraft.block.Blocks;

public final class AltPredicateAdapter implements Predicate {
   public boolean matchesIBlockState(IBlockState blockState) {
      return blockState != null && (blockState.getBlock() == Blocks.block146 || blockState.getBlock() == Blocks.block120);
   }

   public boolean apply(Object entry) {
      return this.matchesIBlockState((IBlockState)entry);
   }
}
