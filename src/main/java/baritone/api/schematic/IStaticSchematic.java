package baritone.api.schematic;

import net.minecraft.block.state.IBlockState;

public interface IStaticSchematic extends ISchematic {
   default IBlockState[] getColumn(int n, int n2) {
      IBlockState[] states = new IBlockState[this.heightY()];

      for (int i = 0; i < this.heightY(); i++) {
         states[i] = this.getDirect(n, i, n2);
      }

      return states;
   }

   IBlockState getDirect(int n, int n2, int n3);
}
