package net.augustus.render.waveycape;

public class StickConstraint {
   public float length;
   public StickPoint pointB;
   public StickPoint pointA;

   public StickConstraint(StickPoint stickPoint, StickPoint stickPoint2, float f) {
      this.pointA = stickPoint;
      this.pointB = stickPoint2;
      this.length = f;
   }
}
