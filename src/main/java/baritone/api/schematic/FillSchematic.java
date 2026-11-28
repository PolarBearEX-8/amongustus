package baritone.api.schematic;

import baritone.api.utils.BlockOptionalMeta;
import java.util.List;
import net.minecraft.block.state.IBlockState;

public class FillSchematic extends AbstractSchematic {
   private final BlockOptionalMeta bom;

   public FillSchematic(int n, int n2, int n3, IBlockState state) {
      this(n, n2, n3, new BlockOptionalMeta(state.getBlock(), state.getBlock().getMetaFromState(state)));
   }

   public BlockOptionalMeta getBom() {
      return this.bom;
   }

   @Override
   public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
      if (this.bom.matches3(state)) {
         return state;
      } else {
         for (IBlockState state2 : (Iterable<IBlockState>)(list)) {
            if (this.bom.matches3(state2)) {
               return state2;
            }
         }

         return this.bom.getAnyBlockState();
      }
   }

   public FillSchematic(int n, int n2, int n3, BlockOptionalMeta blockOptionalMeta) {
      super(n, n2, n3);
      this.bom = blockOptionalMeta;
   }
}
