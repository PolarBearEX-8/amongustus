package client.util;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;

public class InterpolationAccumulator implements Callable {
   public final Entity entity;

   public InterpolationAccumulator(Entity entity2) {
      this.entity = entity2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public String getText() throws Exception {
      return this.entity.entity.toString();
   }
}
