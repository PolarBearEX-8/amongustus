package client.util;

import net.minecraft.item.DiamondRing;

public class AimTracker extends DiamondRing {
   public AimTracker() {
      super(12, 0.0F);
      this.boxesManager6 = new BoxesManager(this, 0, 0);
      this.boxesManager6.applyFloatByFloat(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
      this.boxesManager6.applyFloat(0.0F, 4.0F, -8.0F);
      this.boxesManager6.resolveBoxesManagerByIndex(22, 0).applyFloatByFloat(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.boxesManager6.resolveBoxesManagerByIndex(22, 0).applyFloatByFloat(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.boxesManager3 = new BoxesManager(this, 18, 4);
      this.boxesManager3.applyFloatByFloat(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
      this.boxesManager3.applyFloat(0.0F, 5.0F, 2.0F);
      this.boxesManager3.resolveBoxesManagerByIndex(52, 0).resolveBoxesManager(-2.0F, 2.0F, -8.0F, 4, 6, 1);
      this.boxesManager5.factor5--;
      this.boxesManager2.factor5++;
      this.boxesManager5.factor10 += 0.0F;
      this.boxesManager2.factor10 += 0.0F;
      this.boxesManager.factor5--;
      this.boxesManager4.factor5++;
      this.boxesManager.factor10--;
      this.boxesManager4.factor10--;
      this.factor3 += 2.0F;
   }
}
