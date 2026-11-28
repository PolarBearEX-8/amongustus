package net.augustus.clickgui.components;

import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class ColorAlphaSliderComponent extends SliderComponent {

   public double drawAlphaSlider(
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
      float[] floats
   ) {
      super.updateLayout(n, n2, n5, n6, customFontUtil);
      int n9 = n3 - 1;
      int n10 = n + 1;
      this.sliderWidth = n3;
      this.sliderHeight = n4;

      for (int i = 0; i < n9; i++) {
         Color color = new Color(Color.getHSBColor(floats[0], floats[1], floats[2]).getRGB());
         int n11 = n10 + i;
         RenderUtil.drawBatchedRect(n11, n2, n10 + 1 + i, n2 + n4, (float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)i / (float)n9, i == n9 - 1);
      }

      RenderUtil.drawRectOutline((double)n, (double)n2, (double)(n + n3), (double)(n2 + n4), 2.0F, n8);
      if (this.dragging && RenderUtil.isHovered((float)n, (float)n2, (float)n3, (float)n4, this.clickX, this.clickY)) {
         d = d2 + MathHelper.clamp_double((double)(n5 - n10) / (double)n9, 0.0, 1.0) * (d3 - d2);
      }

      double d5 = (double)n10 + MathHelper.clamp_double((d - d2) / (d3 - d2), 0.0, 1.0) * (double)(n9 - 2);
      RenderUtil.drawRect(d5, (double)(n2 + 1), d5 + 2.0, (double)(n2 + n4 - 1), Color.black.getRGB());
      return MathHelper.clamp_double(d, 0.0, 255.0);
   }
}
