package cc.baritone.utils;

import client.gui.InternalTexturesGuiIconsPngManagerAdapter;
import client.gui.TexturesGuiIconsPngManager;
import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CouldnOpenHelper {
   public static final Minecraft mc = Minecraft.getMinecraft();
   public static final Logger logger = LogManager.getLogger();

   public static void applyIndex(int count, int count2, int count3, int count4) {
      GlStateManager.applyState3();
      GlStateManager.applyState20();
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      float factor = 32.0F;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(0.0, (double)count3, 0.0)
         .getWorldRenderer6((double)(0.0F / factor), (double)((float)count3 / factor))
         .resolveWorldRenderer(32, 32, 32, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)count3, 0.0)
         .getWorldRenderer6((double)((float)count4 / factor), (double)((float)count3 / factor))
         .resolveWorldRenderer(32, 32, 32, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)count, 0.0)
         .getWorldRenderer6((double)((float)count4 / factor), (double)((float)count / factor))
         .resolveWorldRenderer(32, 32, 32, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count2, (double)count, 0.0)
         .getWorldRenderer6((double)((float)count2 / factor), (double)((float)count / factor))
         .resolveWorldRenderer(32, 32, 32, 255)
         .updateState();
      tessellator.updateState();
   }

   public static void applyTextByText(String text, int count, int count2, int count3) {
      mc.fontRendererObj.getIndex2(text, (float)count, (float)count2, count3);
   }

   public static boolean matchesInternalTexturesGuiIconsPn(int count, int count2, InternalTexturesGuiIconsPngManagerAdapter internalTexturesGuiIconsPngManagerAdapter) {
      int count3 = count - internalTexturesGuiIconsPngManagerAdapter.count7;
      int count4 = count2 - internalTexturesGuiIconsPngManagerAdapter.count10;
      return count3 >= 0 && count3 < internalTexturesGuiIconsPngManagerAdapter.getCount() && count4 >= 0 && count4 < 20;
   }

   public static void applyText(String text) {
      try {
         Desktop.getDesktop().browse(new URI(text));
      } catch (Throwable throwable) {
         logger.error("Couldn't open link", throwable);
      }
   }

   public static void applyIndexByIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      GlStateManager.applyState3();
      GlStateManager.applyState20();
      GlStateManager.enableDepth();
      mc.getTextureManager().applyResourceLocation3(TexturesGuiIconsPngManager.resourceLocation);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      float factor = 32.0F;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      int count7 = count3 + count;
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)count4, (double)count7, 0.0)
         .getWorldRenderer6(0.0, (double)((float)count7 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count7, 0.0)
         .getWorldRenderer6((double)((float)count6 / factor), (double)((float)count7 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count3, 0.0)
         .getWorldRenderer6((double)((float)count6 / factor), (double)((float)count3 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)count3, 0.0)
         .getWorldRenderer6(0.0, (double)((float)count3 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      tessellator.updateState();
      int count8 = count5 - count2;
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)count4, (double)count5, 0.0)
         .getWorldRenderer6(0.0, (double)((float)count5 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count5, 0.0)
         .getWorldRenderer6((double)((float)count6 / factor), (double)((float)count5 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count8, 0.0)
         .getWorldRenderer6((double)((float)count6 / factor), (double)((float)count8 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)count8, 0.0)
         .getWorldRenderer6(0.0, (double)((float)count8 / factor))
         .resolveWorldRenderer(64, 64, 64, 255)
         .updateState();
      tessellator.updateState();
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
      GlStateManager.applyState10();
      GlStateManager.glShadeModel(7425);
      GlStateManager.enableTexture2D();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)count4, (double)(count7 + 4), 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(0, 0, 0, 0).updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)(count7 + 4), 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(0, 0, 0, 0).updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count7, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)count7, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
      tessellator.updateState();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)count4, (double)count8, 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)count8, 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(0, 0, 0, 255).updateState();
      worldRenderer.getWorldRenderer((double)count6, (double)(count8 - 4), 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(0, 0, 0, 0).updateState();
      worldRenderer.getWorldRenderer((double)count4, (double)(count8 - 4), 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(0, 0, 0, 0).updateState();
      tessellator.updateState();
      GlStateManager.disableTexture2D();
      GlStateManager.glShadeModel(7424);
      GlStateManager.applyState14();
      GlStateManager.enableBlend();
   }

   public static void applyIndex(int count, int count2, int count3, int count4, int count5, int count6) {
      applyIndex(count3, count4, count5, count6);
      applyIndexByIndex(count, count2, count3, count4, count5, count6);
   }

   public static List resolveList(String text, int count) {
      String text2 = text.replace("\r", "");
      return mc.fontRendererObj.getList(text2, count);
   }

   public static void applyText(String text, int count, int count2, int count3) {
      applyText(text, 300, 100, count, count2, count3);
   }

   public static void applyText(String text, int count, int count2, int count3, int count4, int count5) {
      if (text != null) {
         int count6 = count3 / 2 - count / 2;
         int count7 = count4 - count5 - count2;
         int count8 = count7 + 5;
         GuiScreen.processAction(count6, count7, count6 + count, count7 + count2, 2130706432);

         for (String text2 : (Iterable<String>)(Iterable<?>)resolveList(text, count - 10)) {
            mc.fontRendererObj.getIndex(text2, count6 + 5, count8, 16777215);
            count8 += mc.fontRendererObj.indexOffset;
         }
      }
   }
}
