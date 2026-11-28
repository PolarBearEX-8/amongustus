package net.augustus.utils.render;

import java.awt.Color;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class ColorUtil {

   public static int getAlpha(int n) {
      return n >> 24 & 0xFF;
   }

   public static int getAnimatedFadeColor(Color color, Color color2, int n, int n2) {
      double d = (double)(System.currentTimeMillis() / (long)((int)Math.max(150.0F / ((float)n2 / 2.0F), 1.0F))) - (double)n / 3.0;
      d %= 360.0;
      float f = (float)d / 180.0F;
      if (f > 1.0F) {
         f = Math.abs(f - 2.0F);
      }

      return packRgba(interpolateChannel((double)f, color.getRed(), color2.getRed()), interpolateChannel((double)f, color.getGreen(), color2.getGreen()), interpolateChannel((double)f, color.getBlue(), color2.getBlue()), interpolateChannel((double)f, color.getAlpha(), color2.getAlpha()));
   }

   private static void validateRgbaChannels(int n, int n2, int n3, int n4) {
      if (n4 < 0 || n4 > 255 || n < 0 || n > 255 || n2 < 0 || n2 > 255 || n3 < 0 || n3 > 255) {
         throw new IllegalArgumentException("Color parameter outside of expected range:");
      }
   }

   public static float getGreenFloat(int n) {
      return (float)(n >> 8 & 0xFF) / 255.0F;
   }

   public static int getTeamColor(EntityPlayer player) {
      int n = 16777215;
      ScorePlayerTeam team = (ScorePlayerTeam)player.getTeam();
      if (team != null) {
         String formatFromString = FontRenderer.getFormatFromString(team.getColorPrefix());
         if (formatFromString.length() >= 2) {
            n = Minecraft.getMinecraft().fontRendererObj.getColorCode(formatFromString.charAt(1));
         }
      }

      return n;
   }

   public static int getAstolfoColor(int n, int n2) {
      return getAstolfoColor(n, n2, 255);
   }

   public static float[] toHsb(int n) {
      return Color.RGBtoHSB(getRed(n), getGreen(n), getBlue(n), null);
   }

   public static int getRainbowColor(int n, int n2, float f, float f2, int n3) {
      double d = (double)(System.currentTimeMillis() / (long)((int)Math.max(150.0F / ((float)n2 / 3.0F), 1.0F))) - (double)n / 3.0;
      d %= 360.0;
      int n4 = withFullAlpha(Color.HSBtoRGB((float)(d / 360.0), f, f2));
      return packRgba(getRed(n4), getGreen(n4), getBlue(n4), n3);
   }

   public static int[] getAstolfoRgbArray(double d) {
      double d2 = d % 360.0;
      int n = withFullAlpha(Color.HSBtoRGB((float)(d2 / 360.0 < 0.5 ? -(d2 / 360.0) : d2 / 360.0), 0.45882353F, 1.0F));
      return new int[]{getRed(n), getGreen(n), getBlue(n)};
   }

   public static int interpolateChannel(double d, int n, int n2) {
      return (int)Math.abs(d * (double)n + (1.0 - d) * (double)n2);
   }

   public static int packRgba(int n, int n2, int n3, int n4) {
      validateRgbaChannels(n, n2, n3, n4);
      return (n4 & 0xFF) << 24 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | n3 & 0xFF;
   }

   public static int withFullAlpha(int fullAlpha) {
      return 0xFF000000 | fullAlpha;
   }

   public static int getAstolfoColor(int n, int n2, int n3) {
      double d = (double)(System.currentTimeMillis() / (long)((int)Math.max(150.0F / ((float)n2 / 3.0F), 1.0F))) - (double)n / 3.0;
      d %= 360.0;
      int n4 = withFullAlpha(Color.HSBtoRGB((float)(d / 360.0 < 0.5 ? -(d / 360.0) : d / 360.0), 0.45882353F, 1.0F));
      return packRgba(getRed(n4), getGreen(n4), getBlue(n4), n3);
   }

   public static int getBlue(int n) {
      return n & 0xFF;
   }

   public static int applyAlphaMask(int n, int n2) {
      return n & 16777215 | (n2 & 0xFF) << 24;
   }

   public static int lerpRgb(int n, int n2, float f) {
      int n3 = n >> 16 & 0xFF;
      int n4 = n >> 8 & 0xFF;
      int n5 = n & 0xFF;
      int n6 = n2 >> 16 & 0xFF;
      int n7 = n2 >> 8 & 0xFF;
      int n8 = n2 & 0xFF;
      return 0xFF000000 | ((int)((float)n3 + f * (float)(n6 - n3))) << 16 | ((int)((float)n4 + f * (float)(n7 - n4))) << 8 | ((int)((float)n5 + f * (float)(n8 - n5)));
   }

   public static int withAlpha(int n, int n2) {
      return packRgba(getRed(n), getGreen(n), getBlue(n), n2);
   }

   public static float getAlphaFloat(int n) {
      return (float)(n >> 24 & 0xFF) / 255.0F;
   }

   public static int lerpRgba(int n, int n2, float f) {
      return packRgba(interpolateChannel((double)f, getRed(n), getRed(n2)), interpolateChannel((double)f, getGreen(n), getGreen(n2)), interpolateChannel((double)f, getBlue(n), getBlue(n2)), interpolateChannel((double)f, getAlpha(n), getAlpha(n2)));
   }

   public static int getRed(int n) {
      return n >> 16 & 0xFF;
   }

   public static float getBlueFloat(int n) {
      return (float)(n & 0xFF) / 255.0F;
   }

   public static int blendColors(Color color, Color color2, double d) {
      float f = (float)(1.0 - d);
      return packRgba((int)((double)((float)color.getRed() * f) + (double)color2.getRed() * d), (int)((double)((float)color.getGreen() * f) + (double)color2.getGreen() * d), (int)((double)((float)color.getBlue() * f) + (double)color2.getBlue() * d), (int)((double)((float)color.getAlpha() * f) + (double)color2.getAlpha() * d));
   }

   public static float getRedFloat(int n) {
      return (float)(n >> 16 & 0xFF) / 255.0F;
   }

   public static int packRgb(int n, int n2, int n3) {
      return packRgba(n, n2, n3, 255);
   }

   public static int getGreen(int n) {
      return n >> 8 & 0xFF;
   }

   public static int getRainbowColor(int n, int n2, float f, float f2) {
      return getRainbowColor(n, n2, f, f2, 255);
   }

   public static int resolvePassColor(ShaderRenderPass shaderRenderPass, int n) {
      return shaderRenderPass == ShaderRenderPass.SHADOW ? withAlpha(n, 255) : n;
   }
}
