package net.augustus.cosmetics.render;

import java.awt.image.BufferedImage;

public class CosmeticGifFrame {
   private final int delay;
   private final String disposalMethod;
   private final BufferedImage image;

   public int getDelay() {
      return this.delay;
   }

   public CosmeticGifFrame(BufferedImage image2, int n, String str) {
      this.image = image2;
      this.delay = n;
      this.disposalMethod = str;
   }

   public String getDisposalMethod() {
      return this.disposalMethod;
   }

   public BufferedImage getImage() {
      return this.image;
   }
}
