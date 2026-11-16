package client.gui;

import client.Client;
import client.render.RenderUtils;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class ColorAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public String labelText2;
   public String labelText3;
   public final Color color;
   public boolean enabled5;
   public float factor2;

   public String getLabelText3() {
      return this.labelText3;
   }

   public void applyMinecraft(Minecraft mc, int count, byte byteValue, int count2) {
      if (this.enabled2) {
         FontRenderer fontRendererObj = mc.fontRendererObj;
         mc.getTextureManager().applyResourceLocation3(resourceLocation4);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.enabled = count >= this.count && count2 >= this.count3 && count < this.count + this.count4 && count2 < this.count3 + this.count5;
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.glStateAction3(770, 771);
         int count3 = this.count;
         int count4 = this.count3;
         int count5 = (int)((float)(this.count4 + this.count));
         int count6 = (int)((float)(this.count5 + this.count3));
         Color color2 = Client.getClient().getColor();
         double offset = -0.2;
         Color color3 = color2;
         processAction(count3, count4, count5, count6, this.computeCount2(color3, offset));
         this.applyMinecraft(mc, count, count2);
         int rgb = Color.gray.getRGB();
         if (!this.enabled5) {
            rgb = 14737632;
         }

         fontRendererObj.getIndex2(this.labelText, (float)(this.count + 5), (float)this.count3 - (float)fontRendererObj.indexOffset / 2.0F + (float)this.count5 / 2.0F, rgb);
         fontRendererObj.getIndex2(
            this.labelText2,
            (float)(this.count + this.count4 - 5 - fontRendererObj.getIndex(this.labelText2)),
            (float)this.count3 - 0.5F - (float)fontRendererObj.indexOffset + (float)this.count5 / 2.0F,
            rgb
         );
         fontRendererObj.getIndex2(
            this.labelText3,
            (float)(this.count + this.count4 - 5 - fontRendererObj.getIndex(this.labelText3)),
            (float)this.count3 + 0.5F + (float)this.count5 / 2.0F,
            rgb
         );
         RenderUtils.processAction((float)this.count, (float)this.count3, (float)(this.count + this.count4), (float)this.count3 - 0.5F, Color.black.getRGB());
         RenderUtils.processAction(
            (float)this.count,
            (float)(this.count3 + this.count5) - 0.5F,
            (float)(this.count + this.count4),
            (float)(this.count3 + this.count5),
            Color.black.getRGB()
         );
      }
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public ColorAuxiliaryTexturesGuiIc(int count, int count2, int count3, int count4, int count5, String text, String text2, String text3, Color color2) {
      super(count, count2, count3, count4, count5, text);
      this.color = color2;
      this.labelText2 = text2;
      this.labelText3 = text3;
   }

   public void setEnabled5(boolean enabled) {
      this.enabled5 = enabled;
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

   public void setLabelText2(String labelText) {
      this.labelText2 = labelText;
   }

   public void setLabelText3(String labelText) {
      this.labelText3 = labelText;
   }
}
