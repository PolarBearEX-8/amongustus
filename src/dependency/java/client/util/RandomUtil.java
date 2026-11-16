package client.util;

import client.render.RenderPass;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class RandomUtil {
   public static int getIndex(Color color, Color color2, double offset) {
      float factor = (float)(1.0 - offset);
      int count = (int)((double)((float)color.getRed() * factor) + (double)color2.getRed() * offset);
      int count2 = (int)((double)((float)color.getGreen() * factor) + (double)color2.getGreen() * offset);
      int count3 = (int)((double)((float)color.getBlue() * factor) + (double)color2.getBlue() * offset);
      int count4 = (int)((double)((float)color.getAlpha() * factor) + (double)color2.getAlpha() * offset);
      return getInt12(count, count2, count3, count4);
   }

   private static void applyIndex(int count, int count2, int count3, int count4) {
      if (count4 < 0 || count4 > 255 || count < 0 || count > 255 || count2 < 0 || count2 > 255 || count3 < 0 || count3 > 255) {
         throw new IllegalArgumentException("Color parameter outside of expected range:");
      }
   }

   public static int[] getIntArray(double offset) {
      double offset2 = offset % 360.0;
      int count = processAction8(Color.HSBtoRGB((float)(offset2 / 360.0 < 0.5 ? -(offset2 / 360.0) : offset2 / 360.0), 0.45882353F, 1.0F));
      return new int[]{processAction9(count), processAction6(count), processAction(count)};
   }

   public static int processAction(int count) {
      return count & 0xFF;
   }

   public static float processAction2(int count) {
      return (float)(count >> 24 & 0xFF) / 255.0F;
   }

   public static int getIndex(EntityPlayer player) {
      int count = 16777215;
      ScorePlayerTeam secondaryClientManager = (ScorePlayerTeam)player.getSecondaryClientManager();
      if (secondaryClientManager != null) {
         String text = FontRenderer.resolveTextByText(secondaryClientManager.getText());
         if (text.length() >= 2) {
            count = Minecraft.getMinecraft().fontRendererObj.computeCountByChar(text.charAt(1));
         }
      }

      return count;
   }

   public static int processAction3(int count) {
      return count >> 24 & 0xFF;
   }

   public static int processAction(double offset, int count, int count2) {
      return (int)Math.abs(offset * (double)count + (1.0 - offset) * (double)count2);
   }

   public static int computeCount(int count, int count2, int count3) {
      return getInt12(count, count2, count3, 255);
   }

   public static float processAction4(int count) {
      return (float)(count >> 8 & 0xFF) / 255.0F;
   }

   public static int getIndex(RenderPass renderPass, int count) {
      return renderPass == RenderPass.SHADOW ? getInt13(count, 255) : count;
   }

   public static int computeCount(Color color, Color color2, int count, int count2) {
      double offset = ((double)System.currentTimeMillis() - (double)count * 1.5) / (double)((int)Math.max(150.0F / ((float)count2 / 3.0F), 1.0F));
      float factor = (float)(offset % 360.0) / 180.0F;
      if (factor > 1.0F) {
         factor = Math.abs(factor - 2.0F);
      }

      int count3 = processAction((double)factor, color.getRed(), color2.getRed());
      int count4 = processAction((double)factor, color.getGreen(), color2.getGreen());
      int count5 = processAction((double)factor, color.getBlue(), color2.getBlue());
      int count6 = processAction((double)factor, color.getAlpha(), color2.getAlpha());
      return getInt12(count3, count4, count5, count6);
   }

   public static int getInt6(int count, int count2, float factor, float factor2) {
      return computeCount(count, count2, factor, factor2, 255);
   }

   public static float[] getFloatArray(int count) {
      return Color.RGBtoHSB(processAction9(count), processAction(count), processAction3(count), null);
   }

   public static float processAction5(int count) {
      return (float)(count >> 16 & 0xFF) / 255.0F;
   }

   public static int processAction(int count, int count2, float factor) {
      int count3 = count >> 16 & 0xFF;
      int count4 = count >> 8 & 0xFF;
      int count5 = count & 0xFF;
      int count6 = count2 >> 16 & 0xFF;
      int count7 = count2 >> 8 & 0xFF;
      int count8 = count2 & 0xFF;
      int count9 = (int)((float)count3 + factor * (float)(count6 - count3));
      int count10 = (int)((float)count4 + factor * (float)(count7 - count4));
      int count11 = (int)((float)count5 + factor * (float)(count8 - count5));
      return 0xFF000000 | count9 << 16 | count10 << 8 | count11;
   }

   public static int processAction6(int count) {
      return count >> 8 & 0xFF;
   }

   public static int computeCount(int count, int count2, float factor, float factor2, int count3) {
      int count4 = processAction8(
         Color.HSBtoRGB(
            (float)((double)((System.currentTimeMillis() - (long)count) / (long)((int)Math.max(150.0F / ((float)count2 / 5.0F), 1.0F))) % 360.0 / 360.0),
            factor,
            factor2
         )
      );
      return getInt12(processAction9(count4), processAction6(count4), processAction(count4), count3);
   }

   public static int computeCount(int count, int count2) {
      return computeCountByIndex(count, count2, 255);
   }

   public static float processAction7(int count) {
      return (float)(count & 0xFF) / 255.0F;
   }

   public static int computeCount(int count, int count2, float factor) {
      int count3 = processAction((double)factor, processAction9(count), processAction9(count2));
      int count4 = processAction((double)factor, processAction6(count), processAction6(count2));
      int count5 = processAction((double)factor, processAction(count), processAction(count2));
      int count6 = processAction((double)factor, processAction3(count), processAction3(count2));
      return getInt12(count3, count4, count5, count6);
   }

   public static int getInt12(int count, int count2, int count3, int count4) {
      applyIndex(count, count2, count3, count4);
      return (count4 & 0xFF) << 24 | (count & 0xFF) << 16 | (count2 & 0xFF) << 8 | count3 & 0xFF;
   }

   public static int getInt13(int count, int count2) {
      return getInt12(processAction9(count), processAction6(count), processAction(count), count2);
   }

   public static int processAction8(int count) {
      return 0xFF000000 | count;
   }

   public static int processAction9(int count) {
      return count >> 16 & 0xFF;
   }

   public static int computeCountByIndex(int count, int count2, int count3) {
      double offset = (double)((System.currentTimeMillis() - (long)count) / (long)((int)Math.max(150.0F / ((float)count2 / 5.0F), 1.0F)));
      double offset2 = offset % 360.0;
      int count4 = processAction8(Color.HSBtoRGB((float)(offset2 / 360.0 < 0.5 ? -(offset2 / 360.0) : offset2 / 360.0), 0.45882353F, 1.0F));
      return getInt12(processAction9(count4), processAction6(count4), processAction(count4), count3);
   }
}
