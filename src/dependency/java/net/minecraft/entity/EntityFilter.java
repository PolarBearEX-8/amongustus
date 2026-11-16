package net.minecraft.entity;

import com.google.common.base.Predicate;

public final class EntityFilter implements Predicate {
   public final String labelText;
   public final boolean enabled;

   public EntityFilter(String text, boolean flag) {
      this.labelText = text;
      this.enabled = flag;
   }

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public boolean matchesEntity(Entity entity) {
      return entity.getText().equals(this.labelText) != this.enabled;
   }
}
