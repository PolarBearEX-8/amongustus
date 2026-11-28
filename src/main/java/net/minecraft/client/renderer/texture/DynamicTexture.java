package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;

public class DynamicTexture extends AbstractTexture {
   private final int[] dynamicTextureData;
   private final int width;
   private final int height;

   public void updateDynamicTexture() {
      TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
   }

   public int[] getTextureData() {
      return this.dynamicTextureData;
   }

   @Override
   public void loadTexture(IResourceManager resourceManager) throws IOException {
   }

   public DynamicTexture(BufferedImage image) {
      this(image.getWidth(), image.getHeight());
      image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.dynamicTextureData, 0, image.getWidth());
      this.updateDynamicTexture();
   }

   public DynamicTexture(int n, int n2) {
      this.width = n;
      this.height = n2;
      this.dynamicTextureData = new int[n * n2];
      TextureUtil.allocateTexture(this.getGlTextureId(), n, n2);
   }
}
