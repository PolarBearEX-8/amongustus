package net.minecraft.item;

import client.util.BoxesManager;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLavaslime;
import net.minecraft.entity.EntityLivingBase;

public class AdvancedItemArrayEntry extends PassiveFloatEntry {
   public BoxesManager[] boxesManagerArray = new BoxesManager[8];
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
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      EntityLavaslime entityLavaslime = (EntityLavaslime)livingEntity;
      float factor = entityLavaslime.factor40 + (entityLavaslime.factor44 - entityLavaslime.factor40) * posZ;
      if (factor < 0.0F) {
         factor = 0.0F;
      }

      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         this.boxesManagerArray[index].factor2 = (float)(-(4 - index)) * factor * 1.7F;
      }
   }

   public AdvancedItemArrayEntry() {
      for (int index = 0; index < this.boxesManagerArray.length; index++) {
         byte byteValue = 0;
         int count = index;
         if (index == 2) {
            byteValue = 24;
            count = 10;
         } else if (index == 3) {
            byteValue = 24;
            count = 19;
         }

         this.boxesManagerArray[index] = new BoxesManager(this, byteValue, count);
         this.boxesManagerArray[index].resolveBoxesManager(-4.0F, (float)(16 + index), -4.0F, 8, 1, 8);
      }

      this.boxesManager = new BoxesManager(this, 0, 16);
      this.boxesManager.resolveBoxesManager(-2.0F, 18.0F, -2.0F, 4, 4, 4);
   }
}
