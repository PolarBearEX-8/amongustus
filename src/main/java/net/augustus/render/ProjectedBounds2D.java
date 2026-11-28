package net.augustus.render;

public class ProjectedBounds2D {
   public final double minY;
   public final double maxY;
   public final double maxX;
   public final double minX;

   public ProjectedBounds2D(double d, double d2, double d3, double d4) {
      this.minX = d;
      this.minY = d2;
      this.maxX = d3;
      this.maxY = d4;
   }
}
