package wdl.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class ChunkOverrideModeButton extends GuiButton {
   public final ChunkOverrideMode mode;
   public final GuiWDLChunkOverrides parentGui;

   @Override
   public void drawButton(Minecraft mc, int n, int n2) {
      if (GuiWDLChunkOverrides.getMode(this.parentGui) == this.mode) {
         drawRect(this.xPosition - 2, this.yPosition - 2, this.xPosition + this.width + 2, this.yPosition + this.height + 2, -16744704);
      }

      super.drawButton(mc, n, n2);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      mc.getTextureManager().bindTexture(GuiWDLChunkOverrides.getPermissionWidgetsTexture());
      this.drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, this.mode.overlayU, this.mode.overlayV, 16, 16);
   }

   public ChunkOverrideModeButton(GuiWDLChunkOverrides guiWDLChunkOverrides, int n, int n2, int n3, ChunkOverrideMode chunkOverrideMode) {
      super(n, n2, n3, 20, 20, "");
      this.parentGui = guiWDLChunkOverrides;
      this.mode = chunkOverrideMode;
   }
}
