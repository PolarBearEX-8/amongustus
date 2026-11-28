package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonLanguage extends GuiButton {
   public GuiButtonLanguage(int n, int n2, int n3) {
      super(n, n2, n3, 20, 20, "");
   }

   @Override
   public void drawButton(Minecraft mc, int n, int n2) {
      if (this.visible) {
         mc.getTextureManager().bindTexture(GuiButton.buttonTextures);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         boolean flag = n >= this.xPosition && n2 >= this.yPosition && n < this.xPosition + this.width && n2 < this.yPosition + this.height;
         int n3 = 106;
         if (flag) {
            n3 += this.height;
         }

         this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, n3, this.width, this.height);
      }
   }
}
