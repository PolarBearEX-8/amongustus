package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.MathHelper;

public class ClientManagerBridge extends RotationPool {
   public EntityMob entityMob;
   public EntityLivingBase entityLivingBase;
   public float factor;

   @Override
   public boolean isActive() {
      this.entityLivingBase = this.entityMob.getEntityLivingBase4();
      if (this.entityLivingBase == null) {
         return false;
      } else {
         double coordinate = this.entityMob.getCoordinate(this.entityLivingBase);
         return !(coordinate >= 4.0) || !(coordinate <= 16.0) ? false : (!this.entityMob.onGround ? false : this.entityMob.getRandom().nextInt(5) == 0);
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.entityMob.onGround;
   }

   public ClientManagerBridge(EntityMob entityMob2, float factor2) {
      this.entityMob = entityMob2;
      this.factor = factor2;
      this.setCount(5);
   }

   @Override
   public void updateState3() {
      double offset = this.entityLivingBase.posX - this.entityMob.posX;
      double offset2 = this.entityLivingBase.posZ - this.entityMob.posZ;
      float factor2 = MathHelper.sqrt_double(offset * offset + offset2 * offset2);
      this.entityMob.motionX = this.entityMob.motionX + offset / (double)factor2 * 0.5 * 0.8F + this.entityMob.motionX * 0.2F;
      this.entityMob.motionZ = this.entityMob.motionZ + offset2 / (double)factor2 * 0.5 * 0.8F + this.entityMob.motionZ * 0.2F;
      this.entityMob.motionY = (double)this.factor;
   }
}
