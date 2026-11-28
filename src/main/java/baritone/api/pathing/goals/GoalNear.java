package baritone.api.pathing.goals;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import baritone.api.utils.interfaces.IGoalRenderPos;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import net.minecraft.util.BlockPos;

public class GoalNear implements IGoalRenderPos, Goal {
   private final int x;
   private final int y;
   private final int z;
   private final int rangeSq;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalNear goalNear = (GoalNear)obj;
         return this.x == goalNear.x && this.y == goalNear.y && this.z == goalNear.z && this.rangeSq == goalNear.rangeSq;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return String.format(
         "GoalNear{x=%s, y=%s, z=%s, rangeSq=%d}",
         SettingsUtil.maybeCensor(this.x),
         SettingsUtil.maybeCensor(this.y),
         SettingsUtil.maybeCensor(this.z),
         this.rangeSq
      );
   }

   @Override
   public BlockPos getGoalPos() {
      return new BlockPos(this.x, this.y, this.z);
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return n4 * n4 + n5 * n5 + n6 * n6 <= this.rangeSq;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return GoalBlock.calculate((double)(n - this.x), n2 - this.y, (double)(n3 - this.z));
   }

   @Override
   public double heuristic() {
      int n = (int)Math.ceil(Math.sqrt((double)this.rangeSq));
      DoubleOpenHashSet doubleOpenHashSet = new DoubleOpenHashSet();
      double positiveinfinity = Double.POSITIVE_INFINITY;

      for (int i = -n; i <= n; i++) {
         for (int j = -n; j <= n; j++) {
            for (int k = -n; k <= n; k++) {
               double d = this.heuristic(this.x + i, this.y + j, this.z + k);
               if (d < positiveinfinity && this.isInGoal(this.x + i, this.y + j, this.z + k)) {
                  doubleOpenHashSet.add(d);
               } else {
                  positiveinfinity = Math.min(positiveinfinity, d);
               }
            }
         }
      }

      double negativeinfinity = Double.NEGATIVE_INFINITY;
      DoubleIterator it = doubleOpenHashSet.iterator();

      while (it.hasNext()) {
         double d2 = it.nextDouble();
         if (d2 < positiveinfinity) {
            negativeinfinity = Math.max(negativeinfinity, d2);
         }
      }

      return negativeinfinity;
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPos.longHash(this.x, this.y, this.z) + this.rangeSq;
   }

   public GoalNear(BlockPos pos, int n) {
      this.x = pos.getX();
      this.y = pos.getY();
      this.z = pos.getZ();
      this.rangeSq = n * n;
   }
}
