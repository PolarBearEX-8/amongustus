package cc.baritone.utils;

public final class BreadthFirstEngineDirectListener extends AbstractDirectListener implements BreadthFirstEngine {
   public final BreadthFirstEngine breadthFirstEngine;

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return !this.breadthFirstEngine.checkIndex(count, count2, count3);
   }

   public BreadthFirstEngineDirectListener(BreadthFirstEngine breadthFirstEngine2) {
      super(breadthFirstEngine2.getCount(), breadthFirstEngine2.getCount2(), breadthFirstEngine2.getCount3());
      this.breadthFirstEngine = breadthFirstEngine2;
   }
}
