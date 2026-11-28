package net.optifine;

import net.minecraft.world.World;
import net.optifine.config.Config;

public class LightMap {
   private CustomColormap lightMapRgb = null;
   private float[][] sunRgbs = new float[16][3];
   private float[][] torchRgbs = new float[16][3];

   private static void warn(String str) {
      Config.warn("CustomColors: " + str);
   }

   public LightMap(CustomColormap customColormap) {
      this.lightMapRgb = customColormap;
   }

   public boolean updateLightmap(World world, float f, int[] ints, boolean flag) {
      if (this.lightMapRgb == null) {
         return false;
      } else {
         int height = this.lightMapRgb.getHeight();
         if (flag && height < 64) {
            return false;
         } else {
            int width = this.lightMapRgb.getWidth();
            if (width < 16) {
               warn("Invalid lightmap width: " + width);
               this.lightMapRgb = null;
               return false;
            } else {
               int n = 0;
               if (flag) {
                  n = width * 16 * 2;
               }

               float f2 = 1.1666666F * (world.getSunBrightness(1.0F) - 0.2F);
               if (world.getLastLightningBolt() > 0) {
                  f2 = 1.0F;
               }

               f2 = Config.limitTo1(f2);
               float f3 = f2 * (float)(width - 1);
               float f4 = Config.limitTo1(f + 0.5F) * (float)(width - 1);
               float f5 = Config.limitTo1(Config.getGameSettings().gammaSetting);
               boolean flag2 = f5 > 1.0E-4F;
               float[][] colorsRgb = this.lightMapRgb.getColorsRgb();
               this.getLightMapColumn(colorsRgb, f3, n, width, this.sunRgbs);
               this.getLightMapColumn(colorsRgb, f4, n + 16 * width, width, this.torchRgbs);
               float[] floats = new float[3];

               for (int i = 0; i < 16; i++) {
                  for (int j = 0; j < 16; j++) {
                     for (int k = 0; k < 3; k++) {
                        float f6 = Config.limitTo1(this.sunRgbs[i][k] + this.torchRgbs[j][k]);
                        if (flag2) {
                           float f7 = 1.0F - f6;
                           f7 = 1.0F - f7 * f7 * f7 * f7;
                           f6 = f5 * f7 + (1.0F - f5) * f6;
                        }

                        floats[k] = f6;
                     }

                     ints[i * 16 + j] = 0xFF000000 | ((int)(floats[0] * 255.0F)) << 16 | ((int)(floats[1] * 255.0F)) << 8 | ((int)(floats[2] * 255.0F));
                  }
               }

               return true;
            }
         }
      }
   }

   private static void dbg(String str) {
      Config.dbg("CustomColors: " + str);
   }

   private void getLightMapColumn(float[][] floatsArr, float f, int n, int n2, float[][] floatsArr2) {
      int n3 = (int)Math.floor((double)f);
      int n4 = (int)Math.ceil((double)f);
      if (n3 == n4) {
         for (int i = 0; i < 16; i++) {
            float[] floats = floatsArr[n + i * n2 + n3];
            float[] floats2 = floatsArr2[i];

            for (int j = 0; j < 3; j++) {
               floats2[j] = floats[j];
            }
         }
      } else {
         float f2 = 1.0F - (f - (float)n3);
         float f3 = 1.0F - ((float)n4 - f);

         for (int k = 0; k < 16; k++) {
            float[] floats3 = floatsArr[n + k * n2 + n3];
            float[] floats4 = floatsArr[n + k * n2 + n4];
            float[] floats5 = floatsArr2[k];

            for (int m = 0; m < 3; m++) {
               floats5[m] = floats3[m] * f2 + floats4[m] * f3;
            }
         }
      }
   }

   public CustomColormap getLightMapRgb() {
      return this.lightMapRgb;
   }
}
