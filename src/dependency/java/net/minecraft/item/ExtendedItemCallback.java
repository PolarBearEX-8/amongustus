package net.minecraft.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public interface ExtendedItemCallback {
   ExtendedItemCallback extendedItemCallback = ExtendedItemCallback::resolveBufferedImageByBufferedImage;
   ExtendedItemCallback extendedItemCallback2 = ExtendedItemCallback::resolveBufferedImage2;

   BufferedImage resolveBufferedImage(BufferedImage bufferedImage);

   static BufferedImage resolveBufferedImageByBufferedImage(BufferedImage bufferedImage) {
      if (bufferedImage.getWidth() / 2 == bufferedImage.getHeight()) {
         return bufferedImage;
      } else {
         int imageWidth = 64;
         int imageHeight = 32;
         int width = bufferedImage.getWidth();

         for (int height = bufferedImage.getHeight(); imageWidth < width || imageHeight < height; imageHeight *= 2) {
            imageWidth *= 2;
         }

         BufferedImage bufferedImage2 = new BufferedImage(imageWidth, imageHeight, 2);
         Graphics graphics = bufferedImage2.getGraphics();
         graphics.drawImage(bufferedImage, 0, 0, (ImageObserver)null);
         graphics.dispose();
         return bufferedImage2;
      }
   }

   static BufferedImage resolveBufferedImage2(BufferedImage bufferedImage) {
      return bufferedImage;
   }
}
