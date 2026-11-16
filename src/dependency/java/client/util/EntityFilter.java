package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAdapter;

public final class EntityFilter implements Predicate {
   public final int count;

   public boolean apply(Object entry) {
      return this.matchesEntity((Entity)entry);
   }

   public EntityFilter(int count2) {
      this.count = count2;
   }

   public boolean matchesEntity(Entity entity) {
      return !(entity instanceof EntityPlayerAdapter)
         ? false
         : ((EntityPlayerAdapter)entity).internalWorldEntry.getWorldSettingsGameType().getIndex() == this.count;
   }
}
