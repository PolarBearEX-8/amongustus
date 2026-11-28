package baritone.api.schematic;

import baritone.api.schematic.mask.Mask;
import java.util.List;
import net.minecraft.block.state.IBlockState;

public abstract class MaskSchematic extends AbstractSchematic {
   private final ISchematic schematic;

   public static MaskSchematic create(ISchematic schematic2, Mask mask) {
      return new MaskSchematic.DefaultMaskSchematic(schematic2, mask);
   }

   public MaskSchematic(ISchematic schematic2) {
      super(schematic2.widthX(), schematic2.heightY(), schematic2.lengthZ());
      this.schematic = schematic2;
   }

   @Override
   public boolean inSchematic(int n, int n2, int n3, IBlockState state) {
      return this.schematic.inSchematic(n, n2, n3, state) && this.partOfMask(n, n2, n3, state);
   }

   public abstract boolean partOfMask(int n, int n2, int n3, IBlockState state);

   @Override
   public IBlockState desiredState(int n, int n2, int n3, IBlockState state, List list) {
      return this.schematic.desiredState(n, n2, n3, state, list);
   }


   public static final class DefaultMaskSchematic extends MaskSchematic {
      public final Mask mask;

      @Override
      public boolean partOfMask(int n, int n2, int n3, IBlockState state) {
         return this.mask.partOfMask(n, n2, n3, state);
      }

      public DefaultMaskSchematic(ISchematic schematic2, Mask mask2) {
         super(schematic2);
         this.mask = mask2;
      }
   }
}
