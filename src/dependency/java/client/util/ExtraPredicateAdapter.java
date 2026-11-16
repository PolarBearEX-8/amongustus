package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;

public final class ExtraPredicateAdapter implements Predicate {
   public boolean matchesEntityByEntity(Entity entity) {
      return entity instanceof IInventory && entity.canExecute();
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
