package net.augustus.cosmetics.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public interface CosmeticImageProcessor {
   CosmeticImageProcessor IDENTITY = img -> img;
   CosmeticImageProcessor CLOAK_NORMALIZER = CosmeticImageProcessor::normalizeCloakImage;

   BufferedImage processImage(BufferedImage image);

   static BufferedImage normalizeCloakImage(BufferedImage image) {
      if (image.getWidth() / 2 == image.getHeight()) {
         return image;
      }
      int targetWidth = 64;
      int targetHeight = 32;
      int width = image.getWidth();
      int height = image.getHeight();
      while (targetWidth < width || targetHeight < height) {
         targetWidth *= 2;
         targetHeight *= 2;
      }

      BufferedImage normalized = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics graphics = normalized.getGraphics();
      graphics.drawImage(image, 0, 0, null);
      graphics.dispose();
      return normalized;
   }
}
