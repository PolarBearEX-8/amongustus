package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;

public final class SupplementalPredicateAdapter implements Predicate {
   public boolean matchesEntityByEntity(Entity entity) {
      return entity instanceof EntityHorse && ((EntityHorse)entity).isActive38();
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
