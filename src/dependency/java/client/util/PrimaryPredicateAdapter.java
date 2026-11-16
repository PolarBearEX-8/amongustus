package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class PrimaryPredicateAdapter implements Predicate {
   public final EntityLivingBase entityLivingBase;

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public PrimaryPredicateAdapter(EntityLivingBase livingEntity) {
      this.entityLivingBase = livingEntity;
   }

   public boolean matchesEntity(Entity entity) {
      return entity.isActive9();
   }
}
