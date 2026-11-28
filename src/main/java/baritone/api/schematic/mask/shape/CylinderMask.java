package baritone.api.schematic.mask.shape;

import net.minecraft.util.EnumFacing;
import baritone.api.schematic.mask.AbstractMask;
import baritone.api.schematic.mask.StaticMask;

public final class CylinderMask extends AbstractMask implements StaticMask {
   private final double centerA;
   private final double centerB;
   private final double radiusASq;
   private final double radiusBSq;
   private final boolean filled;
   private final EnumFacing.Axis alignment;

   private boolean outsideEllipsoid(double d, double d2) {
      return d * d / this.radiusASq + d2 * d2 / this.radiusBSq > 1.0;
   }

   private static int selectSecondAxis(int n, int n2, EnumFacing.Axis axis) {
      return axis == EnumFacing.Axis.Z ? n : n2;
   }

   @Override
   public boolean partOfMask(int n, int n2, int n3) {
      double d = Math.abs((double)selectFirstAxis(n, n2, this.alignment) + 0.5 - this.centerA);
      double d2 = Math.abs((double)selectSecondAxis(n2, n3, this.alignment) + 0.5 - this.centerB);
      return this.outsideEllipsoid(d, d2) ? false : this.filled || this.outsideEllipsoid(d + 1.0, d2) || this.outsideEllipsoid(d, d2 + 1.0);
   }

   private static int selectFirstAxis(int n, int n2, EnumFacing.Axis axis) {
      return axis == EnumFacing.Axis.X ? n2 : n;
   }

   public CylinderMask(int n, int n2, int n3, boolean flag, EnumFacing.Axis axis) {
      super(n, n2, n3);
      this.centerA = (double)selectFirstAxis(n, n2, axis) / 2.0;
      this.centerB = (double)selectSecondAxis(n2, n3, axis) / 2.0;
      this.radiusASq = (this.centerA - 1.0) * (this.centerA - 1.0);
      this.radiusBSq = (this.centerB - 1.0) * (this.centerB - 1.0);
      this.filled = flag;
      this.alignment = axis;
   }
}
