package net.augustus.utils.render;

import net.minecraft.client.renderer.GlStateManager;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class GlStateSaver {
   private boolean savedRescaleNormal;
   private boolean savedBlend;
   private boolean savedFog;
   private boolean savedLineSmooth;
   private boolean savedDepth;
   private boolean savedColorLogic;
   private boolean savedNormalize;
   private boolean savedLighting;
   private int savedLightMask;
   private boolean savedAlpha;
   private boolean savedTexture2D;
   private boolean savedColorMaterial;
   private boolean savedCull;
   private boolean savedPolygonOffset;

   public void setCull(boolean cull) {
      this.savedCull = GlStateManager.isCullEnabled();
      if (cull) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }
   }

   public void setDepth(boolean depth) {
      this.savedDepth = GlStateManager.isDepthEnabled();
      if (depth) {
         GlStateManager.enableDepth();
      } else {
         GlStateManager.disableDepth();
      }
   }

   public void restoreBlend() {
      if (this.savedBlend) {
         GlStateManager.enableBlend();
      } else {
         GlStateManager.disableBlend();
      }
   }

   public void restorePolygonOffset() {
      if (this.savedPolygonOffset) {
         GlStateManager.enablePolygonOffset();
      } else {
         GlStateManager.disablePolygonOffset();
      }
   }

   public void restoreTexture2D() {
      if (this.savedTexture2D) {
         GlStateManager.enableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }
      } else {
         GlStateManager.disableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }
      }
   }

   public void restoreAlpha() {
      if (this.savedAlpha) {
         GlStateManager.enableAlpha();
      } else {
         GlStateManager.disableAlpha();
      }
   }

   public void setLighting(boolean lighting) {
      this.savedLighting = GlStateManager.isLightingEnabled();
      if (lighting) {
         GlStateManager.enableLighting();
      } else {
         GlStateManager.disableLighting();
      }
   }

   public void restoreCull() {
      if (this.savedCull) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }
   }

   public void setAlpha(boolean alpha) {
      this.savedAlpha = GlStateManager.isAlphaEnabled();
      if (alpha) {
         GlStateManager.enableAlpha();
      } else {
         GlStateManager.disableAlpha();
      }
   }

   public void setTexture2D(boolean texture2D) {
      this.savedTexture2D = GlStateManager.isTexture2DEnabled();
      if (texture2D) {
         GlStateManager.enableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.enableTexture2D();
         }
      } else {
         GlStateManager.disableTexture2D();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }
      }
   }

   public void restoreLighting() {
      if (this.savedLighting) {
         GlStateManager.enableLighting();
      } else {
         GlStateManager.disableLighting();
      }
   }

   private GlStateSaver() {
   }

   public void restoreDepth() {
      if (this.savedDepth) {
         GlStateManager.enableDepth();
      } else {
         GlStateManager.disableDepth();
      }
   }

   public void setFog(boolean fog) {
      this.savedFog = GlStateManager.isFogEnabled();
      if (fog) {
         GlStateManager.enableFog();
      } else {
         GlStateManager.disableFog();
      }
   }

   public void restoreFog() {
      if (this.savedFog) {
         GlStateManager.enableFog();
      } else {
         GlStateManager.disableFog();
      }
   }

   public void restoreNormalize() {
      if (this.savedNormalize) {
         GlStateManager.enableNormalize();
      } else {
         GlStateManager.disableNormalize();
      }
   }

   public void setPolygonOffset(boolean polygonOffset) {
      this.savedPolygonOffset = GlStateManager.isPolygonOffsetEnabled();
      if (polygonOffset) {
         GlStateManager.enablePolygonOffset();
      } else {
         GlStateManager.disablePolygonOffset();
      }
   }

   public void restoreColorMaterial() {
      if (this.savedColorMaterial) {
         GlStateManager.enableColorMaterial();
      } else {
         GlStateManager.disableColorMaterial();
      }
   }

   public void restoreLight(int n2) {
      if ((this.savedLightMask & 1 << n2) != 0) {
         GlStateManager.enableLight(n2);
      } else {
         GlStateManager.disableLight(n2);
      }
   }

   public void setNormalize(boolean normalize) {
      this.savedNormalize = GlStateManager.isNormalizeEnabled();
      if (normalize) {
         GlStateManager.enableNormalize();
      } else {
         GlStateManager.disableNormalize();
      }
   }

   public void setColorLogic(boolean colorLogic) {
      this.savedColorLogic = GlStateManager.isColorLogicEnabled();
      if (colorLogic) {
         GlStateManager.enableColorLogic();
      } else {
         GlStateManager.disableColorLogic();
      }
   }

   public void restoreColorLogic() {
      if (this.savedColorLogic) {
         GlStateManager.enableColorLogic();
      } else {
         GlStateManager.disableColorLogic();
      }
   }

   public void restoreLineSmooth() {
      if (this.savedLineSmooth) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }
   }

   public void setBlend(boolean blend) {
      this.savedBlend = GlStateManager.isBlendEnabled();
      if (blend) {
         GlStateManager.enableBlend();
      } else {
         GlStateManager.disableBlend();
      }
   }

   public void setLight(int n, boolean flag) {
      if (GlStateManager.isLightEnabled(n)) {
         this.savedLightMask |= 1 << n;
      } else {
         this.savedLightMask &= ~(1 << n);
      }

      if (flag) {
         GlStateManager.enableLight(n);
      } else {
         GlStateManager.disableLight(n);
      }
   }

   public void setRescaleNormal(boolean rescaleNormal) {
      this.savedRescaleNormal = GlStateManager.isRescaleNormalEnabled();
      if (rescaleNormal) {
         GlStateManager.enableRescaleNormal();
      } else {
         GlStateManager.disableRescaleNormal();
      }
   }

   public void restoreRescaleNormal() {
      if (this.savedRescaleNormal) {
         GlStateManager.enableRescaleNormal();
      } else {
         GlStateManager.disableRescaleNormal();
      }
   }

   public void setColorMaterial(boolean colorMaterial) {
      this.savedColorMaterial = GlStateManager.isColorMaterialEnabled();
      if (colorMaterial) {
         GlStateManager.enableColorMaterial();
      } else {
         GlStateManager.disableColorMaterial();
      }
   }

   public static GlStateSaver create() {
      return new GlStateSaver();
   }

   public void setLineSmooth(boolean lineSmooth) {
      this.savedLineSmooth = GL11.glIsEnabled(2848);
      if (lineSmooth) {
         GL11.glEnable(2848);
      } else {
         GL11.glDisable(2848);
      }
   }




}
