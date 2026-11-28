package baritone.api.schematic.mask.shape;


import baritone.api.schematic.mask.AbstractMask;
import baritone.api.schematic.mask.StaticMask;
public final class SphereMask extends AbstractMask implements StaticMask {
   private final double radiusX;
   private final double radiusY;
   private final double radiusZ;
   private final double radiusXSq;
   private final double radiusYSq;
   private final double radiusZSq;
   private final boolean filled;

   private boolean outsideEllipsoid(double d, double d2, double d3) {
      return d * d / this.radiusXSq + d2 * d2 / this.radiusYSq + d3 * d3 / this.radiusZSq > 1.0;
   }

   @Override
   public boolean partOfMask(int n, int n2, int n3) {
      double d = Math.abs((double)n + 0.5 - this.radiusX);
      double d2 = Math.abs((double)n2 + 0.5 - this.radiusY);
      double d3 = Math.abs((double)n3 + 0.5 - this.radiusZ);
      return this.outsideEllipsoid(d, d2, d3)
         ? false
         : this.filled || this.outsideEllipsoid(d + 1.0, d2, d3) || this.outsideEllipsoid(d, d2 + 1.0, d3) || this.outsideEllipsoid(d, d2, d3 + 1.0);
   }

   public SphereMask(int n, int n2, int n3, boolean flag) {
      super(n, n2, n3);
      this.radiusX = (double)n / 2.0;
      this.radiusY = (double)n2 / 2.0;
      this.radiusZ = (double)n3 / 2.0;
      this.radiusXSq = this.radiusX * this.radiusX;
      this.radiusYSq = this.radiusY * this.radiusY;
      this.radiusZSq = this.radiusZ * this.radiusZ;
      this.filled = flag;
   }
}
