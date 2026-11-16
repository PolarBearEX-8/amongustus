package client.util;

import client.render.WorldRenderer;
import java.util.Properties;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class InterpolationChecker {
   public ResourceLocation resourceLocation;
   public int count = 0;
   public int count2 = 2;
   public boolean enabled;
   public static final int count3 = 2;
   public static final int count4 = 0;
   public static final int count5 = 1;
   public static final int count6 = 2;

   public InterpolationChecker(ResourceLocation location, int count7, int count8, boolean flag) {
      this.resourceLocation = location;
      this.count = count7;
      this.count2 = count8;
      this.enabled = flag;
   }

   private static int computeCount(String text, int count7) {
      if (text == null) {
         return count7;
      } else {
         String text2 = text.trim();
         int count8 = BuildManager.computeCount(text2, -1);
         if (count8 < 1) {
            PngManager.applyTextByText("Invalid scale: " + text2);
            return count7;
         } else {
            return count8;
         }
      }
   }

   public static InterpolationChecker resolveInterpolationChecker(String text, int count7, Properties properties) {
      ResourceLocation location = new ResourceLocation(text);
      int count8 = computeCount(resolveText("scaleMode", count7, properties));
      int count9 = count8 == 0 ? 2 : 1;
      int count10 = computeCount(resolveText("scale", count7, properties), count9);
      boolean flag = BuildManager.matchesText(resolveText("center", count7, properties), false);
      return new InterpolationChecker(location, count8, count10, flag);
   }

   private static int computeCount(String text) {
      if (text == null) {
         return 0;
      } else {
         String text2 = text.toLowerCase().trim();
         if (text2.equals("fixed")) {
            return 0;
         } else if (text2.equals("full")) {
            return 1;
         } else if (text2.equals("stretch")) {
            return 2;
         } else {
            PngManager.applyTextByText("Invalid scale mode: " + text2);
            return 0;
         }
      }
   }

   private static String resolveText(String text, int count7, Properties properties) {
      if (properties == null) {
         return null;
      } else {
         String property = properties.getProperty("dim" + count7 + "." + text);
         return property != null ? property : properties.getProperty(text);
      }
   }

   public void applyIndex(int count7, int count8) {
      GlStateManager.applyState3();
      GlStateManager.applyState20();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      BuildManager.getTextureManagerAsTextureManager().applyResourceLocation3(this.resourceLocation);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      double offset = (double)(16 * this.count2);
      double offset2 = (double)count7 / offset;
      double offset3 = (double)count8 / offset;
      double offset4 = 0.0;
      double offset5 = 0.0;
      if (this.enabled) {
         offset4 = (offset - (double)count7) / (offset * 2.0);
         offset5 = (offset - (double)count8) / (offset * 2.0);
      }

      switch (this.count) {
         case 1:
            double offset6 = (double)Math.max(count7, count8);
            offset2 = (double)(this.count2 * count7) / offset6;
            offset3 = (double)(this.count2 * count8) / offset6;
            if (this.enabled) {
               offset4 = (double)this.count2 * (offset6 - (double)count7) / (offset6 * 2.0);
               offset5 = (double)this.count2 * (offset6 - (double)count8) / (offset6 * 2.0);
            }
            break;
         case 2:
            offset2 = (double)this.count2;
            offset3 = (double)this.count2;
            offset4 = 0.0;
            offset5 = 0.0;
      }

      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(0.0, (double)count8, 0.0).getWorldRenderer6(offset4, offset5 + offset3).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer((double)count7, (double)count8, 0.0)
         .getWorldRenderer6(offset4 + offset2, offset5 + offset3)
         .resolveWorldRenderer(255, 255, 255, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count7, 0.0, 0.0).getWorldRenderer6(offset4 + offset2, offset5).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(offset4, offset5).resolveWorldRenderer(255, 255, 255, 255).updateState();
      tessellator.updateState();
   }
}
