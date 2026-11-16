package client.util;

import java.awt.image.BufferedImage;

public class BufferedImageEntry {
   public final BufferedImage bufferedImage;
   public final String labelText;
   public final int count;

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public BufferedImage getBufferedImage() {
      return this.bufferedImage;
   }

   public BufferedImageEntry(BufferedImage bufferedImage2, int count2, String text) {
      this.bufferedImage = bufferedImage2;
      this.count = count2;
      this.labelText = text;
   }
}
