package client.util;

public class FovAccumulator {
   public int count = 0;
   public BoxesManager boxesManager = null;

   public BoxesManager getBoxesManager() {
      return this.boxesManager;
   }

   public FovAccumulator(int count2, BoxesManager boxesManager2) {
      this.count = count2;
      this.boxesManager = boxesManager2;
   }

   public void applyFloatEntryRegistry(FloatEntryRegistry floatEntryRegistry, float factor) {
      BoxesManager boxesManager2 = DimensionEntry.resolveBoxesManager(floatEntryRegistry, this.count);
      if (boxesManager2 != null) {
         boxesManager2.applyFloatByFloat(factor);
      }

      this.boxesManager.applyFloat3(factor);
   }
}
