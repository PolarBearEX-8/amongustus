package client.util;

import java.util.Iterator;
import net.minecraft.util.BlockPos;

public final class SecondaryIterableAdapter implements Iterable {
   public final BlockPos blockPos;
   public final BlockPos blockPos2;

   public SecondaryIterableAdapter(BlockPos blockPos3, BlockPos blockPos4) {
      this.blockPos = blockPos3;
      this.blockPos2 = blockPos4;
   }

   @Override
   public Iterator iterator() {
      return new AbstractIteratorAdapter(this);
   }
}
