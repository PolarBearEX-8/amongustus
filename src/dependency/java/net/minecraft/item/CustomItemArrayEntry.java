package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.util.MathHelper;

public class CustomItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray;
   public BoxesManager[] boxesManagerArray2;

   @Override
   public void applyEntity(Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ, boolean flag) {
      this.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
      BoxesManager[] boxesManagerArray3 = this.boxesManagerArray2;
      int length = boxesManagerArray3.length;

      for (int index = 0; index < length; index++) {
         boxesManagerArray3[index].applyFloat3(posZ);
      }

      BoxesManager[] boxesManagerArray4 = this.boxesManagerArray;
      int length2 = boxesManagerArray4.length;

      for (int index2 = 0; index2 < length2; index2++) {
         boxesManagerArray4[index2].applyFloat3(posZ);
      }
   }

   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      float factor = MathHelper.cos(posX3 * 0.1F);
      this.boxesManagerArray[1].factor = (0.065F + 0.05F * factor) * (float) Math.PI;
      this.boxesManagerArray[2]
         .applyFloat(-2.0F, 6.9F + MathHelper.cos(this.boxesManagerArray[1].factor) * 10.0F, -0.5F + MathHelper.abs(this.boxesManagerArray[1].factor) * 10.0F);
      this.boxesManagerArray[2].factor = (0.265F + 0.1F * factor) * (float) Math.PI;
      this.boxesManagerArray2[0].factor9 = posX4 / (180.0F / (float)Math.PI);
      this.boxesManagerArray2[0].factor = posY / (180.0F / (float)Math.PI);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      EntityWitherboss entityWitherboss = (EntityWitherboss)livingEntity;

      for (int index = 1; index < 3; index++) {
         this.boxesManagerArray2[index].factor9 = (entityWitherboss.computeFactor(index - 1) - livingEntity.scaleFactor8) / (180.0F / (float)Math.PI);
         this.boxesManagerArray2[index].factor = entityWitherboss.computeFactorByIndex(index - 1) / (180.0F / (float)Math.PI);
      }
   }

   public CustomItemArrayEntry(float factor) {
      this.count2 = 64;
      this.count = 64;
      this.boxesManagerArray = new BoxesManager[3];
      this.boxesManagerArray[0] = new BoxesManager(this, 0, 16);
      this.boxesManagerArray[0].applyFloatByFloat(-10.0F, 3.9F, -0.5F, 20, 3, 3, factor);
      this.boxesManagerArray[1] = new BoxesManager(this).resolveBoxesManager(this.count2, this.count);
      this.boxesManagerArray[1].applyFloat(-2.0F, 6.9F, -0.5F);
      this.boxesManagerArray[1].resolveBoxesManagerByIndex(0, 22).applyFloatByFloat(0.0F, 0.0F, 0.0F, 3, 10, 3, factor);
      this.boxesManagerArray[1].resolveBoxesManagerByIndex(24, 22).applyFloatByFloat(-4.0F, 1.5F, 0.5F, 11, 2, 2, factor);
      this.boxesManagerArray[1].resolveBoxesManagerByIndex(24, 22).applyFloatByFloat(-4.0F, 4.0F, 0.5F, 11, 2, 2, factor);
      this.boxesManagerArray[1].resolveBoxesManagerByIndex(24, 22).applyFloatByFloat(-4.0F, 6.5F, 0.5F, 11, 2, 2, factor);
      this.boxesManagerArray[2] = new BoxesManager(this, 12, 22);
      this.boxesManagerArray[2].applyFloatByFloat(0.0F, 0.0F, 0.0F, 3, 6, 3, factor);
      this.boxesManagerArray2 = new BoxesManager[3];
      this.boxesManagerArray2[0] = new BoxesManager(this, 0, 0);
      this.boxesManagerArray2[0].applyFloatByFloat(-4.0F, -4.0F, -4.0F, 8, 8, 8, factor);
      this.boxesManagerArray2[1] = new BoxesManager(this, 32, 0);
      this.boxesManagerArray2[1].applyFloatByFloat(-4.0F, -4.0F, -4.0F, 6, 6, 6, factor);
      this.boxesManagerArray2[1].factor5 = -8.0F;
      this.boxesManagerArray2[1].factor2 = 4.0F;
      this.boxesManagerArray2[2] = new BoxesManager(this, 32, 0);
      this.boxesManagerArray2[2].applyFloatByFloat(-4.0F, -4.0F, -4.0F, 6, 6, 6, factor);
      this.boxesManagerArray2[2].factor5 = 10.0F;
      this.boxesManagerArray2[2].factor2 = 4.0F;
   }
}
