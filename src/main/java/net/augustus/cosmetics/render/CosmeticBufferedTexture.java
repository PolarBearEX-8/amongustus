package net.augustus.cosmetics.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class CosmeticBufferedTexture extends SimpleTexture {
   private boolean uploaded;
   private BufferedImage bufferedImage;
   private int width;
   private int height;
   private float aspectRatio;

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public int getHeight() {
      return this.height;
   }

   public float getAspectRatio() {
      return this.aspectRatio;
   }

   public BufferedImage getBufferedImage() {
      return this.bufferedImage;
   }

   @Override
   public int getGlTextureId() {
      int glTextureId2 = super.getGlTextureId();
      if (!this.uploaded && this.bufferedImage != null) {
         this.uploaded = true;
         TextureUtil.uploadTextureImage(glTextureId2, this.bufferedImage);
      }

      return glTextureId2;
   }

   public void setAspectRatio(float f) {
      this.aspectRatio = f;
   }

   public boolean isUploaded() {
      return this.uploaded;
   }

   public int getWidth() {
      return this.width;
   }

   public void setBufferedImage(BufferedImage image) {
      this.bufferedImage = image;
      this.width = image.getWidth();
      this.height = image.getHeight();
      this.aspectRatio = (float)this.width / (float)this.height;
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
   }

   public void releaseTexture() {
      this.deleteGlTexture();
   }

   @Override
   public void deleteGlTexture() {
      super.deleteGlTexture();
      this.bufferedImage = null;
   }

   public CosmeticBufferedTexture(ResourceLocation location, BufferedImage image) {
       super(location, true);
      if (image != null) {
         this.setBufferedImage(image);
      }
   }



}
