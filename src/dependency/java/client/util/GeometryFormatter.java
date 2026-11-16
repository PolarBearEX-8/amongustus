package client.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.WorldServer;

public class GeometryFormatter implements Predicate {
   public final WorldServer worldServer;

   public GeometryFormatter(WorldServer worldServer2) {
      this.worldServer = worldServer2;
   }

   public boolean matchesEntityLivingBase(EntityLivingBase livingEntity) {
      return livingEntity != null && livingEntity.canExecute() && this.worldServer.checkIndex2(livingEntity.getBlockPos().getX(), livingEntity.getBlockPos().getY(), livingEntity.getBlockPos().getZ());
   }

   public boolean apply(Object entry) {
      return this.matchesEntityLivingBase((EntityLivingBase)entry);
   }
}
