package client.util;

import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class HitBoxSorter extends RotationPool {
   public EntityGhast entityGhast;

   public HitBoxSorter(EntityGhast entityGhast2) {
      this.entityGhast = entityGhast2;
      this.setCount(2);
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public void updateState2() {
      if (this.entityGhast.getEntityLivingBase4() == null) {
         this.entityGhast.scaleFactor8 = this.entityGhast.rotationYaw = -((float)MathHelper.getDouble6(this.entityGhast.motionX, this.entityGhast.motionZ))
            * 180.0F
            / (float) Math.PI;
      } else {
         EntityLivingBase entityLivingBase = this.entityGhast.getEntityLivingBase4();
         double offset = 64.0;
         if (entityLivingBase.getCoordinate(this.entityGhast) < offset * offset) {
            double offset2 = entityLivingBase.posX - this.entityGhast.posX;
            double offset3 = entityLivingBase.posZ - this.entityGhast.posZ;
            this.entityGhast.scaleFactor8 = this.entityGhast.rotationYaw = -((float)MathHelper.getDouble6(offset2, offset3)) * 180.0F / (float) Math.PI;
         }
      }
   }
}
