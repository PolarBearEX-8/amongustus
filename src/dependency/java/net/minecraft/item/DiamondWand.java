package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class DiamondWand extends PassiveFloatEntry {
   public BoxesManager boxesManager;
   public BoxesManager[] boxesManagerArray = new BoxesManager[9];

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 0.6F, 0.0F);
      this.boxesManager.applyFloat3(posZ);
      BoxesManager[] boxesManagerArray2 = this.boxesManagerArray;
      int length = boxesManagerArray2.length;

      for (int index = 0; index < length; index++) {
         boxesManagerArray2[index].applyFloat3(posZ);
      }

      GlStateManager.popMatrix();
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].factor = 0.2F * MathHelper.abs(posX3 * 0.3F + (float)index) + 0.4F;
      }
   }

   public DiamondWand() {
      this.boxesManager = new BoxesManager(this, 0, 0);
      this.boxesManager.resolveBoxesManager(-8.0F, -8.0F, -8.0F, 16, 16, 16);
      this.boxesManager.factor2 += (float)8;
      Random random = new Random(1660L);

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index] = new BoxesManager(this, 0, 0);
         float factor = (((float)(index % 3) - (float)(index / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
         float factor2 = ((float)(index / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
         int count = random.nextInt(7) + 8;
         this.boxesManagerArray[index].resolveBoxesManager(-1.0F, 0.0F, -1.0F, 2, count, 2);
         this.boxesManagerArray[index].factor5 = factor;
         this.boxesManagerArray[index].factor10 = factor2;
         this.boxesManagerArray[index].factor2 = (float)15;
      }
   }
}
