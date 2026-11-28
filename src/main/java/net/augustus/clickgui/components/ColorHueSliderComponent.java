package net.augustus.clickgui.components;

import net.augustus.font.CustomFontUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class ColorHueSliderComponent extends SliderComponent {

   public double drawHueSlider(
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
      float[] floats,
      int n9
   ) {
      super.updateLayout(n, n2, n5, n6, customFontUtil);
      int n10 = n3 - 1;
      int n11 = n + 1;
      this.sliderWidth = n3;
      this.sliderHeight = n4;

      for (int i = 0; i < n10; i++) {
         int n12 = 0xFF000000 | Color.HSBtoRGB((float)i / (float)n10, floats[1], floats[2]);
         int n13 = n11 + i;
         RenderUtil.drawBatchedRect(n13, n2, n11 + 1 + i, n2 + n4, ColorUtil.getRedFloat(n12), ColorUtil.getGreenFloat(n12), ColorUtil.getBlueFloat(n12), (float)n9 / 255.0F, i == n10 - 1);
      }

      RenderUtil.drawRectOutline((double)n, (double)n2, (double)(n + n3), (double)(n2 + n4), 2.0F, n8);
      if (this.dragging && RenderUtil.isHovered((float)n, (float)n2, (float)n3, (float)n4, this.clickX, this.clickY)) {
         d = d2 + MathHelper.clamp_double((double)(n5 - n11) / (double)n10, 0.0, 1.0) * (d3 - d2);
      }

      double d5 = (double)n11 + MathHelper.clamp_double((d - d2) / (d3 - d2), 0.0, 0.9952153) * (double)(n10 - 2);
      RenderUtil.drawRect(d5, (double)(n2 + 1), d5 + 2.0, (double)(n2 + n4 - 1), Color.black.getRGB());
      return MathHelper.clamp_double(d, 0.0, 0.9952153);
   }
}
