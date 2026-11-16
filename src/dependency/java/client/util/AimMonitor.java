package client.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySkeleton;

public class AimMonitor extends InternalFloatEntryBridge {
   @Override
   public void applyEntity(float posX, float posX2, float posX3, float posX4, float posY, float posZ, Entity entity) {
      super.applyEntity(posX, posX2, posX3, posX4, posY, posZ, entity);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.enabled6 = ((EntitySkeleton)livingEntity).getCount9() == 1;
      super.applyEntityLivingBase(livingEntity, posX, posY, posZ);
   }

   public AimMonitor(float factor, boolean flag) {
      super(factor, 0.0F, 64, 32);
      if (!flag) {
         this.boxesManager2 = new BoxesManager(this, 40, 16);
         this.boxesManager2.applyFloatByFloat(-1.0F, -2.0F, -1.0F, 2, 12, 2, factor);
         this.boxesManager2.applyFloat(-5.0F, 2.0F, 0.0F);
         this.boxesManager = new BoxesManager(this, 40, 16);
         this.boxesManager.enabled = true;
         this.boxesManager.applyFloatByFloat(-1.0F, -2.0F, -1.0F, 2, 12, 2, factor);
         this.boxesManager.applyFloat(5.0F, 2.0F, 0.0F);
         this.boxesManager5 = new BoxesManager(this, 0, 16);
         this.boxesManager5.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 12, 2, factor);
         this.boxesManager5.applyFloat(-2.0F, 12.0F, 0.0F);
         this.boxesManager6 = new BoxesManager(this, 0, 16);
         this.boxesManager6.enabled = true;
         this.boxesManager6.applyFloatByFloat(-1.0F, 0.0F, -1.0F, 2, 12, 2, factor);
         this.boxesManager6.applyFloat(2.0F, 12.0F, 0.0F);
      }
   }

   public AimMonitor() {
      this(0.0F, false);
   }
}
