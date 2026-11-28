package net.augustus.utils;

import com.google.common.base.Predicate;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;

public class EntityCollidablePredicate implements Predicate {
   public final EntityRenderer entityRenderer;

   public EntityCollidablePredicate(EntityRenderer entityRenderer2) {
      this.entityRenderer = entityRenderer2;
   }

   public boolean apply(Object obj) {
      return this.apply2((Entity)obj);
   }

   public boolean apply2(Entity entity) {
      return entity.canBeCollidedWith();
   }
}
