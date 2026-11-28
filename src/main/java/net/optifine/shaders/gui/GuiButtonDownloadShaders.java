package net.optifine.shaders.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDownloadShaders extends GuiButton {
   @Override
   public void drawButton(Minecraft mc, int n, int n2) {
      if (this.visible) {
         super.drawButton(mc, n, n2);
         ResourceLocation location = new ResourceLocation("optifine/textures/icons.png");
         mc.getTextureManager().bindTexture(location);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.xPosition + 3, this.yPosition + 2, 0, 0, 16, 16);
      }
   }

   public GuiButtonDownloadShaders(int n, int n2, int n3) {
      super(n, n2, n3, 22, 20, "");
   }
}
