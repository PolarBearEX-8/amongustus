package client.util;

import java.awt.Dimension;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class DimManager {
   public final String labelText;
   public final int count;
   public final int count2;
   public final int[] countArray2;
   public final boolean enabled;
   public int[][] countArray;
   public IntBuffer[] intBufferArray;
   public Dimension[] dimensionArray;

   public static IntBuffer[] resolveIntBufferArray(Dimension[] dimensionArray2, int[][] countMatrix) {
      if (dimensionArray2 == null) {
         return null;
      } else {
         IntBuffer[] bufferArray = new IntBuffer[dimensionArray2.length];

         for (int index = 0; index < dimensionArray2.length; index++) {
            Dimension dimension = dimensionArray2[index];
            IntBuffer buffer = VectorMatcher.resolveIntBuffer(dimension.width * dimension.height);
            int[] countArray3 = countMatrix[index];
            ((Buffer)buffer).clear();
            buffer.put(countArray3);
            ((Buffer)buffer).clear();
            bufferArray[index] = buffer;
         }

         return bufferArray;
      }
   }

   public static int[][] resolveIndexArray(int[] countArray3, int count3, int count4, Dimension[] dimensionArray2) {
      int[] countArray4 = countArray3;
      int count5 = count3;
      boolean flag = true;
      int[][] countMatrix = new int[dimensionArray2.length][];

      for (int index = 0; index < dimensionArray2.length; index++) {
         Dimension dimension = dimensionArray2[index];
         int width = dimension.width;
         int height = dimension.height;
         int[] countArray5 = new int[width * height];
         countMatrix[index] = countArray5;
         if (flag) {
            for (int index2 = 0; index2 < width; index2++) {
               for (int index3 = 0; index3 < height; index3++) {
                  int count6 = countArray4[index2 * 2 + 0 + (index3 * 2 + 0) * count5];
                  int count7 = countArray4[index2 * 2 + 1 + (index3 * 2 + 0) * count5];
                  int count8 = countArray4[index2 * 2 + 1 + (index3 * 2 + 1) * count5];
                  int count9 = countArray4[index2 * 2 + 0 + (index3 * 2 + 1) * count5];
                  int count10 = computeCount(count6, count7, count8, count9);
                  countArray5[index2 + index3 * width] = count10;
               }
            }
         }

         countArray4 = countArray5;
         count5 = width;
         if (width <= 1 || height <= 1) {
            flag = false;
         }
      }

      return countMatrix;
   }

   private static int computeCountByIndex(int count3, int count4) {
      int count5 = count3;
      int count6 = count4;
      int count7 = (count3 & 0xFF000000) >> 24 & 0xFF;
      int count8 = (count4 & 0xFF000000) >> 24 & 0xFF;
      int count9 = (count7 + count8) / 2;
      if (count7 == 0 && count8 == 0) {
         count7 = 1;
         count8 = 1;
      } else {
         if (count7 == 0) {
            count5 = count4;
            count9 /= 2;
         }

         if (count8 == 0) {
            count6 = count5;
            count9 /= 2;
         }
      }

      int count10 = (count5 >> 16 & 0xFF) * count7;
      int count11 = (count5 >> 8 & 0xFF) * count7;
      int count12 = (count5 & 0xFF) * count7;
      int count13 = (count6 >> 16 & 0xFF) * count8;
      int count14 = (count6 >> 8 & 0xFF) * count8;
      int count15 = (count6 & 0xFF) * count8;
      int count16 = (count10 + count13) / (count7 + count8);
      int count17 = (count11 + count14) / (count7 + count8);
      int count18 = (count12 + count15) / (count7 + count8);
      return count9 << 24 | count16 << 16 | count17 << 8 | count18;
   }

   public static void applyText(int count3, int count4, String text) {
      Dimension[] dimensionArray2 = resolveDimensionArray(count3, count4, text);

      for (int index = 0; index < dimensionArray2.length; index++) {
         Dimension dimension = dimensionArray2[index];
         int width = dimension.width;
         int height = dimension.height;
         int count5 = index + 1;
         GL11.glTexImage2D(3553, count5, 6408, width, height, 0, 32993, 33639, (IntBuffer)null);
      }
   }

   public static int computeCount(int count3, int count4, int count5, int count6) {
      int count7 = computeCountByIndex(count3, count4);
      int count8 = computeCountByIndex(count5, count6);
      return computeCountByIndex(count7, count8);
   }

   private int computeCount(int count3, int count4) {
      int count5 = (count3 & 0xFF000000) >> 24 & 0xFF;
      int count6 = (count4 & 0xFF000000) >> 24 & 0xFF;
      return (count5 + count6 >> 1 << 24) + ((count3 & 16711422) + (count4 & 16711422) >> 1);
   }

   public static Dimension[] resolveDimensionArray(int count3, int count4, String text) {
      int count5 = InternalPngManager.computeCount(count3);
      int count6 = InternalPngManager.computeCount(count4);
      if (count5 == count3 && count6 == count4) {
         ArrayList list = new ArrayList();

         while (true) {
            count5 /= 2;
            count6 /= 2;
            if (count5 <= 0 && count6 <= 0) {
               return (Dimension[])list.toArray(new Dimension[list.size()]);
            }

            if (count5 <= 0) {
               count5 = 1;
            }

            if (count6 <= 0) {
               count6 = 1;
            }

            Dimension dimension = new Dimension(count5, count6);
            list.add(dimension);
         }
      } else {
         BuildManager.applyText3("Mipmaps not possible (power of 2 dimensions needed), texture: " + text + ", dim: " + count3 + "x" + count4);
         return new Dimension[0];
      }
   }

   public DimManager(String text, int count3, int count4, int[] countArray3, boolean flag) {
      this.labelText = text;
      this.count = count3;
      this.count2 = count4;
      this.countArray2 = countArray3;
      this.enabled = flag;
      this.dimensionArray = resolveDimensionArray(count3, count4, text);
      this.countArray = resolveIndexArray(countArray3, count3, count4, this.dimensionArray);
      if (flag) {
         this.intBufferArray = resolveIntBufferArray(this.dimensionArray, this.countArray);
      }
   }
}
