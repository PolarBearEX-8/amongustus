package client.gui;

import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import client.util.ServiceStringEntry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class TexturesGuiIconsPngManager {
   public static final ResourceLocation resourceLocation;
   public static final ResourceLocation resourceLocation3;
   public static final ResourceLocation resourceLocation2;
   public float factor;

   public static void applyFontRenderer2(FontRenderer fontRenderer, String text, int count, int count2, int count3) {
      fontRenderer.computeCount(text, (float)(count - fontRenderer.getIndex(text) / 2), (float)count2, count3);
   }

   public void applyFontRenderer(FontRenderer fontRenderer, String text, int count, int count2, int count3) {
      fontRenderer.getIndex2(text, (float)count, (float)count2, count3);
   }

   public void applyIndex(int count, int count2, int count3, int count4) {
      int count5 = count;
      int count6 = count2;
      if (count2 < count) {
         count5 = count2;
         count6 = count;
      }

      processAction(count5, count3, count6 + 1, count3 + 1, count4);
   }

   public void applyFloat(float factor2, float factor3, int count, int count2, int count3, int count4) {
      float factor4 = 0.00390625F;
      float factor5 = 0.00390625F;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(factor2 + 0.0F), (double)(factor3 + (float)count4), (double)this.factor)
         .getWorldRenderer6((double)((float)(count + 0) * factor4), (double)((float)(count2 + count4) * factor5))
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 + (float)count3), (double)(factor3 + (float)count4), (double)this.factor)
         .getWorldRenderer6((double)((float)(count + count3) * factor4), (double)((float)(count2 + count4) * factor5))
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 + (float)count3), (double)(factor3 + 0.0F), (double)this.factor)
         .getWorldRenderer6((double)((float)(count + count3) * factor4), (double)((float)(count2 + 0) * factor5))
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor2 + 0.0F), (double)(factor3 + 0.0F), (double)this.factor)
         .getWorldRenderer6((double)((float)(count + 0) * factor4), (double)((float)(count2 + 0) * factor5))
         .updateState();
      tessellator.updateState();
   }

   public static void processAction(int count, int count2, float factor2, float factor3, int count3, int count4, int count5, int count6, float factor4, float factor5) {
      float factor6 = 1.0F / factor4;
      float factor7 = 1.0F / factor5;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)count, (double)(count2 + count6), 0.0)
         .getWorldRenderer6((double)(factor2 * factor6), (double)((factor3 + (float)count4) * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count5), (double)(count2 + count6), 0.0)
         .getWorldRenderer6((double)((factor2 + (float)count3) * factor6), (double)((factor3 + (float)count4) * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count5), (double)count2, 0.0)
         .getWorldRenderer6((double)((factor2 + (float)count3) * factor6), (double)(factor3 * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count2, 0.0).getWorldRenderer6((double)(factor2 * factor6), (double)(factor3 * factor7)).updateState();
      tessellator.updateState();
   }

   public static void processAction(int count, int count2, int count3, int count4, int count5) {
      int count6 = count;
      int count7 = count2;
      int count8 = count3;
      int count9 = count4;
      if (count < count3) {
         count6 = count3;
         count8 = count;
      }

      if (count2 < count4) {
         count7 = count4;
         count9 = count2;
      }

      float factor2 = (float)(count5 >> 24 & 0xFF) / 255.0F;
      float factor3 = (float)(count5 >> 16 & 0xFF) / 255.0F;
      float factor4 = (float)(count5 >> 8 & 0xFF) / 255.0F;
      float factor5 = (float)(count5 & 0xFF) / 255.0F;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(factor3, factor4, factor5, factor2);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)count6, (double)count9, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count8, (double)count9, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count8, (double)count7, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count7, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public void applyIndexByIndex(int count, int count2, int count3, int count4) {
      int count5 = count2;
      int count6 = count3;
      if (count3 < count2) {
         count5 = count3;
         count6 = count2;
      }

      processAction(count, count5 + 1, count + 1, count6, count4);
   }

   public static void applyFontRenderer4(FontRenderer fontRenderer, String text, int count, int count2, int count3) {
      fontRenderer.getIndex2(text, (float)(count - fontRenderer.getIndex(text) / 2), (float)count2, count3);
   }

   public void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      float factor2 = (float)(count5 >> 24 & 0xFF) / 255.0F;
      float factor3 = (float)(count5 >> 16 & 0xFF) / 255.0F;
      float factor4 = (float)(count5 >> 8 & 0xFF) / 255.0F;
      float factor5 = (float)(count5 & 0xFF) / 255.0F;
      float factor6 = (float)(count6 >> 24 & 0xFF) / 255.0F;
      float factor7 = (float)(count6 >> 16 & 0xFF) / 255.0F;
      float factor8 = (float)(count6 >> 8 & 0xFF) / 255.0F;
      float factor9 = (float)(count6 & 0xFF) / 255.0F;
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GlStateManager.applyState10();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.glShadeModel(7425);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer((double)count3, (double)count2, (double)this.factor).resolveWorldRenderer(factor3, factor4, factor5, factor2).updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count2, (double)this.factor).resolveWorldRenderer(factor3, factor4, factor5, factor2).updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count4, (double)this.factor).resolveWorldRenderer(factor7, factor8, factor9, factor6).updateState();
      worldRenderer.getWorldRenderer((double)count3, (double)count4, (double)this.factor).resolveWorldRenderer(factor7, factor8, factor9, factor6).updateState();
      tessellator.updateState();
      GlStateManager.glShadeModel(7424);
      GlStateManager.enableBlend();
      GlStateManager.applyState14();
      GlStateManager.disableTexture2D();
   }

   public void processAction(int count, int count2, int count3, int count4, int count5, int count6) {
      float factor2 = 0.00390625F;
      float factor3 = 0.00390625F;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + count6), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * factor2), (double)((float)(count4 + count6) * factor3))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count5), (double)(count2 + count6), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + count5) * factor2), (double)((float)(count4 + count6) * factor3))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count5), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + count5) * factor2), (double)((float)(count4 + 0) * factor3))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)((float)(count3 + 0) * factor2), (double)((float)(count4 + 0) * factor3))
         .updateState();
      tessellator.updateState();
   }

   public void applyServiceStringEntry(int count, int count2, ServiceStringEntry serviceStringEntry, int count3, int count4) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + count4), (double)this.factor)
         .getWorldRenderer6((double)serviceStringEntry.getFactor5(), (double)serviceStringEntry.getFactor6())
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)(count2 + count4), (double)this.factor)
         .getWorldRenderer6((double)serviceStringEntry.getFactor2(), (double)serviceStringEntry.getFactor6())
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)serviceStringEntry.getFactor2(), (double)serviceStringEntry.getFactor())
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + 0), (double)(count2 + 0), (double)this.factor)
         .getWorldRenderer6((double)serviceStringEntry.getFactor5(), (double)serviceStringEntry.getFactor())
         .updateState();
      tessellator.updateState();
   }

   public static void applyIndex(int count, int count2, float factor2, float factor3, int count3, int count4, float factor4, float factor5) {
      float factor6 = 1.0F / factor4;
      float factor7 = 1.0F / factor5;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)count, (double)(count2 + count4), 0.0)
         .getWorldRenderer6((double)(factor2 * factor6), (double)((factor3 + (float)count4) * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)(count2 + count4), 0.0)
         .getWorldRenderer6((double)((factor2 + (float)count3) * factor6), (double)((factor3 + (float)count4) * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)count2, 0.0)
         .getWorldRenderer6((double)((factor2 + (float)count3) * factor6), (double)(factor3 * factor7))
         .updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count2, 0.0).getWorldRenderer6((double)(factor2 * factor6), (double)(factor3 * factor7)).updateState();
      tessellator.updateState();
   }

   static {
      String[] textArray = new String[]{"textures/gui/container/stats_icons.png", "textures/gui/options_background.png", "textures/gui/icons.png"};
      resourceLocation = new ResourceLocation(textArray[1]);
      resourceLocation3 = new ResourceLocation(textArray[0]);
      resourceLocation2 = new ResourceLocation(textArray[2]);
   }
}
