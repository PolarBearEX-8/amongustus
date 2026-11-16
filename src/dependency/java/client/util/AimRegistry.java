package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityGuardian;
import net.minecraft.entity.player.EntityPlayerAdapter;

public class AimRegistry implements Predicate {
   public final EntityGuardian entityGuardian;

   public boolean matchesEntityPlayerAdapter(EntityPlayerAdapter entityPlayerAdapter) {
      return this.entityGuardian.getCoordinate(entityPlayerAdapter) < 2500.0 && entityPlayerAdapter.internalWorldEntry.isActive();
   }

   public boolean apply(Object entry) {
      return this.matchesEntityPlayerAdapter((EntityPlayerAdapter)entry);
   }

   public AimRegistry(EntityGuardian entityGuardian2) {
      this.entityGuardian = entityGuardian2;
   }
}
