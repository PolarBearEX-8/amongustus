package cc.baritone.utils;

public class HeuristicTracker implements SecondaryListener {
   public static final double value = Math.sqrt(2.0) / 2.0;

   @Override
   public boolean equals(Object other) {
      return other.getClass() == HeuristicTracker.class;
   }

   @Override
   public double computeValue(int count, int count2, int count3) {
      int count4 = Math.abs(count);
      int count5 = Math.abs(count3);
      int count6 = Math.min(count4, count5);
      int count7 = Math.max(count4, count5) - count6;
      return Math.min((double)count4, Math.min((double)count5, (double)count7 * value)) * (Double)SettingsTxtManager.getSettings().costHeuristic.dataRef
         + BreadthFirstInspector.computeValue((Integer)SettingsTxtManager.getSettings().axisHeight.dataRef, count2);
   }

   @Override
   public String toString() {
      return "GoalAxis";
   }

   @Override
   public int hashCode() {
      return 201385781;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return count2 == (Integer)SettingsTxtManager.getSettings().axisHeight.dataRef && (count == 0 || count3 == 0 || Math.abs(count) == Math.abs(count3));
   }
}
