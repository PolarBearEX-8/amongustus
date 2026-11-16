package client.util;

import net.minecraft.entity.EntityGhast;
import net.minecraft.entity.EntityPillagerAdapter;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class BezierSynchronizer extends EntityPillagerAdapter {
   public EntityGhast entityGhast;
   public int count;

   public BezierSynchronizer(EntityGhast entityGhast2) {
      super(entityGhast2);
      this.entityGhast = entityGhast2;
   }

   private boolean checkDouble(double posX, double posX2, double posY, double posZ) {
      double offset = (posX - this.entityGhast.posX) / posZ;
      double offset2 = (posX2 - this.entityGhast.posY) / posZ;
      double offset3 = (posY - this.entityGhast.posZ) / posZ;
      AxisAlignedBB boundingBox = this.entityGhast.getBoundingBox2();

      for (int index = 1; (double)index < posZ; index++) {
         boundingBox = boundingBox.expand(offset, offset2, offset3);
         if (!this.entityGhast.worldObj.getList2(this.entityGhast, boundingBox).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void updateState() {
      if (this.enabled) {
         double offset = this.value4 - this.entityGhast.posX;
         double offset2 = this.value2 - this.entityGhast.posY;
         double offset3 = this.value3 - this.entityGhast.posZ;
         double offset4 = offset * offset + offset2 * offset2 + offset3 * offset3;
         if (this.count-- <= 0) {
            this.count = this.count + this.entityGhast.getRandom().nextInt(5) + 2;
            double offset5 = (double)MathHelper.sqrt_double(offset4);
            if (this.checkDouble(this.value4, this.value2, this.value3, offset5)) {
               this.entityGhast.motionX += offset / offset5 * 0.1;
               this.entityGhast.motionY += offset2 / offset5 * 0.1;
               this.entityGhast.motionZ += offset3 / offset5 * 0.1;
            } else {
               this.enabled = false;
            }
         }
      }
   }
}
