package net.augustus.clickgui.components;

import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class SliderComponent extends GuiComponent {
   public int sliderWidth;
   public boolean dragging = false;
   public int clickY;
   public int sliderHeight;
   public int clickX = 0;

   public void mouseClickMove(int mouseX2, int mouseY2, int clickedMouseButton, long timeSinceLastClick) {
      if (clickedMouseButton == 0 && RenderUtil.isHovered((float)this.x, (float)this.y, (float)this.sliderWidth, (float)this.sliderHeight, this.clickX, this.clickY)) {
         this.dragging = true;
      }
   }

   public int getClickX() {
      return this.clickX;
   }

   public double drawSlider(
      int n,
      int n2,
      int n3,
      int n4,
      int n5,
      int n6,
      double d,
      double d2,
      double d3,
      int n7,
      CustomFontUtil customFontUtil,
      int n8,
      int n9
   ) {
      super.updateLayout(n, n2, n5, n6, customFontUtil);
      this.sliderWidth = n3;
      this.sliderHeight = n4;
      RenderUtil.drawRectOutline((double)n, (double)n2, (double)(n + n3), (double)(n2 + n4), 2.0F, n8);
      if (d == 1.7976931348623157E308) {
         d = 0.0;
      }

      if (this.dragging && RenderUtil.isHovered((float)n, (float)n2, (float)n3, (float)n4, this.clickX, this.clickY)) {
         d = d2 + MathHelper.clamp_double((double)(n5 - n) / (double)n3, 0.0, 1.0) * (d3 - d2);
      }

      d = this.roundToDecimalPlaces(d, n7);
      RenderUtil.drawRect((double)(n + 2), (double)(n2 + 2), (double)(n + 2) + MathHelper.clamp_double((d - d2) / (d3 - d2), 0.0, 1.0) * (double)(n3 - 3), (double)(n2 + n4 - 2), n9);
      return d;
   }

   public void mouseClicked(int mouseX2, int mouseY2, int mouseButton) throws IOException {
      this.clickX = mouseX2;
      this.clickY = mouseY2;
   }

   public void setDragging(boolean flag) {
      this.dragging = flag;
   }

   public int getSliderHeight() {
      return this.sliderHeight;
   }

   public boolean isDragging() {
      return this.dragging;
   }

   public SliderComponent() {
      this.clickY = 0;
   }

   private double roundToDecimalPlaces(double d, int n) {
      if (n == 0) {
         return (double)((int)d);
      } else {
         double d2 = Math.pow(10.0, (double)n);
         return (double)Math.round(d * d2) / d2;
      }
   }

   public void mouseReleased(int mouseX2, int mouseY2, int state) {
      this.dragging = false;
      this.clickX = 2147483647;
      this.clickY = 2147483647;
   }

   public int getSliderWidth() {
      return this.sliderWidth;
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1) {
         this.dragging = false;
      }
   }

   public void onClose() {
      this.dragging = false;
      this.clickX = 2147483647;
      this.clickY = 2147483647;
   }

   public int getClickY() {
      return this.clickY;
   }



}
