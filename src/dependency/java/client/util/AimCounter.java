package client.util;

import net.minecraft.item.DiamondCrown;

public class AimCounter extends DiamondCrown {
   public AimCounter() {
      this.boxesManager = new BoxesManager(this, 0, 0).resolveBoxesManager(128, 64);
      this.boxesManager.applyFloatByFloat(0.0F, -5.0F, -14.0F, 30, 5, 14, 0.0F);
      this.boxesManager.factor5 = 1.0F;
      this.boxesManager.factor2 = 7.0F;
      this.boxesManager.factor10 = 15.0F;
      this.boxesManager3 = new BoxesManager(this, 0, 0).resolveBoxesManager(128, 64);
      this.boxesManager3.applyFloatByFloat(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
      this.boxesManager3.factor5 = 16.0F;
      this.boxesManager3.factor2 = 7.0F;
      this.boxesManager3.factor10 = 15.0F;
      this.boxesManager2 = new BoxesManager(this, 0, 19).resolveBoxesManager(128, 64);
      this.boxesManager2.applyFloatByFloat(0.0F, 0.0F, 0.0F, 30, 10, 14, 0.0F);
      this.boxesManager2.factor5 = 1.0F;
      this.boxesManager2.factor2 = 6.0F;
      this.boxesManager2.factor10 = 1.0F;
   }
}
