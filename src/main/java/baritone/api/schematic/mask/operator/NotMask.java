package baritone.api.schematic.mask.operator;

import baritone.api.schematic.mask.AbstractMask;
import baritone.api.schematic.mask.Mask;
import net.minecraft.block.state.IBlockState;

public final class NotMask extends AbstractMask {
   private final Mask source;

   public NotMask(Mask mask) {
      super(mask.widthX(), mask.heightY(), mask.lengthZ());
      this.source = mask;
   }

   @Override
   public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
      return !this.source.partOfMask(n, n2, n3, state);
   }
}
