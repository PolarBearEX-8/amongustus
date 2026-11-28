package wdl.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;

public class GuiSlider extends GuiButton {
   private float sliderValue;
   private boolean dragging;
   private final String text;
   private final int max;

   public void setValue(int n) {
      this.sliderValue = (float)n / (float)this.max;
      this.displayString = I18n.format(this.text, new Object[]{this.getValue()});
   }

   public GuiSlider(int n, int n2, int n3, int n4, int n5, String str, int n6, int n7) {
      super(n, n2, n3, n4, n5, str);
      this.text = str;
      this.max = n7;
      this.setValue(n6);
   }

   @Override
   public void mouseDragged(Minecraft mc, int n, int n2) {
      if (this.visible) {
         if (this.dragging) {
            this.sliderValue = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
            this.dragging = true;
            this.displayString = I18n.format(this.text, new Object[]{this.getValue()});
         }

         mc.getTextureManager().bindTexture(buttonTextures);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (this.enabled) {
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
         } else {
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 46, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 46, 4, 20);
         }
      }
   }

   public int getValue() {
      return (int)(this.sliderValue * (float)this.max);
   }

   @Override
   public int getHoverState(boolean flag) {
      return 0;
   }

   @Override
   public void mouseReleased(int n, int n2) {
      this.dragging = false;
   }

   @Override
   public boolean mousePressed(Minecraft mc, int n, int n2) {
      if (super.mousePressed(mc, n, n2)) {
         this.sliderValue = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
         this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
         this.displayString = I18n.format(this.text, new Object[]{this.getValue()});
         this.dragging = true;
         return true;
      } else {
         return false;
      }
   }
}
