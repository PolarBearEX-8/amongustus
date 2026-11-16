package client.util;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;

public class SpecialPredicateAdapter implements Predicate {
   public final Block block;

   public boolean matchesIBlockState(IBlockState blockState) {
      return blockState != null && blockState.getBlock() == this.block;
   }

   public static SpecialPredicateAdapter resolveSpecialPredicateAdapter(Block block2) {
      return new SpecialPredicateAdapter(block2);
   }

   private SpecialPredicateAdapter(Block block2) {
      this.block = block2;
   }

   public boolean apply(Object entry) {
      return this.matchesIBlockState((IBlockState)entry);
   }
}
