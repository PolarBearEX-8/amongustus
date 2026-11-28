package baritone.utils.schematic;

import baritone.api.schematic.AbstractSchematic;
import baritone.api.schematic.IStaticSchematic;
import java.util.List;
import net.minecraft.block.state.IBlockState;

public class StaticSchematic extends AbstractSchematic implements IStaticSchematic {
   public IBlockState[][][] states;

   @Override
   public IBlockState[] getColumn(int n, int n2) {
      return this.states[n][n2];
   }

   @Override
   public IBlockState getDirect(int n, int n2, int n3) {
      return this.states[n][n3][n2];
   }

   @Override
   public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
      return this.states[n][n3][n2];
   }
}
