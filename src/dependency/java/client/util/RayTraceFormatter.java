package client.util;

import java.util.concurrent.Callable;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityRenderer;

public class RayTraceFormatter implements Callable {
   public final ScaledResolution scaledResolution;
   public final EntityRenderer entityRenderer;

   @Override
   public Object call() throws Exception {
      return this.getText();
   }

   public RayTraceFormatter(EntityRenderer entityRenderer2, ScaledResolution scaledResolution2) {
      this.entityRenderer = entityRenderer2;
      this.scaledResolution = scaledResolution2;
   }

   public String getText() throws Exception {
      return String.format(
         "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d",
         this.scaledResolution.getScaledWidth(),
         this.scaledResolution.getScaledHeight(),
         EntityRenderer.resolveMinecraftByEntityRenderer(this.entityRenderer).displayWidth,
         EntityRenderer.resolveMinecraftByEntityRenderer(this.entityRenderer).displayHeight,
         this.scaledResolution.getScaleFactor()
      );
   }
}
