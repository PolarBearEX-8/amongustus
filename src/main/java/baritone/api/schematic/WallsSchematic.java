package baritone.api.schematic;

import net.minecraft.block.state.IBlockState;

public class WallsSchematic extends MaskSchematic {
   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      return n == 0 || n3 == 0 || n == this.widthX() - 1 || n3 == this.lengthZ() - 1;
   }

   public WallsSchematic(ISchematic schematic2) {
      super(schematic2);
   }
}
