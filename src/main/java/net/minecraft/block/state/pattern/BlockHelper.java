package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockHelper implements Predicate {
   private final Block block;

   public boolean apply(IBlockState state) {
      return state != null && state.getBlock() == this.block;
   }

   private BlockHelper(Block block2) {
      this.block = block2;
   }

   public boolean apply(Object obj) {
      return this.apply((IBlockState)obj);
   }

   public static BlockHelper forBlock(Block block2) {
      return new BlockHelper(block2);
   }
}
