package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class DefaultPredicateAdapter implements Predicate {
   public final Predicate predicate;
   public final ExtendedClassEntry extendedClassEntry;

   public DefaultPredicateAdapter(ExtendedClassEntry extendedClassEntry2, Predicate predicate2) {
      this.extendedClassEntry = extendedClassEntry2;
      this.predicate = predicate2;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      if (this.predicate != null && !this.predicate.apply(livingEntity)) {
         return false;
      } else {
         if (livingEntity instanceof EntityPlayer) {
            double value = this.extendedClassEntry.getValue();
            if (livingEntity.isSprinting()) {
               value *= 0.8F;
            }

            if (livingEntity.isActive12()) {
               float factor = ((EntityPlayer)livingEntity).getFactor3();
               if (factor < 0.1F) {
                  factor = 0.1F;
               }

               value *= (double)(0.7F * factor);
            }

            if ((double)livingEntity.getDistanceSqToEntity(this.extendedClassEntry.auxiliaryUUIDEntry) > value) {
               return false;
            }
         }

         return this.extendedClassEntry.matchesEntityLivingBase(livingEntity, false);
      }
   }

   public boolean apply(Object entry) {
      return this.matchesEntityLivingBase((EntityLivingBase)entry);
   }
}
