package net.minecraft.entity;

import net.minecraft.util.MathHelper;

public class AuxiliaryEntityLivingBaseEntry {
   public EntityLivingBase entityLivingBase;
   public int count;
   public float factor;

   public void updateState() {
      double offset = this.entityLivingBase.posX - this.entityLivingBase.prevPosX;
      double offset2 = this.entityLivingBase.posZ - this.entityLivingBase.prevPosZ;
      if (offset * offset + offset2 * offset2 > 2.5000003E-7F) {
         this.entityLivingBase.scaleFactor8 = this.entityLivingBase.rotationYaw;
         this.entityLivingBase.scaleFactor12 = this.computeFactor(this.entityLivingBase.scaleFactor8, this.entityLivingBase.scaleFactor12, 75.0F);
         this.factor = this.entityLivingBase.scaleFactor12;
         this.count = 0;
      } else {
         float factor2 = 75.0F;
         if (Math.abs(this.entityLivingBase.scaleFactor12 - this.factor) > 15.0F) {
            this.count = 0;
            this.factor = this.entityLivingBase.scaleFactor12;
         } else {
            this.count++;
            if (this.count > 10) {
               factor2 = Math.max(1.0F - (float)(this.count - 10) / 10.0F, 0.0F) * 75.0F;
            }
         }

         this.entityLivingBase.scaleFactor8 = this.computeFactor(this.entityLivingBase.scaleFactor12, this.entityLivingBase.scaleFactor8, factor2);
      }
   }

   private float computeFactor(float posX, float posY, float posZ) {
      float factor2 = MathHelper.sqrt_float(posX - posY);
      if (factor2 < -posZ) {
         factor2 = -posZ;
      }

      if (factor2 >= posZ) {
         factor2 = posZ;
      }

      return posX - factor2;
   }

   public AuxiliaryEntityLivingBaseEntry(EntityLivingBase livingEntity) {
      this.entityLivingBase = livingEntity;
   }
}
