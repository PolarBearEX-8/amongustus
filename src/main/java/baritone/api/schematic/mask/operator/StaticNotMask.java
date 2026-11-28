package baritone.api.schematic.mask.operator;


import baritone.api.schematic.mask.AbstractMask;
import baritone.api.schematic.mask.StaticMask;
public final class StaticNotMask extends AbstractMask implements StaticMask {
   private final StaticMask source;

   @Override
   public boolean partOfMask(int n, int n2, int n3) {
      return !this.source.partOfMask(n, n2, n3);
   }

   public StaticNotMask(StaticMask staticMask) {
      super(staticMask.widthX(), staticMask.heightY(), staticMask.lengthZ());
      this.source = staticMask;
   }
}
