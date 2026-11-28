package net.minecraft.client.gui;

import java.awt.Color;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class GuiSlider extends GuiButton {
   private float sliderPosition = 1.0F;
   public boolean isMouseDown;
   private String name;
   private final float min;
   private final float max;
   private final GuiPageButtonList.GuiResponder responder;
   private GuiSlider.FormatHelper formatHelper;

   private String getDisplayString() {
      return this.formatHelper == null
         ? I18n.format(this.name, new Object[0]) + ": " + this.getSliderValue()
         : this.formatHelper.getText(this.id, I18n.format(this.name, new Object[0]), this.getSliderValue());
   }

   public void setSliderValue(float f, boolean flag) {
      this.sliderPosition = (f - this.min) / (this.max - this.min);
      this.displayString = this.getDisplayString();
      if (flag) {
         this.responder.onTick(this.id, this.getSliderValue());
      }
   }

   public float getSliderPosition() {
      return this.sliderPosition;
   }

   @Override
   public void mouseReleased(int n, int n2) {
      this.isMouseDown = false;
   }

   @Override
   public int getHoverState(boolean flag) {
      return 0;
   }

   public GuiSlider(GuiPageButtonList.GuiResponder guiResponder, int n, int n2, int n3, String str, float f, float f2, float f3, GuiSlider.FormatHelper formatHelper2) {
      super(n, n2, n3, 150, 20, "", false);
      this.name = str;
      this.min = f;
      this.max = f2;
      this.sliderPosition = (f3 - f) / (f2 - f);
      this.formatHelper = formatHelper2;
      this.responder = guiResponder;
      this.displayString = this.getDisplayString();
   }

   @Override
   public boolean mousePressed(Minecraft mc, int n, int n2) {
      if (super.mousePressed(mc, n, n2)) {
         this.sliderPosition = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
         if (this.sliderPosition < 0.0F) {
            this.sliderPosition = 0.0F;
         }

         if (this.sliderPosition > 1.0F) {
            this.sliderPosition = 1.0F;
         }

         this.displayString = this.getDisplayString();
         this.responder.onTick(this.id, this.getSliderValue());
         this.isMouseDown = true;
         return true;
      } else {
         return false;
      }
   }

   public float getSliderValue() {
      return this.min + (this.max - this.min) * this.sliderPosition;
   }

   @Override
   public void mouseDragged(Minecraft mc, int n, int n2) {
      if (this.visible) {
         if (this.isMouseDown) {
            this.sliderPosition = (float)(n - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.sliderPosition < 0.0F) {
               this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F) {
               this.sliderPosition = 1.0F;
            }

            this.displayString = this.getDisplayString();
            this.responder.onTick(this.id, this.getSliderValue());
         }

         if (!((Module)ClientManagers.moduleManager.invisMode).isToggled() && !this.forceVanillaStyle) {
            int n3 = this.xPosition + (int)(this.sliderPosition * (float)(this.width - 8));
            int yPosition2 = this.yPosition;
            byte b = 8;
            byte b2 = 20;
            int n4 = this.darkenColor(Augustus.getInstance().getClientColor(), -0.3);
            if (ClientManagers.moduleManager.clientSettings.roundedButtons.getValue()) {
               RenderUtil.drawRoundedRect(
                  (float)n3,
                  (float)yPosition2,
                  (float)(n3 + b),
                  (float)(yPosition2 + b2),
                  (float)ClientManagers.moduleManager.clientSettings.roundFactor.getValue(),
                  1,
                  new Color(n4)
               );
               GlStateManager.enableBlend();
            } else {
               RenderUtil.drawRect(n3, yPosition2, n3 + b, yPosition2 + b2, n4);
            }
         } else {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
         }
      }
   }

   public void setSliderPosition(float f) {
      this.sliderPosition = f;
      this.displayString = this.getDisplayString();
      this.responder.onTick(this.id, this.getSliderValue());
   }



   public interface FormatHelper {
      String getText(int n, String str, float f);
   }
}
