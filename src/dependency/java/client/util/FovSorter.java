package client.util;

import net.minecraft.client.renderer.GlStateManager;

public class FovSorter {
   public static final int count = 0;
   public static final int count2 = 1;
   public static final int count3 = 2;
   public static final int count4 = 3;
   public static final int count5 = 4;
   public static final int count6 = 5;
   public static final int count7 = 6;
   public static final int count8 = 7;
   public static final int count9 = 8;
   public static final int count10 = 1;

   public static int computeCount(String text) {
      if (text == null) {
         return 1;
      } else {
         String text2 = text.toLowerCase().trim();
         if (text2.equals("alpha")) {
            return 0;
         } else if (text2.equals("add")) {
            return 1;
         } else if (text2.equals("subtract")) {
            return 2;
         } else if (text2.equals("multiply")) {
            return 3;
         } else if (text2.equals("dodge")) {
            return 4;
         } else if (text2.equals("burn")) {
            return 5;
         } else if (text2.equals("screen")) {
            return 6;
         } else if (text2.equals("overlay")) {
            return 7;
         } else if (text2.equals("replace")) {
            return 8;
         } else {
            BuildManager.applyText3("Unknown blend: " + text2);
            return 1;
         }
      }
   }

   public static void applyIndex(int count11, float factor) {
      switch (count11) {
         case 0:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(770, 771);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor);
            break;
         case 1:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(770, 1);
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor);
            break;
         case 2:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(775, 0);
            GlStateManager.color4f2(factor, factor, factor, 1.0F);
            break;
         case 3:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(774, 771);
            GlStateManager.color4f2(factor, factor, factor, factor);
            break;
         case 4:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(1, 1);
            GlStateManager.color4f2(factor, factor, factor, 1.0F);
            break;
         case 5:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(0, 769);
            GlStateManager.color4f2(factor, factor, factor, 1.0F);
            break;
         case 6:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(1, 769);
            GlStateManager.color4f2(factor, factor, factor, 1.0F);
            break;
         case 7:
            GlStateManager.applyState10();
            GlStateManager.disableBlend();
            GlStateManager.glStateAction3(774, 768);
            GlStateManager.color4f2(factor, factor, factor, 1.0F);
            break;
         case 8:
            GlStateManager.applyState14();
            GlStateManager.enableBlend();
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor);
      }

      GlStateManager.disableTexture2D();
   }

   public static void applyFloat(float factor) {
      GlStateManager.applyState10();
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(770, 1);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor);
   }
}
