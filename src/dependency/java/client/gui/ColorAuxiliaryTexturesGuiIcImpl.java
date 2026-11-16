package client.gui;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ColorAuxiliaryTexturesGuiIcImpl extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final Color color;

   @Override
   public void applyMinecraftByMinecraft(Minecraft mc, int count, int count2) {
      if (this.enabled2) {
         FontRenderer fontRendererObj = mc.fontRendererObj;
         mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.enabled = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         this.computeCount(this.enabled);
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.glStateAction3(770, 771);
         int count3 = (int)((float)(this.count4 + this.count));
         int count4 = (int)((float)(this.count5 + this.count3));
         double offset = -0.2;
         Color color2 = this.color;
         processAction(this.count, this.count3, count3, count4, this.computeCount2(color2, offset));
         this.applyMinecraft(mc, count, count2);
         int count5 = 14737632;
         if (!this.enabled3) {
            count5 = 10526880;
         } else if (this.enabled) {
            count5 = 16777120;
         }

         applyFontRenderer4(fontRendererObj, this.labelText, this.count + this.count4 / 2, this.count3 + (this.count5 - 8) / 2, count5);
      }
   }

   private int computeCount2(Color color2, double offset) {
      int count;
      if (this.enabled) {
         float[] hsbValues = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
         float factor = (float)((double)hsbValues[2] + offset);
         if ((double)hsbValues[2] + offset > 1.0) {
            factor = 1.0F;
         } else if ((double)hsbValues[2] + offset < 0.0) {
            factor = 0.0F;
         }

         count = Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor);
      } else {
         count = color2.getRGB();
      }

      return count;
   }

   public ColorAuxiliaryTexturesGuiIcImpl(int count, int count2, int count3, int count4, int count5, String text, Color color2) {
      super(count, count2, count3, count4, count5, text);
      this.color = color2;
   }
}
