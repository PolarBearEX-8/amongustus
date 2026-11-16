package cc.baritone.utils;

import client.util.IBlockState;

public interface BreadthFirstEngine extends DirectListener {
   default BreadthFirstEngine resolveBreadthFirstEngineByBreadthFirstEngi(BreadthFirstEngine breadthFirstEngine) {
      return new RuntimeComponentManager(this, breadthFirstEngine, SharedCustom.OR);
   }

   default BreadthFirstEngine resolveBreadthFirstEngine2(BreadthFirstEngine breadthFirstEngine) {
      return new RuntimeComponentManager(this, breadthFirstEngine, SharedCustom.AND);
   }

   default BreadthFirstEngine resolveBreadthFirstEngine(BreadthFirstEngine breadthFirstEngine) {
      return new RuntimeComponentManager(this, breadthFirstEngine, SharedCustom.XOR);
   }

   @Override
   default DirectListener getDirectListener() {
      return this.getBreadthFirstEngineAsBreadthFirstEngi();
   }

   boolean checkIndex(int count, int count2, int count3);

   @Override
   default boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState) {
      return this.checkIndex(count, count2, count3);
   }

   default BreadthFirstEngine getBreadthFirstEngine() {
      return new RuntimeComponentProvider(this);
   }

   default BreadthFirstEngine getBreadthFirstEngineAsBreadthFirstEngi() {
      return new BreadthFirstEngineDirectListener(this);
   }
}
