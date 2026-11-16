package client.util;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;

public class SplineRanker implements Callable {
   public final Entity entity;

   public String getText() throws Exception {
      return this.entity.getText();
   }

   public SplineRanker(Entity entity2) {
      this.entity = entity2;
   }

   @Override
   public Object call() throws Exception {
      return this.getText();
   }
}
