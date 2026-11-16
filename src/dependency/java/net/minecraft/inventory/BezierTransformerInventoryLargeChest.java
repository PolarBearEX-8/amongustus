package net.minecraft.inventory;

import client.util.BezierTransformer;

public class BezierTransformerInventoryLargeChest extends InventoryLargeChest {
   public final BezierTransformer bezierTransformer;

   @Override
   public void processAction5() {
      super.processAction5();
      this.bezierTransformer.processAction(this);
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public BezierTransformerInventoryLargeChest(BezierTransformer bezierTransformer2, String text, boolean flag, int count) {
      super(text, flag, count);
      this.bezierTransformer = bezierTransformer2;
   }
}
