package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;

public class EntityRendererScreenNameCallable implements Callable {
   public final EntityRenderer entityRenderer;

   public String getScreenName() throws Exception {
      return Minecraft.getMinecraft().currentScreen.getClass().getCanonicalName();
   }

   @Override
   public Object call() throws Exception {
      return this.getScreenName();
   }

   public EntityRendererScreenNameCallable(EntityRenderer entityRenderer2) {
      this.entityRenderer = entityRenderer2;
   }
}
