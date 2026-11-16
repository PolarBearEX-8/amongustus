package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;

public class DiamondShard extends PassiveFloatEntry {
   public BoxesManager boxesManager = new BoxesManager(this, 0, 0);
   public BoxesManager boxesManager2;

   public void updateState() {
      this.boxesManager.applyFloat3(0.0625F);
      this.boxesManager2.applyFloat3(0.0625F);
   }

   public DiamondShard() {
      this.boxesManager.applyFloatByFloat(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
      this.boxesManager2 = new BoxesManager(this, 0, 14);
      this.boxesManager2.applyFloatByFloat(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
   }
}
