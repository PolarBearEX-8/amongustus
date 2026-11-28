package net.augustus.modules.combat.killaura;

public class HitVecCandidate {
   public boolean[] visibilityFlags = null;
   public double y;
   public double z;
   public double x;

   public HitVecCandidate(double d, double d2, double d3) {
      this.x = d;
      this.y = d2;
      this.z = d3;
   }
}
