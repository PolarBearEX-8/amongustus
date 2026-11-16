package client.util;

public class InternalFloatEntryAdapter extends PassiveFloatEntry {
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager3;
   public BoxesManager boxesManager;

   public void updateState() {
      this.boxesManager2.factor2 = -32.0F;
      this.boxesManager2.applyFloat3(0.0625F);
      this.boxesManager3.applyFloat3(0.0625F);
      this.boxesManager.applyFloat3(0.0625F);
   }

   public InternalFloatEntryAdapter() {
      this.count2 = 64;
      this.count = 64;
      this.boxesManager2 = new BoxesManager(this, 0, 0);
      this.boxesManager2.applyFloatByFloat(-10.0F, 0.0F, -2.0F, 20, 40, 1, 0.0F);
      this.boxesManager3 = new BoxesManager(this, 44, 0);
      this.boxesManager3.applyFloatByFloat(-1.0F, -30.0F, -1.0F, 2, 42, 2, 0.0F);
      this.boxesManager = new BoxesManager(this, 0, 42);
      this.boxesManager.applyFloatByFloat(-10.0F, -32.0F, -1.0F, 20, 2, 2, 0.0F);
   }
}
