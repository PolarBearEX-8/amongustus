package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LayeredTexture extends AbstractTexture {
   private static final Logger LOGGER = LogManager.getLogger();
   public final List layeredTextureNames;
   private ResourceLocation textureLocation;

   public LayeredTexture(String[] strings) {
      this.layeredTextureNames = Lists.newArrayList(strings);
      if (strings.length > 0 && strings[0] != null) {
         this.textureLocation = new ResourceLocation(strings[0]);
      }
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
      this.deleteGlTexture();
      BufferedImage image = null;

      try {
         for (String str : (Iterable<String>)(this.layeredTextureNames)) {
            if (str != null) {
               BufferedImage image2 = TextureUtil.readBufferedImage(resourceManager.getResource(new ResourceLocation(str)).getInputStream());
               if (image == null) {
                  image = new BufferedImage(image2.getWidth(), image2.getHeight(), 2);
               }

               image.getGraphics().drawImage(image2, 0, 0, (ImageObserver)null);
            }
         }
      } catch (IOException e) {
         LOGGER.error("Couldn't load layered image", e);
         return;
      }

      if (Config.isShaderPackLoaded()) {
         ShadersTex.loadSimpleTexture(this.getGlTextureId(), image, false, false, resourceManager, this.textureLocation, this.getMultiTexID());
      } else {
         TextureUtil.uploadTextureImage(this.getGlTextureId(), image);
      }
   }
}
