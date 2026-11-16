package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityEndermite;

public class NativePredicateAdapter implements Predicate {
   public final EntityEnderman entityEnderman;

   public NativePredicateAdapter(EntityEnderman entityEnderman2) {
      this.entityEnderman = entityEnderman2;
   }

   public boolean apply(Object entry) {
      return this.matchesEntityEndermite((EntityEndermite)entry);
   }

   public boolean matchesEntityEndermite(EntityEndermite entityEndermite) {
      return entityEndermite.isEnabled28();
   }
}
