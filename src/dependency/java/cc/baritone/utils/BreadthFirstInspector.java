package cc.baritone.utils;

import cc.SettingsUtil;

public class BreadthFirstInspector implements SecondaryListener, ServiceEntry {
   public final int count;

   public static double computeValue(int count2, int count3) {
      if (count3 > count2) {
         return valueArray[2] / 2.0 * (double)(count3 - count2);
      } else {
         return count3 < count2 ? (double)(count2 - count3) * value14 : 0.0;
      }
   }

   @Override
   public String toString() {
      return String.format("GoalYLevel{y=%s}", SettingsUtil.maybeCensor(this.count));
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         BreadthFirstInspector breadthFirstInspector = (BreadthFirstInspector)other;
         return this.count == breadthFirstInspector.count;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.count * 1271009915;
   }

   @Override
   public double computeValue(int count2, int count3, int count4) {
      return computeValue(this.count, count3);
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4) {
      return count3 == this.count;
   }

   public BreadthFirstInspector(int count2) {
      this.count = count2;
   }
}
