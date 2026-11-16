package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class TertiaryItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray = new BoxesManager[12];
   public BoxesManager boxesManager;

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
      float factor = posX3 * (float) Math.PI * -0.1F;

      for (int index = 0; index < 4; index++) {
         this.boxesManagerArray[index].factor2 = -2.0F + MathHelper.cos(((float)(index * 2) + posX3) * 0.25F);
         this.boxesManagerArray[index].factor5 = MathHelper.cos(factor) * 9.0F;
         this.boxesManagerArray[index].factor10 = MathHelper.abs(factor) * 9.0F;
         factor++;
      }

      float factor2 = (float) (Math.PI / 4) + posX3 * (float) Math.PI * 0.03F;

      for (int index2 = 4; index2 < 8; index2++) {
         this.boxesManagerArray[index2].factor2 = 2.0F + MathHelper.cos(((float)(index2 * 2) + posX3) * 0.25F);
         this.boxesManagerArray[index2].factor5 = MathHelper.cos(factor2) * 7.0F;
         this.boxesManagerArray[index2].factor10 = MathHelper.abs(factor2) * 7.0F;
         factor2++;
      }

      float factor3 = 0.47123894F + posX3 * (float) Math.PI * -0.05F;

      for (int index3 = 8; index3 < 12; index3++) {
         this.boxesManagerArray[index3].factor2 = 11.0F + MathHelper.cos(((float)index3 * 1.5F + posX3) * 0.5F);
         this.boxesManagerArray[index3].factor5 = MathHelper.cos(factor3) * 5.0F;
         this.boxesManagerArray[index3].factor10 = MathHelper.abs(factor3) * 5.0F;
         factor3++;
      }

      this.boxesManager.factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManager.factor = posY / (180.0F / (float)Math.PI);
   }

   public TertiaryItemArrayEntry() {
      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, 0, 16);
         this.boxesManagerArray[index].resolveBoxesManager(0.0F, 0.0F, 0.0F, 2, 8, 2);
      }

      this.boxesManager = new BoxesManager(this, 0, 0);
      this.boxesManager.resolveBoxesManager(-4.0F, -4.0F, -4.0F, 8, 8, 8);
   }
}
