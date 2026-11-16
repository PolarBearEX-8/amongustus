package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class EnhancedPredicateAdapter implements Predicate {
   public final BaseLoggerEntry baseLoggerEntry;

   public EnhancedPredicateAdapter(BaseLoggerEntry baseLoggerEntry2) {
      this.baseLoggerEntry = baseLoggerEntry2;
   }

   public boolean matchesEntity(Entity entity) {
      if (!(entity instanceof EntityPlayer)) {
         return false;
      } else if (((EntityPlayer)entity).invulnerableManager.enabled) {
         return false;
      } else {
         double value = this.baseLoggerEntry.getValue();
         if (entity.isSprinting()) {
            value *= 0.8F;
         }

         if (entity.isActive12()) {
            float factor = ((EntityPlayer)entity).getFactor3();
            if (factor < 0.1F) {
               factor = 0.1F;
            }

            value *= (double)(0.7F * factor);
         }

         return (double)entity.getDistanceSqToEntity(BaseLoggerEntry.resolveEntityMob(this.baseLoggerEntry)) > value
            ? false
            : PrimaryClientManagerAdapter.matchesEntityMob(BaseLoggerEntry.resolveEntityMob(this.baseLoggerEntry), (EntityLivingBase)entity, false, true);
      }
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }
}
