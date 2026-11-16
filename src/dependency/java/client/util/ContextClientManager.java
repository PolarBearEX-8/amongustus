package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySpider;

public class ContextClientManager extends AuxiliaryWorldEntry {
   @Override
   public double computeValue(EntityLivingBase livingEntity) {
      return (double)(4.0F + livingEntity.width);
   }

   @Override
   public boolean isActiveAsFlag() {
      if (this.auxiliaryUUIDEntry.computeFactor(1.0F) >= 0.5F && this.auxiliaryUUIDEntry.getRandom().nextInt(100) == 0) {
         this.auxiliaryUUIDEntry.setEntityLivingBase((EntityLivingBase)null);
         return false;
      } else {
         return super.isActiveAsFlag();
      }
   }

   public ContextClientManager(EntitySpider entitySpider, Class clazz) {
      super(entitySpider, clazz, 1.0, true);
   }
}
