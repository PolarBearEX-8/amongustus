package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public class CompositePredicateAdapter implements Predicate {
   public final AuxiliaryPredicateEntry auxiliaryPredicateEntry;

   public boolean matchesEntityByEntity(Entity entity) {
      return entity.canExecute() && this.auxiliaryPredicateEntry.auxiliaryUUIDEntry.getCanSeeManager().matchesEntityByEntity(entity);
   }

   public CompositePredicateAdapter(AuxiliaryPredicateEntry auxiliaryPredicateEntry2) {
      this.auxiliaryPredicateEntry = auxiliaryPredicateEntry2;
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
