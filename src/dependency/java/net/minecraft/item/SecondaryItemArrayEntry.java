package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;

public class SecondaryItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray = new BoxesManager[5];

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      for (int index = 0; index < 5; index++) {
         this.boxesManagerArray[index].applyFloat3(posZ);
      }
   }

   public SecondaryItemArrayEntry() {
      this.boxesManagerArray[0] = new BoxesManager(this, 0, 8);
      this.boxesManagerArray[1] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[2] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[3] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[4] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[0].applyFloatByFloat((float)(-24 / 2), (float)(-20 / 2 + 2), -3.0F, 24, 16, 4, 0.0F);
      this.boxesManagerArray[0].applyFloat(0.0F, (float)4, 0.0F);
      this.boxesManagerArray[1].applyFloatByFloat((float)(-24 / 2 + 2), (float)-7, -1.0F, 20, 6, 2, 0.0F);
      this.boxesManagerArray[1].applyFloat((float)(-24 / 2 + 1), (float)4, 0.0F);
      this.boxesManagerArray[2].applyFloatByFloat((float)(-24 / 2 + 2), (float)-7, -1.0F, 20, 6, 2, 0.0F);
      this.boxesManagerArray[2].applyFloat((float)(24 / 2 - 1), (float)4, 0.0F);
      this.boxesManagerArray[3].applyFloatByFloat((float)(-24 / 2 + 2), (float)-7, -1.0F, 20, 6, 2, 0.0F);
      this.boxesManagerArray[3].applyFloat(0.0F, (float)4, (float)(-20 / 2 + 1));
      this.boxesManagerArray[4].applyFloatByFloat((float)(-24 / 2 + 2), (float)-7, -1.0F, 20, 6, 2, 0.0F);
      this.boxesManagerArray[4].applyFloat(0.0F, (float)4, (float)(20 / 2 - 1));
      this.boxesManagerArray[0].factor = (float) (Math.PI / 2);
      this.boxesManagerArray[1].factor9 = (float) (Math.PI * 3.0 / 2.0);
      this.boxesManagerArray[2].factor9 = (float) (Math.PI / 2);
      this.boxesManagerArray[3].factor9 = (float) Math.PI;
   }
}
