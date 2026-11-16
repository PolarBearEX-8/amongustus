package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityRabbit;

public class RotationEstimator extends AuxiliaryWorldEntry {
   @Override
   public double computeValue(EntityLivingBase livingEntity) {
      return (double)(4.0F + livingEntity.width);
   }

   public RotationEstimator(EntityRabbit entityRabbit) {
      super(entityRabbit, EntityLivingBase.class, 1.4, true);
   }
}
