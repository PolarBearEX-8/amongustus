package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.Mipmaps;
import net.optifine.config.Config;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class TextureUtil {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final IntBuffer dataBuffer = GLAllocation.createDirectIntBuffer(4194304);
   public static final DynamicTexture missingTexture = new DynamicTexture(16, 16);
   public static final int[] missingTextureData = missingTexture.getTextureData();
   private static final int[] mipmapBuffer;
   private static int[] dataArray = new int[4194304];

   public static int glGenTextures() {
      return GlStateManager.generateTexture();
   }

   public static void deleteTexture(int n) {
      GlStateManager.deleteTexture(n);
   }

   public static int uploadTextureImage(int n, BufferedImage image) {
      return uploadTextureImageAllocate(n, image, false, false);
   }

   public static void uploadTexture(int n, int[] ints, int n2, int n3) {
      bindTexture(n);
      uploadTextureSub(0, ints, n2, n3, 0, 0, false, false, false);
   }

   public static int[][] generateMipmapData(int n, int n2, int[][] intsArr) {
      int[][] intsArr2 = new int[n + 1][];
      intsArr2[0] = intsArr[0];
      if (n > 0) {
         boolean flag = false;

         for (int i = 0; i < intsArr[0].length; i++) {
            if (intsArr[0][i] >> 24 == 0) {
               flag = true;
               break;
            }
         }

         for (int j = 1; j <= n; j++) {
            if (intsArr[j] != null) {
               intsArr2[j] = intsArr[j];
            } else {
               int[] ints = intsArr2[j - 1];
               int[] ints2 = new int[ints.length >> 2];
               int n3 = n2 >> j;
               int n4 = ints2.length / n3;
               int n5 = n3 << 1;

               for (int k = 0; k < n3; k++) {
                  for (int m = 0; m < n4; m++) {
                     int n6 = 2 * (k + m * n5);
                     ints2[k + m * n3] = blendColors(ints[n6 + 0], ints[n6 + 1], ints[n6 + 0 + n5], ints[n6 + 1 + n5], flag);
                  }
               }

               intsArr2[j] = ints2;
            }
         }
      }

      return intsArr2;
   }

   private static int blendColors(int n, int n2, int n3, int n4, boolean flag) {
      return Mipmaps.alphaBlend(n, n2, n3, n4);
   }

   private static int blendColorComponent(int n, int n2, int n3, int n4, int n5) {
      float f = (float)Math.pow((double)((float)(n >> n5 & 0xFF) / 255.0F), 2.2);
      float f2 = (float)Math.pow((double)((float)(n2 >> n5 & 0xFF) / 255.0F), 2.2);
      float f3 = (float)Math.pow((double)((float)(n3 >> n5 & 0xFF) / 255.0F), 2.2);
      float f4 = (float)Math.pow((double)((float)(n4 >> n5 & 0xFF) / 255.0F), 2.2);
      return (int)((double)((float)Math.pow((double)(f + f2 + f3 + f4) * 0.25, 0.45454545454545453)) * 255.0);
   }

   public static void uploadTextureMipmap(int[][] intsArr, int n, int n2, int n3, int n4, boolean flag, boolean flag2) {
      for (int i = 0; i < intsArr.length; i++) {
         uploadTextureSub(i, intsArr[i], n >> i, n2 >> i, n3 >> i, n4 >> i, flag, flag2, intsArr.length > 1);
      }
   }

   private static void uploadTextureSub(int n, int[] ints, int n2, int n3, int n4, int n5, boolean flag, boolean flag2, boolean flag3) {
      int n6 = 4194304 / n2;
      setTextureBlurMipmap(flag, flag3);
      setTextureClamped(flag2);
      int n7 = 0;

      while (n7 < n2 * n3) {
         int n8 = n7 / n2;
         int n9 = Math.min(n6, n3 - n8);
         copyToBufferPos(ints, n7, n2 * n9);
         GL11.glTexSubImage2D(3553, n, n4, n5 + n8, n2, n9, 32993, 33639, dataBuffer);
         n7 += n2 * n9;
      }
   }

   public static int uploadTextureImageAllocate(int n, BufferedImage image, boolean flag, boolean flag2) {
      allocateTexture(n, image.getWidth(), image.getHeight());
      return uploadTextureImageSub(n, image, 0, 0, flag, flag2);
   }

   public static void allocateTexture(int n, int n2, int n3) {
      allocateTextureImpl(n, 0, n2, n3);
   }

   public static void allocateTextureImpl(int n, int n2, int n3, int n4) {
      synchronized (TextureUtil.class) {
         deleteTexture(n);
         bindTexture(n);
      }

      if (n2 >= 0) {
         GL11.glTexParameteri(3553, 33085, n2);
         GL11.glTexParameterf(3553, 33082, 0.0F);
         GL11.glTexParameterf(3553, 33083, (float)n2);
         GL11.glTexParameterf(3553, 34049, 0.0F);
      }

      for (int i = 0; i <= n2; i++) {
         GL11.glTexImage2D(3553, i, 6408, n3 >> i, n4 >> i, 0, 32993, 33639, (IntBuffer)null);
      }
   }

   public static int uploadTextureImageSub(int n, BufferedImage image, int n2, int n3, boolean flag, boolean flag2) {
      bindTexture(n);
      uploadTextureImageSubImpl(image, n2, n3, flag, flag2);
      return n;
   }

   private static void uploadTextureImageSubImpl(BufferedImage image, int n, int n2, boolean flag, boolean flag2) {
      int width = image.getWidth();
      int height = image.getHeight();
      int n3 = 4194304 / width;
      int[] ints = dataArray;
      setTextureBlurred(flag);
      setTextureClamped(flag2);

      for (int i = 0; i < width * height; i += width * n3) {
         int n4 = i / width;
         int n5 = Math.min(n3, height - n4);
         int n6 = width * n5;
         image.getRGB(0, n4, width, n5, ints, 0, width);
         copyToBuffer(ints, n6);
         GL11.glTexSubImage2D(3553, 0, n, n2 + n4, width, n5, 32993, 33639, dataBuffer);
      }
   }

   public static void setTextureClamped(boolean flag) {
      if (flag) {
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
      } else {
         GL11.glTexParameteri(3553, 10242, 10497);
         GL11.glTexParameteri(3553, 10243, 10497);
      }
   }

   private static void setTextureBlurred(boolean flag) {
      setTextureBlurMipmap(flag, false);
   }

   public static void setTextureBlurMipmap(boolean flag, boolean flag2) {
      if (flag) {
         GL11.glTexParameteri(3553, 10241, flag2 ? 9987 : 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
      } else {
         int mipmapType = Config.getMipmapType();
         GL11.glTexParameteri(3553, 10241, flag2 ? mipmapType : 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
      }
   }

   private static void copyToBuffer(int[] ints, int n) {
      copyToBufferPos(ints, 0, n);
   }

   private static void copyToBufferPos(int[] ints, int n, int n2) {
      int[] ints2 = ints;
      if (Minecraft.getMinecraft().gameSettings.anaglyph) {
         ints2 = updateAnaglyph(ints);
      }

      ((Buffer)dataBuffer).clear();
      dataBuffer.put(ints2, n, n2);
      ((Buffer)dataBuffer).position(0).limit(n2);
   }

   public static void bindTexture(int n) {
      GlStateManager.bindTexture(n);
   }

   public static int[] readImageData(IResourceManager resourceManager, ResourceLocation location) throws IOException {
      BufferedImage image = readBufferedImage(resourceManager.getResource(location).getInputStream());
      if (image == null) {
         return null;
      } else {
         int width = image.getWidth();
         int height = image.getHeight();
         int[] ints = new int[width * height];
         image.getRGB(0, 0, width, height, ints, 0, width);
         return ints;
      }
   }

   public static BufferedImage readBufferedImage(InputStream in) throws IOException {
      if (in == null) {
         return null;
      } else {
         BufferedImage image;
         try {
            image = ImageIO.read(in);
         } finally {
            IOUtils.closeQuietly(in);
         }

         return image;
      }
   }

   public static int[] updateAnaglyph(int[] ints) {
      int[] ints2 = new int[ints.length];

      for (int i = 0; i < ints.length; i++) {
         ints2[i] = anaglyphColor(ints[i]);
      }

      return ints2;
   }

   public static int anaglyphColor(int n) {
      int n2 = n >> 24 & 0xFF;
      int n3 = n >> 16 & 0xFF;
      int n4 = n >> 8 & 0xFF;
      int n5 = n & 0xFF;
      return n2 << 24 | ((n3 * 30 + n4 * 59 + n5 * 11) / 100) << 16 | ((n3 * 30 + n4 * 70) / 100) << 8 | ((n3 * 30 + n5 * 70) / 100);
   }

   public static void processPixelValues(int[] ints, int n, int n2) {
      int[] ints2 = new int[n];
      int n3 = n2 / 2;

      for (int i = 0; i < n3; i++) {
         System.arraycopy(ints, i * n, ints2, 0, n);
         System.arraycopy(ints, (n2 - 1 - i) * n, ints, i * n, n);
         System.arraycopy(ints2, 0, ints, (n2 - 1 - i) * n, n);
      }
   }

   static {
      int[] ints = new int[]{-524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040};
      int[] ints2 = new int[]{-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216};
      int length = ints.length;

      for (int i = 0; i < 16; i++) {
         System.arraycopy(i < length ? ints : ints2, 0, missingTextureData, 16 * i, length);
         System.arraycopy(i < length ? ints2 : ints, 0, missingTextureData, 16 * i + length, length);
      }

      missingTexture.updateDynamicTexture();
      mipmapBuffer = new int[4];
   }
}
