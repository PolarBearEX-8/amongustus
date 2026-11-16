package client.util;

import com.google.common.collect.AbstractIterator;
import net.minecraft.util.BlockPos;

public class AuxiliaryAbstractIteratorAdapter extends AbstractIterator {
   public BlockPos blockPos;
   public final ExtendedIterableAdapter extendedIterableAdapter;

   public BlockPos getBlockPos() {
      if (this.blockPos == null) {
         this.blockPos = this.extendedIterableAdapter.blockPos;
         return this.blockPos;
      } else if (this.blockPos.equals(this.extendedIterableAdapter.blockPos2)) {
         return (BlockPos)this.endOfData();
      } else {
         int coordX = this.blockPos.getX();
         int coordY = this.blockPos.getY();
         int coordZ = this.blockPos.getZ();
         if (coordX < this.extendedIterableAdapter.blockPos2.getX()) {
            coordX++;
         } else if (coordY < this.extendedIterableAdapter.blockPos2.getY()) {
            coordX = this.extendedIterableAdapter.blockPos.getX();
            coordY++;
         } else if (coordZ < this.extendedIterableAdapter.blockPos2.getZ()) {
            coordX = this.extendedIterableAdapter.blockPos.getX();
            coordY = this.extendedIterableAdapter.blockPos.getY();
            coordZ++;
         }

         this.blockPos = new BlockPos(coordX, coordY, coordZ);
         return this.blockPos;
      }
   }

   public AuxiliaryAbstractIteratorAdapter(ExtendedIterableAdapter extendedIterableAdapter2) {
      this.extendedIterableAdapter = extendedIterableAdapter2;
      this.blockPos = null;
   }

   public Object computeNext() {
      return this.getBlockPos();
   }
}
