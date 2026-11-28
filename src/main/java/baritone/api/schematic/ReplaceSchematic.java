package baritone.api.schematic;

import baritone.api.utils.BlockOptionalMetaLookup;
import net.minecraft.block.state.IBlockState;

public class ReplaceSchematic extends MaskSchematic {
   private final BlockOptionalMetaLookup filter;
   private final Boolean[][][] cache;

   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      if (this.cache[n][n2][n3] == null) {
         this.cache[n][n2][n3] = this.filter.has3(state);
      }

      return this.cache[n][n2][n3];
   }

   @Override
   public void reset() {
      for (int i = 0; i < this.cache.length; i++) {
         for (int j = 0; j < this.cache[0].length; j++) {
            for (int k = 0; k < this.cache[0][0].length; k++) {
               this.cache[i][j][k] = null;
            }
         }
      }
   }

   public ReplaceSchematic(ISchematic schematic2, BlockOptionalMetaLookup blockOptionalMetaLookup) {
      super(schematic2);
      this.filter = blockOptionalMetaLookup;
      this.cache = new Boolean[this.widthX()][this.heightY()][this.lengthZ()];
   }
}
