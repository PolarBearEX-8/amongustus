package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class StaticPredicateAdapter implements Predicate {
   public boolean matchesEntityByEntity(Entity entity) {
      return entity.canExecute() && entity.entity == null && entity.ridingEntity == null;
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
