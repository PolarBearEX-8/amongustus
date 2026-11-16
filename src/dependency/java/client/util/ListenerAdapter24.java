package client.util;

import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class ListenerAdapter24 implements IResourceManagerReloadListener {
   public static final ResourceLocation resourceLocation;

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      try {
         CoreIntArrayEntry.setCountArray(SpecialLoggerEntry.resolveIndexArray(iResourceManager, resourceLocation));
      } catch (IOException ioException) {
      }
   }

   static {
      String text = "textures/colormap/foliage.png";
      resourceLocation = new ResourceLocation(text);
   }
}
