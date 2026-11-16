package cc.baritone.utils;

public final class HeuristicPolicy extends DiagonalEvaluator {
   @Override
   public double computeValue(int count, int count2, int count3) {
      return super.computeValue(count, count2, count3) + BreadthFirstInspector.computeValue((Integer)BaritoneManager.getSettings().exploreMaintainY.dataRef, count2);
   }

   public HeuristicPolicy(int count, int count2) {
      super(count, count2);
   }
}
