package client.util;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerAdapter;

public class RotationAccumulator implements Runnable {
   public final IntegratedServer integratedServer;

   public RotationAccumulator(IntegratedServer integratedServer2) {
      this.integratedServer = integratedServer2;
   }

   @Override
   public void run() {
      for (EntityPlayerAdapter entityPlayerAdapter : (Iterable<EntityPlayerAdapter>)(Iterable)Lists.newArrayList(this.integratedServer.getMovingManager().getList())) {
         this.integratedServer.getMovingManager().applyEntityPlayerAdapterByEntityPlayerAdap(entityPlayerAdapter);
      }
   }
}
