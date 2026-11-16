package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class DiagonalEvaluator implements SecondaryListener {
   public static final double value = Math.sqrt(2.0);
   public final int count;
   public final int count2;

   @Override
   public int hashCode() {
      return (1204478626 + this.count) * -1331679453 + this.count2;
   }

   public int getCount2() {
      return this.count2;
   }

   public static DiagonalEvaluator resolveDiagonalEvaluator(Vec3 vec, float factor, double offset) {
      float factor2 = (float)Math.toRadians((double)factor);
      double offset2 = vec.xCoord - (double)MathHelper.abs(factor2) * offset;
      double offset3 = vec.zCoord + (double)MathHelper.cos(factor2) * offset;
      return new DiagonalEvaluator(MathHelper.ceiling_double_int(offset2), MathHelper.ceiling_double_int(offset3));
   }

   @Override
   public double computeValue(int count3, int count4, int count5) {
      int count6 = count3 - this.count;
      int count7 = count5 - this.count2;
      return computeValue((double)count6, (double)count7);
   }

   public DiagonalEvaluator(BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos) {
      this.count = betterBlockPosXSYSZSBlockPos.count9;
      this.count2 = betterBlockPosXSYSZSBlockPos.count10;
   }

   public DiagonalEvaluator(int count3, int count4) {
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public String toString() {
      return String.format("GoalXZ{x=%s,z=%s}", SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count2));
   }

   public static double computeValue(double offset, double offset2) {
      double offset3 = Math.abs(offset);
      double offset4 = Math.abs(offset2);
      double offset5;
      if (offset3 < offset4) {
         offset5 = offset4 - offset3;
      } else {
         offset5 = offset3 - offset4;
         offset3 = offset4;
      }

      return (offset3 * value + offset5) * (Double)SettingsTxtManager.getSettings().costHeuristic.dataRef;
   }

   @Override
   public boolean checkIndex(int count3, int count4, int count5) {
      return count3 == this.count && count5 == this.count2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DiagonalEvaluator diagonalEvaluator = (DiagonalEvaluator)other;
         return this.count == diagonalEvaluator.count && this.count2 == diagonalEvaluator.count2;
      } else {
         return false;
      }
   }

   public int getCount() {
      return this.count;
   }
}
