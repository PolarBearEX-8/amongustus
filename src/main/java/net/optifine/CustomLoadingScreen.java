package net.optifine;

import java.util.Properties;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;

public class CustomLoadingScreen {
   private ResourceLocation locationTexture;
   private int scaleMode = 0;
   private int scale = 2;
   private boolean center;
   private static final int SCALE_DEFAULT = 2;
   private static final int SCALE_MODE_FIXED = 0;
   private static final int SCALE_MODE_FULL = 1;
   private static final int SCALE_MODE_STRETCH = 2;

   private static int parseScaleMode(String str) {
      if (str == null) {
         return 0;
      } else {
         str = str.toLowerCase().trim();
         if (str.equals("fixed")) {
            return 0;
         } else if (str.equals("full")) {
            return 1;
         } else if (str.equals("stretch")) {
            return 2;
         } else {
            CustomLoadingScreens.warn("Invalid scale mode: " + str);
            return 0;
         }
      }
   }

   private static int parseScale(String str, int n) {
      if (str == null) {
         return n;
      } else {
         str = str.trim();
         int n2 = Config.parseInt(str, -1);
         if (n2 < 1) {
            CustomLoadingScreens.warn("Invalid scale: " + str);
            return n;
         } else {
            return n2;
         }
      }
   }

   private static String getProperty(String str, int n, Properties properties) {
      if (properties == null) {
         return null;
      } else {
         String property = properties.getProperty("dim" + n + "." + str);
         return property != null ? property : properties.getProperty(str);
      }
   }

   public static CustomLoadingScreen parseScreen(String str, int n, Properties properties) {
      ResourceLocation location = new ResourceLocation(str);
      int n2 = parseScaleMode(getProperty("scaleMode", n, properties));
      int n3 = n2 == 0 ? 2 : 1;
      int n4 = parseScale(getProperty("scale", n, properties), n3);
      boolean flag = Config.parseBoolean2(getProperty("center", n, properties), false);
      return new CustomLoadingScreen(location, n2, n4, flag);
   }

   public CustomLoadingScreen(ResourceLocation location, int n, int n2, boolean flag) {
      this.locationTexture = location;
      this.scaleMode = n;
      this.scale = n2;
      this.center = flag;
   }

   public void drawBackground(int n, int n2) {
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      Config.getTextureManager().bindTexture(this.locationTexture);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      double d = (double)(16 * this.scale);
      double d2 = (double)n / d;
      double d3 = (double)n2 / d;
      double d4 = 0.0;
      double d5 = 0.0;
      if (this.center) {
         d4 = (d - (double)n) / (d * 2.0);
         d5 = (d - (double)n2) / (d * 2.0);
      }

      switch (this.scaleMode) {
         case 1:
            d = (double)Math.max(n, n2);
            d2 = (double)(this.scale * n) / d;
            d3 = (double)(this.scale * n2) / d;
            if (this.center) {
               d4 = (double)this.scale * (d - (double)n) / (d * 2.0);
               d5 = (double)this.scale * (d - (double)n2) / (d * 2.0);
            }
            break;
         case 2:
            d2 = (double)this.scale;
            d3 = (double)this.scale;
            d4 = 0.0;
            d5 = 0.0;
      }

      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(0.0, (double)n2, 0.0).tex(d4, d5 + d3).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).tex(d4 + d2, d5 + d3).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)n, 0.0, 0.0).tex(d4 + d2, d5).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos(0.0, 0.0, 0.0).tex(d4, d5).color(255, 255, 255, 255).endVertex();
      instance.draw();
   }
}
