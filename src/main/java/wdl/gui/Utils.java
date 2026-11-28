package wdl.gui;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
   private static final Minecraft mc = Minecraft.getMinecraft();
   private static final Logger LOGGER = LogManager.getLogger();

   public static void drawGuiInfoBox(String str, int n, int n2, int n3, int n4, int n5) {
      if (str != null) {
         int n6 = n3 / 2 - n / 2;
         int n7 = n4 - n5 - n2;
         int n8 = n7 + 5;
         GuiScreen.drawRect(n6, n7, n6 + n, n7 + n2, 2130706432);

         for (String str2 : (Iterable<String>)(wordWrap(str, n - 10))) {
            mc.fontRendererObj.drawString(str2, n6 + 5, n8, 16777215);
            n8 += mc.fontRendererObj.FONT_HEIGHT;
         }
      }
   }

   public static void drawDarkBackground(int n, int n2, int n3, int n4) {
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f = 32.0F;
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos(0.0, (double)n3, 0.0).tex((double)(0.0F / f), (double)((float)n3 / f)).color(32, 32, 32, 255).endVertex();
      worldRenderer.pos((double)n4, (double)n3, 0.0).tex((double)((float)n4 / f), (double)((float)n3 / f)).color(32, 32, 32, 255).endVertex();
      worldRenderer.pos((double)n4, (double)n, 0.0).tex((double)((float)n4 / f), (double)((float)n / f)).color(32, 32, 32, 255).endVertex();
      worldRenderer.pos((double)n2, (double)n, 0.0).tex((double)((float)n2 / f), (double)((float)n / f)).color(32, 32, 32, 255).endVertex();
      instance.draw();
   }

   public static void drawStringWithShadow(String str, int n, int n2, int n3) {
      mc.fontRendererObj.drawStringWithShadow(str, (float)n, (float)n2, n3);
   }

   public static boolean isMouseOverTextBox(int n, int n2, GuiTextField guiTextField) {
      int n3 = n - guiTextField.xPosition;
      int n4 = n2 - guiTextField.yPosition;
      return n3 >= 0 && n3 < guiTextField.getWidth() && n4 >= 0 && n4 < 20;
   }

   public static List wordWrap(String str, int n) {
      str = str.replace("\r", "");
      return mc.fontRendererObj.listFormattedStringToWidth(str, n);
   }

   public static void drawListBackground(int n, int n2, int n3, int n4, int n5, int n6) {
      drawDarkBackground(n3, n4, n5, n6);
      drawBorder(n, n2, n3, n4, n5, n6);
   }

   public static void drawBorder(int n, int n2, int n3, int n4, int n5, int n6) {
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      GlStateManager.disableDepth();
      byte b = 4;
      mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f = 32.0F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      int n7 = n3 + n;
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)n4, (double)n7, 0.0).tex(0.0, (double)((float)n7 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n6, (double)n7, 0.0).tex((double)((float)n6 / f), (double)((float)n7 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n6, (double)n3, 0.0).tex((double)((float)n6 / f), (double)((float)n3 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n4, (double)n3, 0.0).tex(0.0, (double)((float)n3 / f)).color(64, 64, 64, 255).endVertex();
      instance.draw();
      int n8 = n5 - n2;
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)n4, (double)n5, 0.0).tex(0.0, (double)((float)n5 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n6, (double)n5, 0.0).tex((double)((float)n6 / f), (double)((float)n5 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n6, (double)n8, 0.0).tex((double)((float)n6 / f), (double)((float)n8 / f)).color(64, 64, 64, 255).endVertex();
      worldRenderer.pos((double)n4, (double)n8, 0.0).tex(0.0, (double)((float)n8 / f)).color(64, 64, 64, 255).endVertex();
      instance.draw();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
      GlStateManager.disableAlpha();
      GlStateManager.shadeModel(7425);
      GlStateManager.disableTexture2D();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)n4, (double)(n7 + b), 0.0).tex(0.0, 1.0).color(0, 0, 0, 0).endVertex();
      worldRenderer.pos((double)n6, (double)(n7 + b), 0.0).tex(1.0, 1.0).color(0, 0, 0, 0).endVertex();
      worldRenderer.pos((double)n6, (double)n7, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
      worldRenderer.pos((double)n4, (double)n7, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
      instance.draw();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      worldRenderer.pos((double)n4, (double)n8, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
      worldRenderer.pos((double)n6, (double)n8, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
      worldRenderer.pos((double)n6, (double)(n8 - b), 0.0).tex(1.0, 0.0).color(0, 0, 0, 0).endVertex();
      worldRenderer.pos((double)n4, (double)(n8 - b), 0.0).tex(0.0, 0.0).color(0, 0, 0, 0).endVertex();
      instance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
   }

   public static void drawGuiInfoBox(String str, int n, int n2, int n3) {
      drawGuiInfoBox(str, 300, 100, n, n2, n3);
   }

   public static void openLink(String str) {
      try {
         Desktop.getDesktop().browse(new URI(str));
      } catch (Throwable e) {
         LOGGER.error("Couldn't open link", e);
      }
   }
}
