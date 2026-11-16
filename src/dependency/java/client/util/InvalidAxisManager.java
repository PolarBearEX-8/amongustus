package client.util;

import client.render.WorldRenderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InvalidAxisManager {
   public String labelText4 = null;
   public int count4 = -1;
   public int count7 = -1;
   public int count2 = -1;
   public int count = -1;
   public int count5 = 1;
   public boolean enabled4 = false;
   public float factor = 1.0F;
   public float[] factorArray2;
   public TargetAggregator targetAggregator;
   public int count3;
   public boolean enabled;
   public boolean enabled3;
   public boolean enabled2;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray;
   public TargetAggregator targetAggregator2;
   public float factor2;
   public SplineSelector splineSelector;
   public int count6;
   public World world;
   public static final float[] factorArray = new float[]{1.0F, 0.0F, 0.0F};
   public static final String labelText3 = "clear";
   public static final String labelText = "rain";
   public static final String labelText2 = "thunder";

   private float[] resolveFloatArray(String text, float[] factorArray3) {
      if (text == null) {
         return factorArray3;
      } else {
         String[] textArray = BuildManager.resolveTextArray(text, " ");
         if (textArray.length != 3) {
            BuildManager.applyText3("Invalid axis: " + text);
            return factorArray3;
         } else {
            float[] factorArray4 = new float[3];

            for (int index = 0; index < textArray.length; index++) {
               factorArray4[index] = BuildManager.computeFactor(textArray[index], Float.MIN_VALUE);
               if (factorArray4[index] == Float.MIN_VALUE) {
                  BuildManager.applyText3("Invalid axis: " + text);
                  return factorArray3;
               }

               if (factorArray4[index] < -1.0F || factorArray4[index] > 1.0F) {
                  BuildManager.applyText3("Invalid axis values: " + text);
                  return factorArray3;
               }
            }

            float factor3 = factorArray4[0];
            float factor4 = factorArray4[1];
            float factor5 = factorArray4[2];
            if (factor3 * factor3 + factor4 * factor4 + factor5 * factor5 < 1.0E-5F) {
               BuildManager.applyText3("Invalid axis values: " + text);
               return factorArray3;
            } else {
               return new float[]{factor5, factor4, -factor3};
            }
         }
      }
   }

   private List resolveList(String text) {
      List list = Arrays.asList("clear", "rain", "thunder");
      ArrayList list2 = new ArrayList();
      String[] textArray = BuildManager.resolveTextArray(text, " ");

      for (int index = 0; index < textArray.length; index++) {
         String text2 = textArray[index];
         if (!list.contains(text2)) {
            BuildManager.applyText3("Unknown weather: " + text2);
         } else {
            list2.add(text2);
         }
      }

      return list2;
   }

   private boolean matchesText(String text, boolean flag) {
      if (text == null) {
         return flag;
      } else if (text.toLowerCase().equals("true")) {
         return true;
      } else if (text.toLowerCase().equals("false")) {
         return false;
      } else {
         BuildManager.applyText3("Unknown boolean: " + text);
         return flag;
      }
   }

   private float computeFactor(float factor3, float factor4) {
      float factor5 = 1.0F - factor3;
      float factor6 = factor3 - factor4;
      float factor7 = 0.0F;
      if (this.enabled) {
         factor7 += factor5;
      }

      if (this.enabled3) {
         factor7 += factor6;
      }

      if (this.enabled2) {
         factor7 += factor4;
      }

      return RotationRanker.computeFactor(factor7, 0.0F, 1.0F);
   }

   public boolean matchesText(String text) {
      if (this.labelText4 == null) {
         BuildManager.applyText3("No source texture: " + text);
         return false;
      } else {
         this.labelText4 = InternalPngManager.resolveText(this.labelText4, InternalPngManager.resolveText(text));
         if (this.count4 >= 0 && this.count7 >= 0 && this.count >= 0) {
            int count8 = this.computeCount(this.count7 - this.count4);
            if (this.count2 < 0) {
               this.count2 = this.computeCount(this.count - count8);
               if (this.checkIndex(this.count2, this.count4, this.count7)) {
                  this.count2 = this.count7;
               }
            }

            int count9 = this.computeCount(this.count2 - this.count7);
            int count10 = this.computeCount(this.count - this.count2);
            int count11 = this.computeCount(this.count4 - this.count);
            int count12 = count8 + count9 + count10 + count11;
            if (count12 != 24000) {
               BuildManager.applyText3("Invalid fadeIn/fadeOut times, sum is not 24h: " + count12);
               return false;
            } else if (this.factor < 0.0F) {
               BuildManager.applyText3("Invalid speed: " + this.factor);
               return false;
            } else if (this.count3 <= 0) {
               BuildManager.applyText3("Invalid daysLoop: " + this.count3);
               return false;
            } else {
               return true;
            }
         } else {
            BuildManager.applyText3("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
            return false;
         }
      }
   }

   private boolean checkIndex(int count8, int count9, int count10) {
      return count9 <= count10 ? count8 >= count9 && count8 <= count10 : count8 >= count9 || count8 <= count10;
   }

   private int computeCount(String text) {
      if (text == null) {
         return -1;
      } else {
         String[] textArray = BuildManager.resolveTextArray(text, ":");
         if (textArray.length != 2) {
            BuildManager.applyText3("Invalid time: " + text);
            return -1;
         } else {
            String text2 = textArray[0];
            String text3 = textArray[1];
            int count8 = BuildManager.computeCount(text2, -1);
            int count9 = BuildManager.computeCount(text3, -1);
            if (count8 >= 0 && count8 <= 23 && count9 >= 0 && count9 <= 59) {
               int count10 = count8 - 6;
               if (count10 < 0) {
                  count10 += 24;
               }

               return count10 * 1000 + (int)((double)count9 / 60.0 * 1000.0);
            } else {
               BuildManager.applyText3("Invalid time: " + text);
               return -1;
            }
         }
      }
   }

   private void applyTessellator(Tessellator tessellator, int count8) {
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      double offset = (double)(count8 % 3) / 3.0;
      double offset2 = (double)(count8 / 3) / 2.0;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(-100.0, -100.0, -100.0).getWorldRenderer6(offset, offset2).updateState();
      worldRenderer.getWorldRenderer(-100.0, -100.0, 100.0).getWorldRenderer6(offset, offset2 + 0.5).updateState();
      worldRenderer.getWorldRenderer(100.0, -100.0, 100.0).getWorldRenderer6(offset + 0.3333333333333333, offset2 + 0.5).updateState();
      worldRenderer.getWorldRenderer(100.0, -100.0, -100.0).getWorldRenderer6(offset + 0.3333333333333333, offset2).updateState();
      tessellator.updateState();
   }

   public boolean matchesWorldByWorld(World world2, int count8) {
      if (world2 != this.world) {
         this.world = world2;
         this.splineSelector = null;
      }

      if (this.checkIndex(count8, this.count, this.count4)) {
         return false;
      } else {
         if (this.targetAggregator != null) {
            long value = world2.getTimestampAsTime() - (long)this.count4;

            while (value < 0L) {
               value += (long)(24000 * this.count3);
            }

            int count9 = (int)(value / 24000L) % this.count3;
            if (!this.targetAggregator.checkIndex(count9)) {
               return false;
            }
         }

         return true;
      }
   }

   private float computeFactor(int count8) {
      if (this.checkIndex(count8, this.count4, this.count7)) {
         int count9 = this.computeCount(this.count7 - this.count4);
         return (float)this.computeCount(count8 - this.count4) / (float)count9;
      } else if (this.checkIndex(count8, this.count7, this.count2)) {
         return 1.0F;
      } else if (this.checkIndex(count8, this.count2, this.count)) {
         int count10 = this.computeCount(this.count - this.count2);
         int count11 = this.computeCount(count8 - this.count2);
         return 1.0F - (float)count11 / (float)count10;
      } else {
         return 0.0F;
      }
   }

   private float computeFactor(String text, float factor3) {
      if (text == null) {
         return factor3;
      } else {
         float factor4 = BuildManager.computeFactor(text, Float.MIN_VALUE);
         if (factor4 == Float.MIN_VALUE) {
            BuildManager.applyText3("Invalid value: " + text);
            return factor3;
         } else {
            return factor4;
         }
      }
   }

   private float computeFactor3(World world2) {
      Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (renderViewEntity == null) {
         return 0.0F;
      } else {
         BlockPos blockPos = renderViewEntity.getBlockPos();
         if (this.biomeIDIsOutOfBoundsManagerArray != null) {
            BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = world2.resolveBiomeIDIsOutOfBoundsManager(blockPos);
            if (biomeIDIsOutOfBoundsManager == null) {
               return 0.0F;
            }

            if (!GeometrySelector.matchesBiomeIDIsOutOfBoundsManage(biomeIDIsOutOfBoundsManager, this.biomeIDIsOutOfBoundsManagerArray)) {
               return 0.0F;
            }
         }

         return this.targetAggregator2 != null && !this.targetAggregator2.checkIndex(blockPos.getY()) ? 0.0F : 1.0F;
      }
   }

   private int computeCount(int count8) {
      int count9 = count8;

      while (count9 >= 24000) {
         count9 -= 24000;
      }

      while (count9 < 0) {
         count9 += 24000;
      }

      return count9;
   }

   private float computeFactorByWorld(World world2) {
      if (this.biomeIDIsOutOfBoundsManagerArray == null && this.targetAggregator2 == null) {
         return 1.0F;
      } else {
         float factor3 = this.computeFactor3(world2);
         if (this.splineSelector == null) {
            this.splineSelector = new SplineSelector(factor3, this.factor2);
         }

         return this.splineSelector.computeFactor(factor3);
      }
   }

   public void applyWorld(World world2, int count8, float posX, float posY, float posZ) {
      float factor3 = this.computeFactorByWorld(world2);
      float factor4 = this.computeFactor(posY, posZ);
      float factor5 = this.computeFactor(count8);
      float factor6 = BuildManager.computeFactor(factor3 * factor4 * factor5, 0.0F, 1.0F);
      if (factor6 >= 1.0E-4F) {
         GlStateManager.setActiveTexture(this.count6);
         FovSorter.applyIndex(this.count5, factor6);
         GlStateManager.pushMatrix();
         if (this.enabled4) {
            float factor7 = 0.0F;
            if (this.factor != (float)Math.round(this.factor)) {
               long value = (world2.getTimestampAsTime() + 18000L) / 24000L;
               double offset = (double)(this.factor % 1.0F);
               factor7 = (float)((double)value * offset % 1.0);
            }

            GlStateManager.rotate(360.0F * (factor7 + posX * this.factor), this.factorArray2[0], this.factorArray2[1], this.factorArray2[2]);
         }

         Tessellator tessellator = Tessellator.getTessellator();
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
         this.applyTessellator(tessellator, 4);
         GlStateManager.pushMatrix();
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         this.applyTessellator(tessellator, 1);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         this.applyTessellator(tessellator, 0);
         GlStateManager.popMatrix();
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.applyTessellator(tessellator, 5);
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.applyTessellator(tessellator, 2);
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.applyTessellator(tessellator, 3);
         GlStateManager.popMatrix();
      }
   }

   public InvalidAxisManager(Properties properties, String text) {
      this.factorArray2 = factorArray;
      this.targetAggregator = null;
      this.count3 = 8;
      this.enabled = true;
      this.enabled3 = false;
      this.enabled2 = false;
      this.biomeIDIsOutOfBoundsManagerArray = null;
      this.targetAggregator2 = null;
      this.factor2 = 1.0F;
      this.splineSelector = null;
      this.count6 = -1;
      this.world = null;
      NetherManager netherManager = new NetherManager("CustomSky");
      this.labelText4 = properties.getProperty("source", text);
      this.count4 = this.computeCount(properties.getProperty("startFadeIn"));
      this.count7 = this.computeCount(properties.getProperty("endFadeIn"));
      this.count2 = this.computeCount(properties.getProperty("startFadeOut"));
      this.count = this.computeCount(properties.getProperty("endFadeOut"));
      this.count5 = FovSorter.computeCount(properties.getProperty("blend"));
      this.enabled4 = this.matchesText(properties.getProperty("rotate"), true);
      this.factor = this.computeFactor(properties.getProperty("speed"), 1.0F);
      this.factorArray2 = this.resolveFloatArray(properties.getProperty("axis"), factorArray);
      this.targetAggregator = netherManager.resolveTargetAggregator(properties.getProperty("days"));
      this.count3 = netherManager.computeCount(properties.getProperty("daysLoop"), 8);
      List list = this.resolveList(properties.getProperty("weather", "clear"));
      this.enabled = list.contains("clear");
      this.enabled3 = list.contains("rain");
      this.enabled2 = list.contains("thunder");
      this.biomeIDIsOutOfBoundsManagerArray = netherManager.resolveBiomeIDIsOutOfBoundsManagerA(properties.getProperty("biomes"));
      this.targetAggregator2 = netherManager.resolveTargetAggregator(properties.getProperty("heights"));
      this.factor2 = this.computeFactor(properties.getProperty("transition"), 1.0F);
   }

   @Override
   public String toString() {
      return "" + this.labelText4 + ", " + this.count4 + "-" + this.count7 + " " + this.count2 + "-" + this.count;
   }
}
