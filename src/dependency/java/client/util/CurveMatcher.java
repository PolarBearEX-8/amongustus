package client.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class CurveMatcher {
   public int count;
   public BufferedImage bufferedImage;
   public int count2;
   public ArrayList arrayList;

   private BufferedImage resolveBufferedImage(BufferedImage bufferedImage2, int count3, int count4) {
      BufferedImage bufferedImage3 = new BufferedImage(count3, count4, 12);
      Graphics2D graphics2D = bufferedImage3.createGraphics();
      graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      graphics2D.drawImage(bufferedImage2, 0, 0, count3, count4, (ImageObserver)null);
      graphics2D.dispose();
      return bufferedImage3;
   }

   public CurveMatcher(BufferedImage bufferedImage2, int count3, int count4, boolean flag) {
      this.count2 = count3;
      this.count = count4;
      if (count3 != bufferedImage2.getWidth() || count4 != bufferedImage2.getHeight()) {
         bufferedImage2 = this.resolveBufferedImage(bufferedImage2, count3, count4);
      }

      if (flag) {
         this.bufferedImage = bufferedImage2;
      } else {
         this.arrayList = new ArrayList(100);
         WritableRaster raster = bufferedImage2.getRaster();

         for (int index = 0; index < count3; index++) {
            for (int index2 = 0; index2 < count4; index2++) {
               if (((byte[])raster.getDataElements(index, index2, null))[0] == 0) {
                  this.arrayList.add(new int[]{index, index2});
               }
            }
         }
      }
   }

   public BufferedImage resolveBufferedImage(BufferedImage bufferedImage2) {
      if (this.arrayList != null) {
         for (Object item : this.arrayList) {
            int[] countArray = (int[])item;
            bufferedImage2.setRGB(countArray[0], countArray[1], 0);
         }

         return bufferedImage2;
      } else {
         boolean flag = !bufferedImage2.getColorModel().hasAlpha();
         BufferedImage bufferedImage3 = flag ? new BufferedImage(this.count2, this.count, 2) : bufferedImage2;
         WritableRaster raster = this.bufferedImage.getRaster();

         for (int index = 0; index < this.count2; index++) {
            for (int index2 = 0; index2 < this.count; index2++) {
               if (((byte[])raster.getDataElements(index, index2, null))[0] == 0) {
                  bufferedImage3.setRGB(index, index2, 0);
               } else if (flag) {
                  bufferedImage3.setRGB(index, index2, bufferedImage2.getRGB(index, index2));
               }
            }
         }

         return bufferedImage3;
      }
   }

   public CurveMatcher(BufferedImage bufferedImage2, int count3, int count4) {
      this(bufferedImage2, count3, count4, false);
   }
}
