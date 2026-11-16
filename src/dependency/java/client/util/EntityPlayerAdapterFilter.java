package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.player.EntityPlayerAdapter;

public class EntityPlayerAdapterFilter implements Predicate {
   public final EntityPlayerAdapter entityPlayerAdapter;
   public final DuplicateStatIdManager duplicateStatIdManager;
   public final RayTraceMonitor rayTraceMonitor;

   public EntityPlayerAdapterFilter(RayTraceMonitor rayTraceMonitor2, EntityPlayerAdapter entityPlayerAdapter2, DuplicateStatIdManager duplicateStatIdManager2) {
      this.rayTraceMonitor = rayTraceMonitor2;
      this.entityPlayerAdapter = entityPlayerAdapter2;
      this.duplicateStatIdManager = duplicateStatIdManager2;
   }

   public boolean matchesAuxiliaryDuplicateStatIdMa(AuxiliaryDuplicateStatIdManagerAdapter auxiliaryDuplicateStatIdManagerAdapter) {
      return this.entityPlayerAdapter.getStatFileWriterHandler().matchesAuxiliaryDuplicateStatIdMaByAuxiliaryDuplica(auxiliaryDuplicateStatIdManagerAdapter)
         && auxiliaryDuplicateStatIdManagerAdapter != this.duplicateStatIdManager;
   }

   public boolean apply(Object entry) {
      return this.matchesAuxiliaryDuplicateStatIdMa((AuxiliaryDuplicateStatIdManagerAdapter)entry);
   }
}
