package net.optifine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import net.optifine.util.SmoothFloat;
import net.optifine.config.Matches;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Config;
import net.optifine.config.ConnectedParser;
import net.optifine.config.RangeListInt;
import net.optifine.render.Blender;
import net.optifine.util.NumUtils;
import net.optifine.util.TextureUtils;

public class CustomSkyLayer {
   public String source = null;
   private int startFadeIn = -1;
   private int endFadeIn = -1;
   private int startFadeOut = -1;
   private int endFadeOut = -1;
   private int blend = 1;
   private boolean rotate = false;
   private float speed = 1.0F;
   private float[] axis;
   private RangeListInt days;
   private int daysLoop;
   private boolean weatherClear;
   private boolean weatherRain;
   private boolean weatherThunder;
   public BiomeGenBase[] biomes;
   public RangeListInt heights;
   private float transition;
   private SmoothFloat smoothPositionBrightness;
   public int textureId;
   private World lastWorld;
   public static final float[] DEFAULT_AXIS = new float[]{1.0F, 0.0F, 0.0F};
   private static final String WEATHER_CLEAR = null;
   private static final String WEATHER_RAIN = null;
   private static final String WEATHER_THUNDER = null;

   private float[] parseAxis(String str, float[] floats) {
      if (str == null) {
         return floats;
      } else {
         String[] strings = Config.tokenize(str, " ");
         if (strings.length != 3) {
            Config.warn("Invalid axis: " + str);
            return floats;
         } else {
            float[] floats2 = new float[3];

            for (int i = 0; i < strings.length; i++) {
               floats2[i] = Config.parseFloat(strings[i], Float.MIN_VALUE);
               if (floats2[i] == Float.MIN_VALUE) {
                  Config.warn("Invalid axis: " + str);
                  return floats;
               }

               if (floats2[i] < -1.0F || floats2[i] > 1.0F) {
                  Config.warn("Invalid axis values: " + str);
                  return floats;
               }
            }

            float f = floats2[0];
            float f2 = floats2[1];
            float f3 = floats2[2];
            if (f * f + f2 * f2 + f3 * f3 < 1.0E-5F) {
               Config.warn("Invalid axis values: " + str);
               return floats;
            } else {
               return new float[]{f3, f2, -f};
            }
         }
      }
   }

   public boolean isActive(World world, int n) {
      if (world != this.lastWorld) {
         this.lastWorld = world;
         this.smoothPositionBrightness = null;
      }

      if (this.timeBetween(n, this.endFadeOut, this.startFadeIn)) {
         return false;
      } else {
         if (this.days != null) {
            long l = world.getWorldTime() - (long)this.startFadeIn;

            while (l < 0L) {
               l += (long)(24000 * this.daysLoop);
            }

            int n3 = ((int)(l / 24000L)) % this.daysLoop;
            if (!this.days.isInRange(n3)) {
               return false;
            }
         }

         return true;
      }
   }

   private float parseFloat(String str, float f) {
      if (str == null) {
         return f;
      } else {
         float f2 = Config.parseFloat(str, Float.MIN_VALUE);
         if (f2 == Float.MIN_VALUE) {
            Config.warn("Invalid value: " + str);
            return f;
         } else {
            return f2;
         }
      }
   }

   public CustomSkyLayer(Properties properties, String str) {
      this.axis = DEFAULT_AXIS;
      this.days = null;
      this.daysLoop = 8;
      this.weatherClear = true;
      this.weatherRain = false;
      this.weatherThunder = false;
      this.biomes = null;
      this.heights = null;
      this.transition = 1.0F;
      this.smoothPositionBrightness = null;
      this.textureId = -1;
      this.lastWorld = null;
      ConnectedParser connectedParser = new ConnectedParser("CustomSky");
      this.source = properties.getProperty("source", str);
      this.startFadeIn = this.parseTime(properties.getProperty("startFadeIn"));
      this.endFadeIn = this.parseTime(properties.getProperty("endFadeIn"));
      this.startFadeOut = this.parseTime(properties.getProperty("startFadeOut"));
      this.endFadeOut = this.parseTime(properties.getProperty("endFadeOut"));
      this.blend = Blender.parseBlend(properties.getProperty("blend"));
      this.rotate = this.parseBoolean(properties.getProperty("rotate"), true);
      this.speed = this.parseFloat(properties.getProperty("speed"), 1.0F);
      this.axis = this.parseAxis(properties.getProperty("axis"), DEFAULT_AXIS);
      this.days = connectedParser.parseRangeListInt(properties.getProperty("days"));
      this.daysLoop = connectedParser.parseInt(properties.getProperty("daysLoop"), 8);
      List list = this.parseWeatherList(properties.getProperty("weather", "clear"));
      this.weatherClear = list.contains("clear");
      this.weatherRain = list.contains("rain");
      this.weatherThunder = list.contains("thunder");
      this.biomes = connectedParser.parseBiomes(properties.getProperty("biomes"));
      this.heights = connectedParser.parseRangeListInt(properties.getProperty("heights"));
      this.transition = this.parseFloat(properties.getProperty("transition"), 1.0F);
   }

   private List parseWeatherList(String str) {
      List list = Arrays.asList("clear", "rain", "thunder");
      ArrayList list2 = new ArrayList();
      String[] strings = Config.tokenize(str, " ");

      for (int i = 0; i < strings.length; i++) {
         String str2 = strings[i];
         if (!list.contains(str2)) {
            Config.warn("Unknown weather: " + str2);
         } else {
            list2.add(str2);
         }
      }

      return list2;
   }

   @Override
   public String toString() {
      return "" + this.source + ", " + this.startFadeIn + "-" + this.endFadeIn + " " + this.startFadeOut + "-" + this.endFadeOut;
   }

   private boolean timeBetween(int n, int n2, int n3) {
      return n2 <= n3 ? n >= n2 && n <= n3 : n >= n2 || n <= n3;
   }

   private int normalizeTime(int n) {
      while (n >= 24000) {
         n -= 24000;
      }

      while (n < 0) {
         n += 24000;
      }

      return n;
   }

   private int parseTime(String str) {
      if (str == null) {
         return -1;
      } else {
         String[] strings = Config.tokenize(str, ":");
         if (strings.length != 2) {
            Config.warn("Invalid time: " + str);
            return -1;
         } else {
            String str2 = strings[0];
            String str3 = strings[1];
            int n = Config.parseInt(str2, -1);
            int n2 = Config.parseInt(str3, -1);
            if (n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59) {
               n -= 6;
               if (n < 0) {
                  n += 24;
               }

               return n * 1000 + (int)((double)n2 / 60.0 * 1000.0);
            } else {
               Config.warn("Invalid time: " + str);
               return -1;
            }
         }
      }
   }

   public void render(World world, int n, float f, float f2, float f3) {
      float positionBrightness = this.getPositionBrightness(world);
      float weatherBrightness = this.getWeatherBrightness(f2, f3);
      float fadeBrightness = this.getFadeBrightness(n);
      float f4 = positionBrightness * weatherBrightness * fadeBrightness;
      f4 = Config.limit3(f4, 0.0F, 1.0F);
      if (f4 >= 1.0E-4F) {
         GlStateManager.bindTexture(this.textureId);
         Blender.setupBlend(this.blend, f4);
         GlStateManager.pushMatrix();
         if (this.rotate) {
            float f5 = 0.0F;
            if (this.speed != (float)Math.round(this.speed)) {
               f5 = (float)(((double)((world.getWorldTime() + 18000L) / 24000L) * ((double)(this.speed % 1.0F))) % 1.0);
            }

            GlStateManager.rotate(360.0F * (f5 + f * this.speed), this.axis[0], this.axis[1], this.axis[2]);
         }

         Tessellator instance = Tessellator.getInstance();
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(instance, 4);
         GlStateManager.pushMatrix();
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         this.renderSide(instance, 1);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         this.renderSide(instance, 0);
         GlStateManager.popMatrix();
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(instance, 5);
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(instance, 2);
         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(instance, 3);
         GlStateManager.popMatrix();
      }
   }

   private float getPositionBrightness(World world) {
      if (this.biomes == null && this.heights == null) {
         return 1.0F;
      } else {
         float positionBrightnessRaw = this.getPositionBrightnessRaw(world);
         if (this.smoothPositionBrightness == null) {
            this.smoothPositionBrightness = new SmoothFloat(positionBrightnessRaw, this.transition);
         }

         return this.smoothPositionBrightness.getSmoothValue(positionBrightnessRaw);
      }
   }

   private float getPositionBrightnessRaw(World world) {
      Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();
      if (renderViewEntity == null) {
         return 0.0F;
      } else {
         BlockPos position = renderViewEntity.getPosition();
         if (this.biomes != null) {
            BiomeGenBase biomeGenForCoords = world.getBiomeGenForCoords(position);
            if (biomeGenForCoords == null) {
               return 0.0F;
            }

            if (!Matches.biome(biomeGenForCoords, this.biomes)) {
               return 0.0F;
            }
         }

         return this.heights != null && !this.heights.isInRange(position.getY()) ? 0.0F : 1.0F;
      }
   }

   private boolean parseBoolean(String str, boolean flag) {
      if (str == null) {
         return flag;
      } else if (str.toLowerCase().equals("true")) {
         return true;
      } else if (str.toLowerCase().equals("false")) {
         return false;
      } else {
         Config.warn("Unknown boolean: " + str);
         return flag;
      }
   }

   private void renderSide(Tessellator tessellator, int n) {
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      double d = (double)(n % 3) / 3.0;
      double d2 = (double)(n / 3) / 2.0;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(-100.0, -100.0, -100.0).tex(d, d2).endVertex();
      worldRenderer.pos(-100.0, -100.0, 100.0).tex(d, d2 + 0.5).endVertex();
      worldRenderer.pos(100.0, -100.0, 100.0).tex(d + 0.3333333333333333, d2 + 0.5).endVertex();
      worldRenderer.pos(100.0, -100.0, -100.0).tex(d + 0.3333333333333333, d2).endVertex();
      tessellator.draw();
   }

   private float getFadeBrightness(int n) {
      if (this.timeBetween(n, this.startFadeIn, this.endFadeIn)) {
         int n2 = this.normalizeTime(this.endFadeIn - this.startFadeIn);
         return (float)this.normalizeTime(n - this.startFadeIn) / (float)n2;
      } else if (this.timeBetween(n, this.endFadeIn, this.startFadeOut)) {
         return 1.0F;
      } else if (this.timeBetween(n, this.startFadeOut, this.endFadeOut)) {
         int n4 = this.normalizeTime(this.endFadeOut - this.startFadeOut);
         int n5 = this.normalizeTime(n - this.startFadeOut);
         return 1.0F - (float)n5 / (float)n4;
      } else {
         return 0.0F;
      }
   }

   private float getWeatherBrightness(float f, float f2) {
      float f3 = 1.0F - f;
      float f4 = f - f2;
      float f5 = 0.0F;
      if (this.weatherClear) {
         f5 += f3;
      }

      if (this.weatherRain) {
         f5 += f4;
      }

      if (this.weatherThunder) {
         f5 += f2;
      }

      return NumUtils.limit(f5, 0.0F, 1.0F);
   }

   public boolean isValid(String str) {
      if (this.source == null) {
         Config.warn("No source texture: " + str);
         return false;
      } else {
         this.source = TextureUtils.fixResourcePath(this.source, TextureUtils.getBasePath(str));
         if (this.startFadeIn >= 0 && this.endFadeIn >= 0 && this.endFadeOut >= 0) {
            int n = this.normalizeTime(this.endFadeIn - this.startFadeIn);
            if (this.startFadeOut < 0) {
               this.startFadeOut = this.normalizeTime(this.endFadeOut - n);
               if (this.timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn)) {
                  this.startFadeOut = this.endFadeIn;
               }
            }

            int n5 = n + this.normalizeTime(this.startFadeOut - this.endFadeIn) + this.normalizeTime(this.endFadeOut - this.startFadeOut) + this.normalizeTime(this.startFadeIn - this.endFadeOut);
            if (n5 != 24000) {
               Config.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + n5);
               return false;
            } else if (this.speed < 0.0F) {
               Config.warn("Invalid speed: " + this.speed);
               return false;
            } else if (this.daysLoop <= 0) {
               Config.warn("Invalid daysLoop: " + this.daysLoop);
               return false;
            } else {
               return true;
            }
         } else {
            Config.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
            return false;
         }
      }
   }
}
