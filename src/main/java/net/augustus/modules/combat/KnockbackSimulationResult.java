package net.augustus.modules.combat;

import net.minecraft.util.Vec3;

public final class KnockbackSimulationResult {
   public final boolean fallsOffEdge;
   public final Vec3 endPosition;
   public final double dropScore;

   public KnockbackSimulationResult(boolean flag, Vec3 vec, double d) {
      this.fallsOffEdge = flag;
      this.endPosition = vec;
      this.dropScore = d;
   }
}
