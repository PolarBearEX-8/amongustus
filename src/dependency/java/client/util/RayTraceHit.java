package client.util;

import net.minecraft.util.MovingObjectPosition;

public class RayTraceHit {
   public MovingObjectPosition movingObjectPosition;
   public float scaleFactor;

   public RayTraceHit(MovingObjectPosition hitResult, float factor) {
      this.movingObjectPosition = hitResult;
      this.scaleFactor = factor;
   }
}
