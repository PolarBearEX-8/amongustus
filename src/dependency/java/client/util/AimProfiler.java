package client.util;

import net.minecraft.item.DiamondRing;

public class AimProfiler extends DiamondRing {
   public AimProfiler() {
      this(0.0F);
   }

   public AimProfiler(float factor) {
      super(6, factor);
      this.boxesManager6.resolveBoxesManagerByIndex(16, 16).applyFloatByFloat(-2.0F, 0.0F, -9.0F, 4, 3, 1, factor);
      this.factor2 = 4.0F;
   }
}
