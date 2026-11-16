package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public final class VirtualPredicateAdapter implements Predicate {
   public boolean matchesEntityByEntity(Entity entity) {
      return entity.canExecute();
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
