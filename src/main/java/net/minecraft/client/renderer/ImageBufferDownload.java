package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class ImageBufferDownload implements IImageBuffer {
   private int[] imageData;
   private int imageWidth;
   private int imageHeight;

   private void setAreaOpaque(int n, int n2, int n3, int n4) {
      for (int i = n; i < n3; i++) {
         for (int j = n2; j < n4; j++) {
            this.imageData[i + j * this.imageWidth] = this.imageData[i + j * this.imageWidth] | 0xFF000000;
         }
      }
   }

   private boolean hasTransparency(int n, int n2, int n3, int n4) {
      for (int i = n; i < n3; i++) {
         for (int j = n2; j < n4; j++) {
            if ((this.imageData[i + j * this.imageWidth] >> 24 & 0xFF) < 128) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public BufferedImage parseUserSkin(BufferedImage image) {
      if (image == null) {
         return null;
      } else {
         this.imageWidth = 64;
         this.imageHeight = 64;
         int width = image.getWidth();
         int height = image.getHeight();

         byte b;
         for (b = 1; this.imageWidth < width || this.imageHeight < height; b *= 2) {
            this.imageWidth *= 2;
            this.imageHeight *= 2;
         }

         BufferedImage image2 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
         Graphics graphics = image2.getGraphics();
         graphics.drawImage(image, 0, 0, (ImageObserver)null);
         if (image.getHeight() == 32 * b) {
            graphics.drawImage(image2, 24 * b, 48 * b, 20 * b, 52 * b, 4 * b, 16 * b, 8 * b, 20 * b, (ImageObserver)null);
            graphics.drawImage(image2, 28 * b, 48 * b, 24 * b, 52 * b, 8 * b, 16 * b, 12 * b, 20 * b, (ImageObserver)null);
            graphics.drawImage(image2, 20 * b, 52 * b, 16 * b, 64 * b, 8 * b, 20 * b, 12 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 24 * b, 52 * b, 20 * b, 64 * b, 4 * b, 20 * b, 8 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 28 * b, 52 * b, 24 * b, 64 * b, 0 * b, 20 * b, 4 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 32 * b, 52 * b, 28 * b, 64 * b, 12 * b, 20 * b, 16 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 40 * b, 48 * b, 36 * b, 52 * b, 44 * b, 16 * b, 48 * b, 20 * b, (ImageObserver)null);
            graphics.drawImage(image2, 44 * b, 48 * b, 40 * b, 52 * b, 48 * b, 16 * b, 52 * b, 20 * b, (ImageObserver)null);
            graphics.drawImage(image2, 36 * b, 52 * b, 32 * b, 64 * b, 48 * b, 20 * b, 52 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 40 * b, 52 * b, 36 * b, 64 * b, 44 * b, 20 * b, 48 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 44 * b, 52 * b, 40 * b, 64 * b, 40 * b, 20 * b, 44 * b, 32 * b, (ImageObserver)null);
            graphics.drawImage(image2, 48 * b, 52 * b, 44 * b, 64 * b, 52 * b, 20 * b, 56 * b, 32 * b, (ImageObserver)null);
         }

         graphics.dispose();
         this.imageData = ((DataBufferInt)image2.getRaster().getDataBuffer()).getData();
         this.setAreaOpaque(0, 0, 32 * b, 16 * b);
         this.setAreaTransparent(32 * b, 0, 64 * b, 32 * b);
         this.setAreaOpaque(0, 16 * b, 64 * b, 32 * b);
         this.setAreaTransparent(0, 32 * b, 16 * b, 48 * b);
         this.setAreaTransparent(16 * b, 32 * b, 40 * b, 48 * b);
         this.setAreaTransparent(40 * b, 32 * b, 56 * b, 48 * b);
         this.setAreaTransparent(0, 48 * b, 16 * b, 64 * b);
         this.setAreaOpaque(16 * b, 48 * b, 48 * b, 64 * b);
         this.setAreaTransparent(48 * b, 48 * b, 64 * b, 64 * b);
         return image2;
      }
   }

   private void setAreaTransparent(int n, int n2, int n3, int n4) {
      if (!this.hasTransparency(n, n2, n3, n4)) {
         for (int i = n; i < n3; i++) {
            for (int j = n2; j < n4; j++) {
               this.imageData[i + j * this.imageWidth] = this.imageData[i + j * this.imageWidth] & 16777215;
            }
         }
      }
   }

   @Override
   public void skinAvailable() {
   }
}
