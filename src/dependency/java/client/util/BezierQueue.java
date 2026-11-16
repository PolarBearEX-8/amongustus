package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityRenderer;

public class BezierQueue implements Callable {
   public final EntityRenderer entityRenderer;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public BezierQueue(EntityRenderer entityRenderer2) {
      this.entityRenderer = entityRenderer2;
   }

   public String getText() throws Exception {
      return Minecraft.getMinecraft().currentScreen.getClass().getCanonicalName();
   }
}
