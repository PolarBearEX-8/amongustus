package baritone.api.pathing.goals;

import baritone.api.BaritoneAPI;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class GoalXZ implements Goal {
   private static final double SQRT_2 = Math.sqrt(2.0);
   private final int x;
   private final int z;

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n == this.x && n3 == this.z;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return calculate((double)(n - this.x), (double)(n3 - this.z));
   }

   public static GoalXZ fromDirection(Vec3 vec, float f, double d) {
      float f2 = (float)Math.toRadians((double)f);
      double d2 = vec.xCoord - (double)MathHelper.sin(f2) * d;
      double d3 = vec.zCoord + (double)MathHelper.cos(f2) * d;
      return new GoalXZ(MathHelper.floor_double(d2), MathHelper.floor_double(d3));
   }

   public int getZ() {
      return this.z;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalXZ goalXZ = (GoalXZ)obj;
         return this.x == goalXZ.x && this.z == goalXZ.z;
      } else {
         return false;
      }
   }

   public GoalXZ(int n, int n2) {
      this.x = n;
      this.z = n2;
   }

   public int getX() {
      return this.x;
   }

   @Override
   public int hashCode() {
      int n = 1791873246;
      n = n * 222601791 + this.x;
      return n * -1331679453 + this.z;
   }

   @Override
   public String toString() {
      return String.format("GoalXZ{x=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.z));
   }

   public GoalXZ(BetterBlockPos betterBlockPos) {
      this.x = betterBlockPos.x2;
      this.z = betterBlockPos.z2;
   }

   public static double calculate(double d, double d2) {
      double d3 = Math.abs(d);
      double d4 = Math.abs(d2);
      double d5;
      double d6;
      if (d3 < d4) {
         d5 = d4 - d3;
         d6 = d3;
      } else {
         d5 = d3 - d4;
         d6 = d4;
      }

      d6 *= SQRT_2;
      return (d6 + d5) * (Double)BaritoneAPI.getSettings().costHeuristic.value;
   }
}
