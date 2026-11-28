package wdl.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class GuiWDLExtensionsModDetailList extends TextList {
   public final GuiWDLExtensions parentGui;

   @Override
   public void drawScreen(int mouseX2, int mouseY2, float partialTicks) {
      GlStateManager.translate(0.0F, (float)GuiWDLExtensions.getBottomLocation(this.parentGui), 0.0F);
      this.height = this.parentGui.height - GuiWDLExtensions.getBottomLocation(this.parentGui);
      this.bottom = this.height - 32;
      super.drawScreen(mouseX2, mouseY2, partialTicks);
      Gui.drawCenteredString(GuiWDLExtensions.getFontRendererForDetailsCaption(this.parentGui), I18n.format("wdl.gui.extensions.detailsCaption", new Object[0]), this.parentGui.width / 2, 5, 16777215);
      GlStateManager.translate(0.0F, (float)(-GuiWDLExtensions.getBottomLocation(this.parentGui)), 0.0F);
   }

   public GuiWDLExtensionsModDetailList(GuiWDLExtensions guiWDLExtensions) {
       super(GuiWDLExtensions.getMinecraftForDetailList(guiWDLExtensions), guiWDLExtensions.width, guiWDLExtensions.height - GuiWDLExtensions.getBottomLocation(guiWDLExtensions), 19, 32);
       this.parentGui = guiWDLExtensions;
   }

   @Override
   public void handleMouseInput() {
      this.mouseY = this.mouseY - GuiWDLExtensions.getBottomLocation(this.parentGui);
      if (this.mouseY > 0) {
         super.handleMouseInput();
      }

      this.mouseY = this.mouseY + GuiWDLExtensions.getBottomLocation(this.parentGui);
   }
}
