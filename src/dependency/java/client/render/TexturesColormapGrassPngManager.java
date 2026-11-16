package client.render;

import client.util.DefaultIntArrayEntry;
import client.util.SpecialLoggerEntry;
import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class TexturesColormapGrassPngManager implements IResourceManagerReloadListener {
   public static final ResourceLocation resourceLocation;

   @Override
   public void applyIResourceManager(IResourceManager iResourceManager) {
      try {
         DefaultIntArrayEntry.setCountArray(SpecialLoggerEntry.resolveIndexArray(iResourceManager, resourceLocation));
      } catch (IOException ioException) {
      }
   }

   static {
      String text = "textures/colormap/grass.png";
      resourceLocation = new ResourceLocation(text);
   }
}
