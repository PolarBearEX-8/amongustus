package net.optifine;

import java.awt.Dimension;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.minecraft.client.renderer.GLAllocation;
import net.optifine.config.Config;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class Mipmaps {
   private final String iconName;
   private final int width;
   private final int height;
   private final int[] data;
   private final boolean direct;
   private int[][] mipmapDatas;
   private IntBuffer[] mipmapBuffers;
   private Dimension[] mipmapDimensions;

   public static IntBuffer[] makeMipmapBuffers(Dimension[] dimensions, int[][] intsArr) {
      if (dimensions == null) {
         return null;
      } else {
         IntBuffer[] intBuffers = new IntBuffer[dimensions.length];

         for (int i = 0; i < dimensions.length; i++) {
            Dimension dimension = dimensions[i];
            IntBuffer intBuffer = GLAllocation.createDirectIntBuffer(dimension.width * dimension.height);
            int[] ints = intsArr[i];
            ((Buffer)intBuffer).clear();
            intBuffer.put(ints);
            ((Buffer)intBuffer).clear();
            intBuffers[i] = intBuffer;
         }

         return intBuffers;
      }
   }

   public static int alphaBlend(int n, int n2, int n3, int n4) {
      return alphaBlend(alphaBlend(n, n2), alphaBlend(n3, n4));
   }

   public static Dimension[] makeMipmapDimensions(int n, int n2, String str) {
      int n3 = TextureUtils.ceilPowerOfTwo(n);
      int n4 = TextureUtils.ceilPowerOfTwo(n2);
      if (n3 == n && n4 == n2) {
         ArrayList list = new ArrayList();
         int n5 = n3;
         int n6 = n4;

         while (true) {
            n5 /= 2;
            n6 /= 2;
            if (n5 <= 0 && n6 <= 0) {
               return (Dimension[])list.toArray(new Dimension[list.size()]);
            }

            if (n5 <= 0) {
               n5 = 1;
            }

            if (n6 <= 0) {
               n6 = 1;
            }

            int n7 = n5 * n6 * 4;
            list.add(new Dimension(n5, n6));
         }
      } else {
         Config.warn("Mipmaps not possible (power of 2 dimensions needed), texture: " + str + ", dim: " + n + "x" + n2);
         return new Dimension[0];
      }
   }

   public static int[][] generateMipMapData(int[] ints, int n, int n2, Dimension[] dimensions) {
      int[] ints2 = ints;
      int n3 = n;
      boolean flag = true;
      int[][] intsArr = new int[dimensions.length][];

      for (int i = 0; i < dimensions.length; i++) {
         Dimension dimension = dimensions[i];
         int width2 = dimension.width;
         int height2 = dimension.height;
         int[] ints3 = new int[width2 * height2];
         intsArr[i] = ints3;
         if (flag) {
            for (int j = 0; j < width2; j++) {
               for (int k = 0; k < height2; k++) {
                  ints3[j + k * width2] = alphaBlend(ints2[j * 2 + 0 + (k * 2 + 0) * n3], ints2[j * 2 + 1 + (k * 2 + 0) * n3], ints2[j * 2 + 1 + (k * 2 + 1) * n3], ints2[j * 2 + 0 + (k * 2 + 1) * n3]);
               }
            }
         }

         ints2 = ints3;
         n3 = width2;
         if (width2 <= 1 || height2 <= 1) {
            flag = false;
         }
      }

      return intsArr;
   }

   public static void allocateMipmapTextures(int n, int n2, String str) {
      Dimension[] dimensions = makeMipmapDimensions(n, n2, str);

      for (int i = 0; i < dimensions.length; i++) {
         Dimension dimension = dimensions[i];
         GL11.glTexImage2D(3553, i + 1, 6408, dimension.width, dimension.height, 0, 32993, 33639, (IntBuffer)null);
      }
   }

   public Mipmaps(String str, int n, int n2, int[] ints, boolean flag) {
      this.iconName = str;
      this.width = n;
      this.height = n2;
      this.data = ints;
      this.direct = flag;
      this.mipmapDimensions = makeMipmapDimensions(n, n2, str);
      this.mipmapDatas = generateMipMapData(ints, n, n2, this.mipmapDimensions);
      if (flag) {
         this.mipmapBuffers = makeMipmapBuffers(this.mipmapDimensions, this.mipmapDatas);
      }
   }

   private int averageColor(int n, int n2) {
      return (((n & 0xFF000000) >> 24 & 0xFF) + ((n2 & 0xFF000000) >> 24 & 0xFF) >> 1 << 24) + ((n & 16711422) + (n2 & 16711422) >> 1);
   }

   private static int alphaBlend(int n, int n2) {
      int n3 = (n & 0xFF000000) >> 24 & 0xFF;
      int n4 = (n2 & 0xFF000000) >> 24 & 0xFF;
      int n5 = (n3 + n4) / 2;
      if (n3 == 0 && n4 == 0) {
         n3 = 1;
         n4 = 1;
      } else {
         if (n3 == 0) {
            n = n2;
            n5 /= 2;
         }

         if (n4 == 0) {
            n2 = n;
            n5 /= 2;
         }
      }

      int n6 = (n >> 16 & 0xFF) * n3;
      int n7 = (n >> 8 & 0xFF) * n3;
      int n8 = (n & 0xFF) * n3;
      int n9 = (n2 >> 16 & 0xFF) * n4;
      int n10 = (n2 >> 8 & 0xFF) * n4;
      int n11 = (n2 & 0xFF) * n4;
      return n5 << 24 | ((n6 + n9) / (n3 + n4)) << 16 | ((n7 + n10) / (n3 + n4)) << 8 | ((n8 + n11) / (n3 + n4));
   }
}
