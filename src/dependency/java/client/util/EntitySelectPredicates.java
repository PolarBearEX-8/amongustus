package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRenderer;

public class EntitySelectPredicates implements Predicate {
   public final EntityRenderer entityRenderer;

   public boolean matchesEntityByEntity(Entity entity) {
      return entity.isActive5();
   }

   public EntitySelectPredicates(EntityRenderer entityRenderer2) {
      this.entityRenderer = entityRenderer2;
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
