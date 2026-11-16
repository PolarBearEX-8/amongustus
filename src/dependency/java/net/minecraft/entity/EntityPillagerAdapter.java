package net.minecraft.entity;

import net.minecraft.util.MathHelper;

public class EntityPillagerAdapter {
   public EntityMob entityMob;
   public double value4;
   public double value2;
   public double value3;
   public double value;
   public boolean enabled;

   public double getValue2() {
      return this.value2;
   }

   public float computeFactor(float posX, float posY, float posZ) {
      float factor = MathHelper.sqrt_float(posY - posX);
      if (factor > posZ) {
         factor = posZ;
      }

      if (factor < -posZ) {
         factor = -posZ;
      }

      float factor2 = posX + factor;
      if (factor2 < 0.0F) {
         factor2 += 360.0F;
      } else if (factor2 > 360.0F) {
         factor2 -= 360.0F;
      }

      return factor2;
   }

   public double getValue4() {
      return this.value4;
   }

   public double getValue3() {
      return this.value3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public double getValue() {
      return this.value;
   }

   public void updateState() {
      this.entityMob.setFactor30(0.0F);
      if (this.enabled) {
         this.enabled = false;
         int count = MathHelper.ceiling_double_int(this.entityMob.getBoundingBox2().minY + 0.5);
         double offset = this.value4 - this.entityMob.posX;
         double offset2 = this.value3 - this.entityMob.posZ;
         double offset3 = this.value2 - (double)count;
         if (offset * offset + offset3 * offset3 + offset2 * offset2 >= 2.5000003E-7F) {
            float factor = (float)(MathHelper.getDouble6(offset2, offset) * 180.0 / Math.PI) - 90.0F;
            this.entityMob.rotationYaw = this.computeFactor(this.entityMob.rotationYaw, factor, 30.0F);
            this.entityMob.setScaleFactor6((float)(this.value * this.entityMob.getAuxiliaryListener(SharedMonsterAttributes.extendedListener3).getDouble()));
            if (offset3 > 0.0 && offset * offset + offset2 * offset2 < 1.0) {
               this.entityMob.getPrimaryItemEntry().updateState();
            }
         }
      }
   }

   public void applyDouble(double posX, double posX2, double posY, double posZ) {
      this.value4 = posX;
      this.value2 = posX2;
      this.value3 = posY;
      this.value = posZ;
      this.enabled = true;
   }

   public EntityPillagerAdapter(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      this.value4 = entityMob2.posX;
      this.value2 = entityMob2.posY;
      this.value3 = entityMob2.posZ;
   }
}
