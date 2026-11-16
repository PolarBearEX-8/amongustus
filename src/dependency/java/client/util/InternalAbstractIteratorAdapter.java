package client.util;

import com.google.common.collect.AbstractIterator;
import net.minecraft.block.AuxiliaryBlockPosAdapter;

public class InternalAbstractIteratorAdapter extends AbstractIterator {
   public AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter;
   public final InternalIterableAdapter internalIterableAdapter;

   public AuxiliaryBlockPosAdapter getAuxiliaryBlockPosAdapter() {
      if (this.auxiliaryBlockPosAdapter == null) {
         this.auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter(
            this.internalIterableAdapter.blockPos2.getX(), this.internalIterableAdapter.blockPos2.getY(), this.internalIterableAdapter.blockPos2.getZ()
         );
         return this.auxiliaryBlockPosAdapter;
      } else if (this.auxiliaryBlockPosAdapter.equals(this.internalIterableAdapter.blockPos)) {
         return (AuxiliaryBlockPosAdapter)this.endOfData();
      } else {
         int coordX = this.auxiliaryBlockPosAdapter.getX();
         int coordY = this.auxiliaryBlockPosAdapter.getY();
         int coordZ = this.auxiliaryBlockPosAdapter.getZ();
         if (coordX < this.internalIterableAdapter.blockPos.getX()) {
            coordX++;
         } else if (coordY < this.internalIterableAdapter.blockPos.getY()) {
            coordX = this.internalIterableAdapter.blockPos2.getX();
            coordY++;
         } else if (coordZ < this.internalIterableAdapter.blockPos.getZ()) {
            coordX = this.internalIterableAdapter.blockPos2.getX();
            coordY = this.internalIterableAdapter.blockPos2.getY();
            coordZ++;
         }

         AuxiliaryBlockPosAdapter.computeCount2(this.auxiliaryBlockPosAdapter, coordX);
         AuxiliaryBlockPosAdapter.computeCountByAuxiliaryBlockPo(this.auxiliaryBlockPosAdapter, coordY);
         AuxiliaryBlockPosAdapter.computeCount(this.auxiliaryBlockPosAdapter, coordZ);
         return this.auxiliaryBlockPosAdapter;
      }
   }

   public Object computeNext() {
      return this.getAuxiliaryBlockPosAdapter();
   }

   public InternalAbstractIteratorAdapter(InternalIterableAdapter internalIterableAdapter2) {
      this.internalIterableAdapter = internalIterableAdapter2;
      this.auxiliaryBlockPosAdapter = null;
   }
}
