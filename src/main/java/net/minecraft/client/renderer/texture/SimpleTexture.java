package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.util.ResourceLocation;
import net.optifine.EmissiveTextures;
import net.optifine.config.Config;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleTexture extends AbstractTexture {
   private static final Logger LOGGER = LogManager.getLogger();
   public final ResourceLocation textureLocation;
   public ResourceLocation locationEmissive;
   public boolean isEmissive;
   public final boolean useResourceManager;

   public SimpleTexture(ResourceLocation location, boolean flag) {
      this.textureLocation = location;
      this.useResourceManager = flag;
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
      this.deleteGlTexture();
      InputStream in = null;

      try {
         IResource resource = this.useResourceManager ? resourceManager.getResource(this.textureLocation) : null;
         in = this.useResourceManager
            ? resource.getInputStream()
            : this.getClass().getResourceAsStream("/assets/" + this.textureLocation.getResourceDomain() + "/" + this.textureLocation.getResourcePath());
         BufferedImage image = TextureUtil.readBufferedImage(in);
         if (image != null) {
            boolean flag = false;
            boolean flag2 = false;
            if (resource != null && resource.hasMetadata()) {
               try {
                  TextureMetadataSection metadata = (TextureMetadataSection)resource.getMetadata("texture");
                  if (metadata != null) {
                     flag = metadata.getTextureBlur();
                     flag2 = metadata.getTextureClamp();
                  }
               } catch (RuntimeException e) {
                  LOGGER.warn("Failed reading metadata of: " + this.textureLocation, e);
               }
            }

            if (Config.isShaderPackLoaded()) {
               ShadersTex.loadSimpleTexture(this.getGlTextureId(), image, flag, flag2, resourceManager, this.textureLocation, this.getMultiTexID());
            } else {
               TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), image, flag, flag2);
            }

            if (EmissiveTextures.isActive()) {
               EmissiveTextures.loadTexture(this.textureLocation, this);
            }

            return;
         }

         System.err.println("Failed to load texture: " + this.textureLocation);
         if (in != null) {
            in.close();
         }
      } finally {
         if (in != null) {
            in.close();
         }
      }
   }
}
