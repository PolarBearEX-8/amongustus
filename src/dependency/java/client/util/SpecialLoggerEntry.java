package client.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class SpecialLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public static final IntBuffer intBuffer = VectorMatcher.resolveIntBuffer(4194304);
   public static final DynamicTexture dynamicTexture = new DynamicTexture(16, 16);
   public static final int[] countArray3 = dynamicTexture.getCountArray();
   public static final int[] countArray;
   public static int[] countArray2 = new int[4194304];

   public static int[][] resolveIndexArray(int count, int count2, int[][] countMatrix) {
      int[][] countMatrix2 = new int[count + 1][];
      countMatrix2[0] = countMatrix[0];
      if (count > 0) {
         boolean flag = false;

         for (int index = 0; index < countMatrix[0].length; index++) {
            if (countMatrix[0][index] >> 24 == 0) {
               flag = true;
               break;
            }
         }

         for (int index2 = 1; index2 <= count; index2++) {
            if (countMatrix[index2] != null) {
               countMatrix2[index2] = countMatrix[index2];
            } else {
               int[] countArray4 = countMatrix2[index2 - 1];
               int[] countArray5 = new int[countArray4.length >> 2];
               int count3 = count2 >> index2;
               int count4 = countArray5.length / count3;
               int count5 = count3 << 1;

               for (int index3 = 0; index3 < count3; index3++) {
                  for (int index4 = 0; index4 < count4; index4++) {
                     int count6 = 2 * (index3 + index4 * count5);
                     countArray5[index3 + index4 * count3] = computeCount(countArray4[count6 + 0], countArray4[count6 + 1], countArray4[count6 + 0 + count5], countArray4[count6 + 1 + count5], flag);
                  }
               }

               countMatrix2[index2] = countArray5;
            }
         }
      }

      return countMatrix2;
   }

   public static int computeCount(int count, BufferedImage bufferedImage, boolean flag, boolean flag2) {
      applyIndex(count, bufferedImage.getWidth(), bufferedImage.getHeight());
      return computeCount(count, bufferedImage, 0, 0, flag, flag2);
   }

   public static void applyIndexArray(int[][] countMatrix, int count, int count2, int count3, int count4, boolean flag, boolean flag2) {
      for (int index = 0; index < countMatrix.length; index++) {
         int[] countArray4 = countMatrix[index];
         applyIndexArray(index, countArray4, count >> index, count2 >> index, count3 >> index, count4 >> index, flag, flag2, countMatrix.length > 1);
      }
   }

   private static void applyIndexArrayByIndexArray(int[] countArray4, int count, int count2) {
      int[] countArray5 = countArray4;
      if (Minecraft.getMinecraft().gameSettings.state105) {
         countArray5 = resolveIndexArray(countArray4);
      }

      ((Buffer)intBuffer).clear();
      intBuffer.put(countArray5, count, count2);
      ((Buffer)intBuffer).position(0).limit(count2);
   }

   private static int computeCount(int count, int count2, int count3, int count4, int count5) {
      float factor = (float)Math.pow((double)((float)(count >> count5 & 0xFF) / 255.0F), 2.2);
      float factor2 = (float)Math.pow((double)((float)(count2 >> count5 & 0xFF) / 255.0F), 2.2);
      float factor3 = (float)Math.pow((double)((float)(count3 >> count5 & 0xFF) / 255.0F), 2.2);
      float factor4 = (float)Math.pow((double)((float)(count4 >> count5 & 0xFF) / 255.0F), 2.2);
      return (int)((double)((float)Math.pow((double)(factor + factor2 + factor3 + factor4) * 0.25, 0.45454545454545453)) * 255.0);
   }

   public static int computeCount(int count, BufferedImage bufferedImage) {
      return computeCount(count, bufferedImage, false, false);
   }

   public static int[] resolveIndexArray(IResourceManager iResourceManager, ResourceLocation location) throws IOException {
      BufferedImage bufferedImage = resolveBufferedImage(iResourceManager.resolveIResourceByResourceLocation(location).getInputStream());
      if (bufferedImage == null) {
         return null;
      } else {
         int width = bufferedImage.getWidth();
         int height = bufferedImage.getHeight();
         int[] countArray4 = new int[width * height];
         bufferedImage.getRGB(0, 0, width, height, countArray4, 0, width);
         return countArray4;
      }
   }

   public static int[] resolveIndexArray(int[] countArray4) {
      int[] countArray5 = new int[countArray4.length];

      for (int index = 0; index < countArray4.length; index++) {
         countArray5[index] = computeCount(countArray4[index]);
      }

      return countArray5;
   }

   public static BufferedImage resolveBufferedImage(InputStream inputStream) throws IOException {
      if (inputStream == null) {
         return null;
      } else {
         BufferedImage bufferedImage;
         try {
            bufferedImage = ImageIO.read(inputStream);
         } finally {
            IOUtils.closeQuietly(inputStream);
         }

         return bufferedImage;
      }
   }

   public static void applyIndex(int count, int count2, int count3, int count4) {
      synchronized (SpecialLoggerEntry.class) {
         applyIndex(count);
         applyIndexByIndex(count);
      }

      if (count2 >= 0) {
         GL11.glTexParameteri(3553, 33085, count2);
         GL11.glTexParameterf(3553, 33082, 0.0F);
         GL11.glTexParameterf(3553, 33083, (float)count2);
         GL11.glTexParameterf(3553, 34049, 0.0F);
      }

      for (int index = 0; index <= count2; index++) {
         GL11.glTexImage2D(3553, index, 6408, count3 >> index, count4 >> index, 0, 32993, 33639, (IntBuffer)null);
      }
   }

   public static int computeCount(int count) {
      int count2 = count >> 24 & 0xFF;
      int count3 = count >> 16 & 0xFF;
      int count4 = count >> 8 & 0xFF;
      int count5 = count & 0xFF;
      int count6 = (count3 * 30 + count4 * 59 + count5 * 11) / 100;
      int count7 = (count3 * 30 + count4 * 70) / 100;
      int count8 = (count3 * 30 + count5 * 70) / 100;
      return count2 << 24 | count6 << 16 | count7 << 8 | count8;
   }

   public static void applyIndexByIndex(int count) {
      GlStateManager.setActiveTexture(count);
   }

   public static void setEnabledByFlag(boolean enabledByFlag) {
      if (enabledByFlag) {
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
      } else {
         GL11.glTexParameteri(3553, 10242, 10497);
         GL11.glTexParameteri(3553, 10243, 10497);
      }
   }

   private static int computeCount(int count, int count2, int count3, int count4, boolean flag) {
      return DimManager.computeCount(count, count2, count3, count4);
   }

   public static int getCount() {
      return GlStateManager.getStateInt6();
   }

   private static void applyBufferedImage(BufferedImage bufferedImage, int count, int count2, boolean flag, boolean flag2) {
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      int count3 = 4194304 / width;
      int[] countArray4 = countArray2;
      setEnabled(flag);
      setEnabledByFlag(flag2);

      for (int index = 0; index < width * height; index += width * count3) {
         int count4 = index / width;
         int count5 = Math.min(count3, height - count4);
         int count6 = width * count5;
         bufferedImage.getRGB(0, count4, width, count5, countArray4, 0, width);
         applyIndexArray(countArray4, count6);
         GL11.glTexSubImage2D(3553, 0, count, count2 + count4, width, count5, 32993, 33639, intBuffer);
      }
   }

   public static int computeCount(int count, BufferedImage bufferedImage, int count2, int count3, boolean flag, boolean flag2) {
      applyIndexByIndex(count);
      applyBufferedImage(bufferedImage, count2, count3, flag, flag2);
      return count;
   }

   public static void applyIndexArray(int[] countArray4, int count, int count2) {
      int[] countArray5 = new int[count];
      int count3 = count2 / 2;

      for (int index = 0; index < count3; index++) {
         System.arraycopy(countArray4, index * count, countArray5, 0, count);
         System.arraycopy(countArray4, (count2 - 1 - index) * count, countArray4, index * count, count);
         System.arraycopy(countArray5, 0, countArray4, (count2 - 1 - index) * count, count);
      }
   }

   private static void setEnabled(boolean enabled) {
      applyFlag(enabled, false);
   }

   private static void applyIndexArray(int[] countArray4, int count) {
      applyIndexArrayByIndexArray(countArray4, 0, count);
   }

   private static void applyIndexArray(int count, int[] countArray4, int count2, int count3, int count4, int count5, boolean flag, boolean flag2, boolean flag3) {
      int count6 = 4194304 / count2;
      applyFlag(flag, flag3);
      setEnabledByFlag(flag2);
      int count7 = 0;

      while (count7 < count2 * count3) {
         int count8 = count7 / count2;
         int count9 = Math.min(count6, count3 - count8);
         int count10 = count2 * count9;
         applyIndexArrayByIndexArray(countArray4, count7, count10);
         GL11.glTexSubImage2D(3553, count, count4, count5 + count8, count2, count9, 32993, 33639, intBuffer);
         count7 += count2 * count9;
      }
   }

   public static void applyIndexArray(int count, int[] countArray4, int count2, int count3) {
      applyIndexByIndex(count);
      applyIndexArray(0, countArray4, count2, count3, 0, 0, false, false, false);
   }

   public static void applyIndex(int count) {
      GlStateManager.deleteTexture(count);
   }

   public static void applyIndex(int count, int count2, int count3) {
      applyIndex(count, 0, count2, count3);
   }

   public static void applyFlag(boolean flag, boolean flag2) {
      if (flag) {
         GL11.glTexParameteri(3553, 10241, flag2 ? 9987 : 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
      } else {
         int count = BuildManager.getCount2();
         GL11.glTexParameteri(3553, 10241, flag2 ? count : 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
      }
   }

   static {
      int[] countArray4 = new int[]{-524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040};
      int[] countArray5 = new int[]{-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216};
      int length = countArray4.length;

      for (int index = 0; index < 16; index++) {
         System.arraycopy(index < length ? countArray4 : countArray5, 0, countArray3, 16 * index, length);
         System.arraycopy(index < length ? countArray5 : countArray4, 0, countArray3, 16 * index + length, length);
      }

      dynamicTexture.updateState2();
      countArray = new int[4];
   }
}
