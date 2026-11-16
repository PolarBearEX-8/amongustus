package net.minecraft.entity;

import net.minecraft.util.MathHelper;

public class EntityRavagerAdapter {
   public EntityMob entityMob;
   public float factor;
   public float factor2;
   public boolean enabled;
   public double value2;
   public double value3;
   public double value;

   public double getValue() {
      return this.value;
   }

   public void applyDouble(double posX, double posY, double posZ, float factor3, float factor4) {
      this.value2 = posX;
      this.value3 = posY;
      this.value = posZ;
      this.factor = factor3;
      this.factor2 = factor4;
      this.enabled = true;
   }

   public void updateState() {
      this.entityMob.rotationPitch = 0.0F;
      if (this.enabled) {
         this.enabled = false;
         double offset = this.value2 - this.entityMob.posX;
         double offset2 = this.value3 - (this.entityMob.posY + (double)this.entityMob.getHeight2());
         double offset3 = this.value - this.entityMob.posZ;
         double offset4 = (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3);
         float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI) - 90.0F;
         float factor4 = (float)(-(MathHelper.getDouble6(offset2, offset4) * 180.0 / Math.PI));
         this.entityMob.rotationPitch = this.computeFactor(this.entityMob.rotationPitch, factor4, this.factor2);
         this.entityMob.scaleFactor12 = this.computeFactor(this.entityMob.scaleFactor12, factor3, this.factor);
      } else {
         this.entityMob.scaleFactor12 = this.computeFactor(this.entityMob.scaleFactor12, this.entityMob.scaleFactor8, 10.0F);
      }

      float factor5 = MathHelper.sqrt_float(this.entityMob.scaleFactor12 - this.entityMob.scaleFactor8);
      if (!this.entityMob.getPathfindManager().isActive2()) {
         if (factor5 < -75.0F) {
            this.entityMob.scaleFactor12 = this.entityMob.scaleFactor8 - 75.0F;
         }

         if (factor5 > 75.0F) {
            this.entityMob.scaleFactor12 = this.entityMob.scaleFactor8 + 75.0F;
         }
      }
   }

   public double getValue2() {
      return this.value2;
   }

   public void applyEntityByEntity(Entity entity, float factor3, float factor4) {
      this.value2 = entity.posX;
      if (entity instanceof EntityLivingBase) {
         this.value3 = entity.posY + (double)entity.getHeight2();
      } else {
         this.value3 = (entity.getBoundingBox2().minY + entity.getBoundingBox2().maxY) / 2.0;
      }

      this.value = entity.posZ;
      this.factor = factor3;
      this.factor2 = factor4;
      this.enabled = true;
   }

   private float computeFactor(float posX, float posY, float posZ) {
      float factor3 = MathHelper.sqrt_float(posY - posX);
      if (factor3 > posZ) {
         factor3 = posZ;
      }

      if (factor3 < -posZ) {
         factor3 = -posZ;
      }

      return posX + factor3;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public double getValue3() {
      return this.value3;
   }

   public EntityRavagerAdapter(EntityMob entityMob2) {
      this.entityMob = entityMob2;
   }
}
