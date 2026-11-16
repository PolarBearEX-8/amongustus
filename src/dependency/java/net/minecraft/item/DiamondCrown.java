package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;

public class DiamondCrown extends PassiveFloatEntry {
   public BoxesManager boxesManager = new BoxesManager(this, 0, 0).resolveBoxesManager(64, 64);
   public BoxesManager boxesManager2;
   public BoxesManager boxesManager3;

   public void updateState() {
      this.boxesManager3.factor = this.boxesManager.factor;
      this.boxesManager.applyFloat3(0.0625F);
      this.boxesManager3.applyFloat3(0.0625F);
      this.boxesManager2.applyFloat3(0.0625F);
   }

   public DiamondCrown() {
      this.boxesManager.applyFloatByFloat(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
      this.boxesManager.factor5 = 1.0F;
      this.boxesManager.factor2 = 7.0F;
      this.boxesManager.factor10 = 15.0F;
      this.boxesManager3 = new BoxesManager(this, 0, 0).resolveBoxesManager(64, 64);
      this.boxesManager3.applyFloatByFloat(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
      this.boxesManager3.factor5 = 8.0F;
      this.boxesManager3.factor2 = 7.0F;
      this.boxesManager3.factor10 = 15.0F;
      this.boxesManager2 = new BoxesManager(this, 0, 19).resolveBoxesManager(64, 64);
      this.boxesManager2.applyFloatByFloat(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
      this.boxesManager2.factor5 = 1.0F;
      this.boxesManager2.factor2 = 6.0F;
      this.boxesManager2.factor10 = 1.0F;
   }
}
