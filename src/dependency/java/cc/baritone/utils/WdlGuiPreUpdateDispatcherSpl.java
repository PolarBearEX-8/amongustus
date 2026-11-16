package cc.baritone.utils;

import client.gui.TexturesGuiIconsPngManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StatCollector;

public class WdlGuiPreUpdateDispatcherSpl extends PreUpdateDispatcherSplineCalculator {
   public final GuiDoneGuiScreen guiDoneGuiScreen;

   public WdlGuiPreUpdateDispatcherSpl(GuiDoneGuiScreen guiDoneGuiScreen2) {
      super(GuiDoneGuiScreen.resolveMinecraft(guiDoneGuiScreen2), guiDoneGuiScreen2.width, guiDoneGuiScreen2.height - GuiDoneGuiScreen.computeCount(guiDoneGuiScreen2), 19, 32);
      this.guiDoneGuiScreen = guiDoneGuiScreen2;
   }

   @Override
   public void applyIndex(int count, int count2, float factor) {
      GlStateManager.translate(0.0F, (float)GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen), 0.0F);
      this.count3 = this.guiDoneGuiScreen.height - GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen);
      this.count7 = this.count3 - 32;
      super.applyIndex(count, count2, factor);
      TexturesGuiIconsPngManager.applyFontRenderer4(
         GuiDoneGuiScreen.resolveFontRenderer(this.guiDoneGuiScreen),
         StatCollector.getText("wdl.gui.extensions.detailsCaption", new Object[0]),
         this.guiDoneGuiScreen.width / 2,
         5,
         16777215
      );
      GlStateManager.translate(0.0F, (float)(-GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen)), 0.0F);
   }

   @Override
   public void updateState() {
      this.count2 = this.count2 - GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen);
      if (this.count2 > 0) {
         super.updateState();
      }

      this.count2 = this.count2 + GuiDoneGuiScreen.computeCount(this.guiDoneGuiScreen);
   }
}
