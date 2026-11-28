package net.augustus.modules.world.scaffold;

import net.minecraft.util.MovingObjectPosition;

public class ScaffoldRaycastResult {
   public MovingObjectPosition hitResult;
   public float pitch;

   public ScaffoldRaycastResult(MovingObjectPosition hit, float f) {
      this.hitResult = hit;
      this.pitch = f;
   }
}
