package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;

public class DiamondOre extends PassiveFloatEntry {
   public BoxesManager boxesManager;
   public BoxesManager[] boxesManagerArray = new BoxesManager[8];

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      this.boxesManager.applyFloat3(posZ);

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      BoxesManager[] boxesManagerArray2 = this.boxesManagerArray;
      int length = boxesManagerArray2.length;

      for (int index = 0; index < length; index++) {
         boxesManagerArray2[index].factor = posX3;
      }
   }

   public DiamondOre() {
      this.boxesManager = new BoxesManager(this, 0, 0);
      this.boxesManager.resolveBoxesManager(-6.0F, -8.0F, -6.0F, 12, 16, 12);
      this.boxesManager.factor2 += (float)8;

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, 48, 0);
         double offset = (double)index * Math.PI * 2.0 / (double)this.boxesManagerArray.length;
         float factor = (float)Math.cos(offset) * 5.0F;
         float factor2 = (float)Math.sin(offset) * 5.0F;
         this.boxesManagerArray[index].resolveBoxesManager(-1.0F, 0.0F, -1.0F, 2, 18, 2);
         this.boxesManagerArray[index].factor5 = factor;
         this.boxesManagerArray[index].factor10 = factor2;
         this.boxesManagerArray[index].factor2 = (float)15;
         double offset2 = (double)index * Math.PI * -2.0 / (double)this.boxesManagerArray.length + (Math.PI / 2);
         this.boxesManagerArray[index].factor9 = (float)offset2;
      }
   }
}
