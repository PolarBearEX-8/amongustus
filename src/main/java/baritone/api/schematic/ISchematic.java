package baritone.api.schematic;

import net.minecraft.util.EnumFacing;
import java.util.List;
import net.minecraft.block.state.IBlockState;

public interface ISchematic {
   default int size(EnumFacing.Axis axis) {
      switch (axis) {
         case X:
            return this.widthX();
         case Y:
            return this.heightY();
         case Z:
            return this.lengthZ();
         default:
            throw new UnsupportedOperationException(axis + "");
      }
   }

   default boolean inSchematic(int n, int n2, int n3, IBlockState state) {
      return n >= 0 && n < this.widthX() && n2 >= 0 && n2 < this.heightY() && n3 >= 0 && n3 < this.lengthZ();
   }

   IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list);

   default void reset() {
   }

   int heightY();

   int widthX();

   int lengthZ();

}
