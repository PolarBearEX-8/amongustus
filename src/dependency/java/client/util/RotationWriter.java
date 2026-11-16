package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ResourceLanguageManager;

public class RotationWriter implements Runnable {
   public final Minecraft mc;
   public final GeometrySystem geometrySystem;
   public final ResourceLanguageManager resourceLanguageManager;

   public RotationWriter(ResourceLanguageManager resourceLanguageManager2, Minecraft mc2, GeometrySystem geometrySystem2) {
      this.resourceLanguageManager = resourceLanguageManager2;
      this.mc = mc2;
      this.geometrySystem = geometrySystem2;
   }

   @Override
   public void run() {
      GeometrySystem geometrySystem2 = this.geometrySystem;
      this.mc.displayGuiScreen(geometrySystem2);
   }
}
