package client.util;

import java.util.Iterator;
import net.minecraft.util.BlockPos;

public final class ExtendedIterableAdapter implements Iterable {
   public final BlockPos blockPos;
   public final BlockPos blockPos2;

   @Override
   public Iterator iterator() {
      return new AuxiliaryAbstractIteratorAdapter(this);
   }

   public ExtendedIterableAdapter(BlockPos blockPos3, BlockPos blockPos4) {
      this.blockPos = blockPos3;
      this.blockPos2 = blockPos4;
   }
}
