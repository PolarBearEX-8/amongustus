package net.minecraft.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.player.EntityPlayer;

public final class EntityFilterAdapter implements Predicate {
   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public boolean matchesEntity(Entity entity) {
      return entity instanceof EntityPlayer;
   }
}
