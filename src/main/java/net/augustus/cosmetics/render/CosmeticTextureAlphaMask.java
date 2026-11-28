package net.augustus.cosmetics.render;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class CosmeticTextureAlphaMask {
   private int width;
   private int height;
   private ArrayList transparentPixels;
   private BufferedImage maskImage;

   public BufferedImage applyMask(BufferedImage image) {
      if (this.transparentPixels != null) {
         for (int[] ints : (Iterable<int[]>)(this.transparentPixels)) {
            image.setRGB(ints[0], ints[1], 0);
         }

         return image;
      } else {
         boolean flag = !image.getColorModel().hasAlpha();
         BufferedImage image2 = flag ? new BufferedImage(this.width, this.height, 2) : image;
         WritableRaster raster = this.maskImage.getRaster();

         for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
               if (((byte[])raster.getDataElements(i, j, null))[0] == 0) {
                  image2.setRGB(i, j, 0);
               } else if (flag) {
                  image2.setRGB(i, j, image.getRGB(i, j));
               }
            }
         }

         return image2;
      }
   }

   public CosmeticTextureAlphaMask(BufferedImage image, int n, int n2) {
       this(image, n, n2, false);
   }

   private BufferedImage resizeMask(BufferedImage image, int n, int n2) {
      BufferedImage image2 = new BufferedImage(n, n2, 12);
      Graphics2D g2d = image2.createGraphics();
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      g2d.drawImage(image, 0, 0, n, n2, (ImageObserver)null);
      g2d.dispose();
      return image2;
   }

   public CosmeticTextureAlphaMask(BufferedImage image, int n, int n2, boolean flag) {
      this.width = n;
      this.height = n2;
      if (n != image.getWidth() || n2 != image.getHeight()) {
         image = this.resizeMask(image, n, n2);
      }

      if (flag) {
         this.maskImage = image;
      } else {
         this.transparentPixels = new ArrayList(100);
         WritableRaster raster = image.getRaster();

         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n2; j++) {
               if (((byte[])raster.getDataElements(i, j, null))[0] == 0) {
                  this.transparentPixels.add(new int[]{i, j});
               }
            }
         }
      }
   }



}
