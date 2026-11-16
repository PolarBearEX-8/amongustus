package client.util;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;

public class FovEstimator implements Callable {
   public final Entity entity;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public FovEstimator(Entity entity2) {
      this.entity = entity2;
   }

   public String getText() throws Exception {
      return SkippingEntityWithIdManager.resolveTextByEntity(this.entity) + " (" + this.entity.getClass().getCanonicalName() + ")";
   }
}
