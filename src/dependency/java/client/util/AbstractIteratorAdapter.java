package client.util;

import com.google.common.collect.AbstractIterator;
import net.minecraft.block.ExtendedBlockPosAdapter;

public class AbstractIteratorAdapter extends AbstractIterator {
   public ExtendedBlockPosAdapter extendedBlockPosAdapter;
   public final SecondaryIterableAdapter secondaryIterableAdapter;

   public Object computeNext() {
      return this.getExtendedBlockPosAdapter();
   }

   public ExtendedBlockPosAdapter getExtendedBlockPosAdapter() {
      if (this.extendedBlockPosAdapter == null) {
         this.extendedBlockPosAdapter = new ExtendedBlockPosAdapter(
            this.secondaryIterableAdapter.blockPos.getX(), this.secondaryIterableAdapter.blockPos.getY(), this.secondaryIterableAdapter.blockPos.getZ(), 3
         );
         return this.extendedBlockPosAdapter;
      } else if (this.extendedBlockPosAdapter.equals(this.secondaryIterableAdapter.blockPos2)) {
         return (ExtendedBlockPosAdapter)this.endOfData();
      } else {
         int coordX = this.extendedBlockPosAdapter.getX();
         int coordY = this.extendedBlockPosAdapter.getY();
         int coordZ = this.extendedBlockPosAdapter.getZ();
         if (coordX < this.secondaryIterableAdapter.blockPos2.getX()) {
            coordX++;
         } else if (coordY < this.secondaryIterableAdapter.blockPos2.getY()) {
            coordX = this.secondaryIterableAdapter.blockPos.getX();
            coordY++;
         } else if (coordZ < this.secondaryIterableAdapter.blockPos2.getZ()) {
            coordX = this.secondaryIterableAdapter.blockPos.getX();
            coordY = this.secondaryIterableAdapter.blockPos.getY();
            coordZ++;
         }

         this.extendedBlockPosAdapter.applyIndex(coordX, coordY, coordZ);
         return this.extendedBlockPosAdapter;
      }
   }

   public AbstractIteratorAdapter(SecondaryIterableAdapter secondaryIterableAdapter2) {
      this.secondaryIterableAdapter = secondaryIterableAdapter2;
      this.extendedBlockPosAdapter = null;
   }
}
