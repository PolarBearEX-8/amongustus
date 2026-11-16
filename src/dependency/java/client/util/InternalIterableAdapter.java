package client.util;

import java.util.Iterator;
import net.minecraft.util.BlockPos;

public final class InternalIterableAdapter implements Iterable {
   public final BlockPos blockPos2;
   public final BlockPos blockPos;

   public InternalIterableAdapter(BlockPos blockPos3, BlockPos blockPos4) {
      this.blockPos2 = blockPos3;
      this.blockPos = blockPos4;
   }

   @Override
   public Iterator iterator() {
      return new InternalAbstractIteratorAdapter(this);
   }
}
