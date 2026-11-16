package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;

public class StandardItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray = new BoxesManager[7];

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.boxesManagerArray[5].factor2 = 4.0F - posX3;

      for (int index = 0; index < 6; index++) {
         this.boxesManagerArray[index].applyFloat3(posZ);
      }
   }

   public StandardItemArrayEntry() {
      this.boxesManagerArray[0] = new BoxesManager(this, 0, 10);
      this.boxesManagerArray[1] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[2] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[3] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[4] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray[5] = new BoxesManager(this, 44, 10);
      this.boxesManagerArray[0].applyFloatByFloat((float)(-20 / 2), (float)(-16 / 2), -1.0F, 20, 16, 2, 0.0F);
      this.boxesManagerArray[0].applyFloat(0.0F, (float)4, 0.0F);
      this.boxesManagerArray[5].applyFloatByFloat((float)(-20 / 2 + 1), (float)(-16 / 2 + 1), -1.0F, 18, 14, 1, 0.0F);
      this.boxesManagerArray[5].applyFloat(0.0F, (float)4, 0.0F);
      this.boxesManagerArray[1].applyFloatByFloat((float)(-20 / 2 + 2), (float)-9, -1.0F, 16, 8, 2, 0.0F);
      this.boxesManagerArray[1].applyFloat((float)(-20 / 2 + 1), (float)4, 0.0F);
      this.boxesManagerArray[2].applyFloatByFloat((float)(-20 / 2 + 2), (float)-9, -1.0F, 16, 8, 2, 0.0F);
      this.boxesManagerArray[2].applyFloat((float)(20 / 2 - 1), (float)4, 0.0F);
      this.boxesManagerArray[3].applyFloatByFloat((float)(-20 / 2 + 2), (float)-9, -1.0F, 16, 8, 2, 0.0F);
      this.boxesManagerArray[3].applyFloat(0.0F, (float)4, (float)(-16 / 2 + 1));
      this.boxesManagerArray[4].applyFloatByFloat((float)(-20 / 2 + 2), (float)-9, -1.0F, 16, 8, 2, 0.0F);
      this.boxesManagerArray[4].applyFloat(0.0F, (float)4, (float)(16 / 2 - 1));
      this.boxesManagerArray[0].factor = (float) (Math.PI / 2);
      this.boxesManagerArray[1].factor9 = (float) (Math.PI * 3.0 / 2.0);
      this.boxesManagerArray[2].factor9 = (float) (Math.PI / 2);
      this.boxesManagerArray[3].factor9 = (float) Math.PI;
      this.boxesManagerArray[5].factor = (float) (-Math.PI / 2);
   }
}
