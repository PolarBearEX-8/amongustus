package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public final class CorePredicateAdapter implements Predicate {
   public boolean matchesEntityByEntity(Entity entity) {
      return !(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isActive23();
   }

   public boolean apply(Object entry) {
      return this.matchesEntityByEntity((Entity)entry);
   }
}
