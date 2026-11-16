package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PredicateFilter implements Predicate {
   public final Predicate predicate;
   public final AuxiliaryUUIDEntry auxiliaryUUIDEntry;
   public final HitBoxDirector hitBoxDirector;

   public boolean apply(Object entry) {
      return this.matchesEntityLivingBase((EntityLivingBase)entry);
   }

   public PredicateFilter(HitBoxDirector hitBoxDirector2, Predicate predicate2, AuxiliaryUUIDEntry auxiliaryUUIDEntry2) {
      this.hitBoxDirector = hitBoxDirector2;
      this.predicate = predicate2;
      this.auxiliaryUUIDEntry = auxiliaryUUIDEntry2;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      if (this.predicate != null && !this.predicate.apply(livingEntity)) {
         return false;
      } else if (livingEntity instanceof EntityCreeper) {
         return false;
      } else {
         if (livingEntity instanceof EntityPlayer) {
            double offset = HitBoxDirector.computeValue(this.hitBoxDirector);
            if (livingEntity.isSprinting()) {
               offset *= 0.8F;
            }

            if (livingEntity.isActive12()) {
               float factor = ((EntityPlayer)livingEntity).getFactor3();
               if (factor < 0.1F) {
                  factor = 0.1F;
               }

               offset *= (double)(0.7F * factor);
            }

            if ((double)livingEntity.getDistanceSqToEntity(this.auxiliaryUUIDEntry) > offset) {
               return false;
            }
         }

         return HitBoxDirector.matchesHitBoxDirector(this.hitBoxDirector, livingEntity, false);
      }
   }
}
