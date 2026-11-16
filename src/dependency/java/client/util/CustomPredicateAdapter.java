package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;

public class CustomPredicateAdapter implements Predicate {
   public final AdvancedLoggerEntry advancedLoggerEntry;

   public CustomPredicateAdapter(AdvancedLoggerEntry advancedLoggerEntry2) {
      this.advancedLoggerEntry = advancedLoggerEntry2;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      double value = this.advancedLoggerEntry.getValue();
      if (livingEntity.isSprinting()) {
         value *= 0.8F;
      }

      return livingEntity.isActive12()
         ? false
         : (
            (double)livingEntity.getDistanceSqToEntity(AdvancedLoggerEntry.resolveEntityMob(this.advancedLoggerEntry)) > value
               ? false
               : PrimaryClientManagerAdapter.matchesEntityMob(AdvancedLoggerEntry.resolveEntityMob(this.advancedLoggerEntry), livingEntity, false, true)
         );
   }

   public boolean apply(Object entry) {
      return this.matchesEntityLivingBase((EntityLivingBase)entry);
   }
}
