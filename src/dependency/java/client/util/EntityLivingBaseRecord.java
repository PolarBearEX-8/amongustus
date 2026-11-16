package client.util;

import net.minecraft.entity.EntityLivingBase;

public class EntityLivingBaseRecord {
   public EntityLivingBase entityLivingBase;
   public int count;
   public final SplineChecker splineChecker;

   public EntityLivingBaseRecord(SplineChecker splineChecker2, EntityLivingBase livingEntity, int count2) {
      this.splineChecker = splineChecker2;
      this.entityLivingBase = livingEntity;
      this.count = count2;
   }
}
