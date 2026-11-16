package client.util;

import client.event.PreRenderBroadcaster;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class ServiceIntArrayEntry implements PreRenderBroadcaster {
   public int[] countArray;
   public int count2;
   public int count;

   @Override
   public void updateState() {
   }

   private void applyIndex(int count3, int count4, int count5, int count6) {
      for (int index = count3; index < count5; index++) {
         for (int index2 = count4; index2 < count6; index2++) {
            this.countArray[index + index2 * this.count2] = this.countArray[index + index2 * this.count2] | 0xFF000000;
         }
      }
   }

   private void applyIndexByIndex(int count3, int count4, int count5, int count6) {
      if (!this.checkIndex(count3, count4, count5, count6)) {
         for (int index = count3; index < count5; index++) {
            for (int index2 = count4; index2 < count6; index2++) {
               this.countArray[index + index2 * this.count2] = this.countArray[index + index2 * this.count2] & 16777215;
            }
         }
      }
   }

   private boolean checkIndex(int count3, int count4, int count5, int count6) {
      for (int index = count3; index < count5; index++) {
         for (int index2 = count4; index2 < count6; index2++) {
            if ((this.countArray[index + index2 * this.count2] >> 24 & 0xFF) < 128) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public BufferedImage resolveBufferedImage(BufferedImage bufferedImage) {
      if (bufferedImage == null) {
         return null;
      } else {
         this.count2 = 64;
         this.count = 64;
         int width = bufferedImage.getWidth();
         int height = bufferedImage.getHeight();

         byte byteValue;
         for (byteValue = 1; this.count2 < width || this.count < height; byteValue *= 2) {
            this.count2 *= 2;
            this.count *= 2;
         }

         BufferedImage bufferedImage2 = new BufferedImage(this.count2, this.count, 2);
         Graphics graphics = bufferedImage2.getGraphics();
         graphics.drawImage(bufferedImage, 0, 0, (ImageObserver)null);
         if (bufferedImage.getHeight() == 32 * byteValue) {
            graphics.drawImage(bufferedImage2, 24 * byteValue, 48 * byteValue, 20 * byteValue, 52 * byteValue, 4 * byteValue, 16 * byteValue, 8 * byteValue, 20 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 28 * byteValue, 48 * byteValue, 24 * byteValue, 52 * byteValue, 8 * byteValue, 16 * byteValue, 12 * byteValue, 20 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 20 * byteValue, 52 * byteValue, 16 * byteValue, 64 * byteValue, 8 * byteValue, 20 * byteValue, 12 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 24 * byteValue, 52 * byteValue, 20 * byteValue, 64 * byteValue, 4 * byteValue, 20 * byteValue, 8 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 28 * byteValue, 52 * byteValue, 24 * byteValue, 64 * byteValue, 0 * byteValue, 20 * byteValue, 4 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 32 * byteValue, 52 * byteValue, 28 * byteValue, 64 * byteValue, 12 * byteValue, 20 * byteValue, 16 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 40 * byteValue, 48 * byteValue, 36 * byteValue, 52 * byteValue, 44 * byteValue, 16 * byteValue, 48 * byteValue, 20 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 44 * byteValue, 48 * byteValue, 40 * byteValue, 52 * byteValue, 48 * byteValue, 16 * byteValue, 52 * byteValue, 20 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 36 * byteValue, 52 * byteValue, 32 * byteValue, 64 * byteValue, 48 * byteValue, 20 * byteValue, 52 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 40 * byteValue, 52 * byteValue, 36 * byteValue, 64 * byteValue, 44 * byteValue, 20 * byteValue, 48 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 44 * byteValue, 52 * byteValue, 40 * byteValue, 64 * byteValue, 40 * byteValue, 20 * byteValue, 44 * byteValue, 32 * byteValue, (ImageObserver)null);
            graphics.drawImage(bufferedImage2, 48 * byteValue, 52 * byteValue, 44 * byteValue, 64 * byteValue, 52 * byteValue, 20 * byteValue, 56 * byteValue, 32 * byteValue, (ImageObserver)null);
         }

         graphics.dispose();
         this.countArray = ((DataBufferInt)bufferedImage2.getRaster().getDataBuffer()).getData();
         this.applyIndex(0, 0, 32 * byteValue, 16 * byteValue);
         this.applyIndexByIndex(32 * byteValue, 0, 64 * byteValue, 32 * byteValue);
         this.applyIndex(0, 16 * byteValue, 64 * byteValue, 32 * byteValue);
         this.applyIndexByIndex(0, 32 * byteValue, 16 * byteValue, 48 * byteValue);
         this.applyIndexByIndex(16 * byteValue, 32 * byteValue, 40 * byteValue, 48 * byteValue);
         this.applyIndexByIndex(40 * byteValue, 32 * byteValue, 56 * byteValue, 48 * byteValue);
         this.applyIndexByIndex(0, 48 * byteValue, 16 * byteValue, 64 * byteValue);
         this.applyIndex(16 * byteValue, 48 * byteValue, 48 * byteValue, 64 * byteValue);
         this.applyIndexByIndex(48 * byteValue, 48 * byteValue, 64 * byteValue, 64 * byteValue);
         return bufferedImage2;
      }
   }
}
