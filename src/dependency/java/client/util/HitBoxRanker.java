package client.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityPigzombie;

public class HitBoxRanker extends ClassArrayEntry {
   @Override
   public void applyAuxiliaryUUIDEntry(AuxiliaryUUIDEntry auxiliaryUUIDEntry, EntityLivingBase livingEntity) {
      super.applyAuxiliaryUUIDEntry(auxiliaryUUIDEntry, livingEntity);
      if (auxiliaryUUIDEntry instanceof EntityPigzombie) {
         EntityPigzombie.applyEntityPigzombie((EntityPigzombie)auxiliaryUUIDEntry, livingEntity);
      }
   }

   public HitBoxRanker(EntityPigzombie entityPigzombie) {
      super(entityPigzombie, true, new Class[0]);
   }
}
