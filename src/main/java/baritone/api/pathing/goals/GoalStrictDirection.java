package baritone.api.pathing.goals;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class GoalStrictDirection implements Goal {
   public final int x;
   public final int y;
   public final int z;
   public final int dx;
   public final int dz;

   @Override
   public double heuristic() {
      return Double.NEGATIVE_INFINITY;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      int n4 = (n - this.x) * this.dx + (n3 - this.z) * this.dz;
      int n5 = Math.abs((n - this.x) * this.dz) + Math.abs((n3 - this.z) * this.dx);
      int n6 = Math.abs(n2 - this.y);
      double d = (double)(-n4 * 100);
      d += (double)(n5 * 1000);
      return d + (double)(n6 * 1000);
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return false;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalStrictDirection{x=%s, y=%s, z=%s, dx=%s, dz=%s}",
         SettingsUtil.maybeCensor(this.x),
         SettingsUtil.maybeCensor(this.y),
         SettingsUtil.maybeCensor(this.z),
         SettingsUtil.maybeCensor(this.dx),
         SettingsUtil.maybeCensor(this.dz)
      );
   }

   public GoalStrictDirection(BlockPos pos, EnumFacing facing) {
      this.x = pos.getX();
      this.y = pos.getY();
      this.z = pos.getZ();
      this.dx = facing.getFrontOffsetX();
      this.dz = facing.getFrontOffsetZ();
      if (this.dx == 0 && this.dz == 0) {
         throw new IllegalArgumentException(facing + "");
      }
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalStrictDirection goalStrictDirection = (GoalStrictDirection)obj;
         return this.x == goalStrictDirection.x && this.y == goalStrictDirection.y && this.z == goalStrictDirection.z && this.dx == goalStrictDirection.dx && this.dz == goalStrictDirection.dz;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int n = (int)BetterBlockPos.longHash(this.x, this.y, this.z);
      n = n * 630627507 + this.dx;
      return n * -283028380 + this.dz;
   }
}
