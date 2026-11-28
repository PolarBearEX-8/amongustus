package baritone.api.pathing.goals;

import baritone.api.utils.SettingsUtil;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.util.BlockPos;

public class GoalRunAway implements Goal {
   private final BlockPos[] from;
   private final int distanceSq;
   private final Integer maintainY;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalRunAway goalRunAway = (GoalRunAway)obj;
         return this.distanceSq == goalRunAway.distanceSq && Arrays.equals((Object[])this.from, (Object[])goalRunAway.from) && Objects.equals(this.maintainY, goalRunAway.maintainY);
      } else {
         return false;
      }
   }

   public GoalRunAway(double d, BlockPos[] posArr) {
      this(d, null, posArr);
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      double maxvalue = Double.MAX_VALUE;

      for (BlockPos pos : this.from) {
         double d = GoalXZ.calculate((double)(pos.getX() - n), (double)(pos.getZ() - n3));
         if (d < maxvalue) {
            maxvalue = d;
         }
      }

      maxvalue = -maxvalue;
      if (this.maintainY != null) {
         maxvalue = maxvalue * 0.6 + GoalYLevel.calculate(this.maintainY, n2) * 1.5;
      }

      return maxvalue;
   }

   @Override
   public String toString() {
      return this.maintainY != null
         ? String.format("GoalRunAwayFromMaintainY y=%s, %s", SettingsUtil.maybeCensor(this.maintainY), Arrays.asList(this.from))
         : "GoalRunAwayFrom" + Arrays.<BlockPos>asList(this.from);
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      if (this.maintainY != null && this.maintainY != n2) {
         return false;
      } else {
         for (BlockPos pos : this.from) {
            int n4 = n - pos.getX();
            int n5 = n3 - pos.getZ();
            if ((n4 * n4 + n5 * n5) < this.distanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public int hashCode() {
      int n = Arrays.hashCode((Object[])this.from);
      n = n * 1196803141 + this.distanceSq;
      return n * -2053788840 + this.maintainY;
   }

   @Override
   public double heuristic() {
      int n = (int)Math.ceil(Math.sqrt((double)this.distanceSq));
      int maxvalue = Integer.MAX_VALUE;
      int maxvalue2 = Integer.MAX_VALUE;
      int maxvalue3 = Integer.MAX_VALUE;
      int minvalue = Integer.MIN_VALUE;
      int minvalue2 = Integer.MIN_VALUE;
      int minvalue3 = Integer.MIN_VALUE;

      for (BlockPos pos : this.from) {
         maxvalue = Math.min(maxvalue, pos.getX() - n);
         maxvalue2 = Math.min(maxvalue2, pos.getY() - n);
         maxvalue3 = Math.min(maxvalue3, pos.getZ() - n);
         minvalue = Math.max(maxvalue, pos.getX() + n);
         minvalue2 = Math.max(maxvalue2, pos.getY() + n);
         minvalue3 = Math.max(maxvalue3, pos.getZ() + n);
      }

      DoubleOpenHashSet doubleOpenHashSet = new DoubleOpenHashSet();
      double positiveinfinity = Double.POSITIVE_INFINITY;

      for (int i = maxvalue; i <= minvalue; i++) {
         for (int j = maxvalue2; j <= minvalue2; j++) {
            for (int k = maxvalue3; k <= minvalue3; k++) {
               double d = this.heuristic(i, j, k);
               if (d < positiveinfinity && this.isInGoal(i, j, k)) {
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

   public GoalRunAway(double d, Integer num, BlockPos[] posArr) {
      if (posArr.length == 0) {
         throw new IllegalArgumentException();
      } else {
         this.from = posArr;
         this.distanceSq = (int)(d * d);
         this.maintainY = num;
      }
   }
}
