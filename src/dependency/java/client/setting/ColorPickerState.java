package client.setting;

import client.font.FontFace;
import client.render.RenderUtils;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class ColorPickerState extends NumberSliderComponent {
   public double computeValueByFontFace(
      int count, int count2, int count3, int count4, int count5, int count6, double posX, double posY, double posZ, int count7, FontFace fontFace, Color color, float[] factorArray
   ) {
      double offset = posX;
      super.applyFontFaceByFontFace(count, count2, count5, count6, fontFace);
      int count8 = count3 - 2;
      int count9 = count + 2;
      super.count7 = count3;
      super.count9 = count4;

      for (int index = 0; index < count8; index++) {
         Color color2 = new Color(Color.getHSBColor(factorArray[0], factorArray[1], factorArray[2]).getRGB());
         RenderUtils.processAction(
            count9 + index,
            count2,
            count9 + 1 + index,
            count2 + count4,
            (float)color2.getRed() / 255.0F,
            (float)color2.getGreen() / 255.0F,
            (float)color2.getBlue() / 255.0F,
            (float)index / (float)count8,
            index == count8 - 1
         );
      }

      RenderUtils.processAction((double)count, (double)count2, (double)(count + count3), (double)(count2 + count4), 2.0F, color);
      if (super.enabled && RenderUtils.checkCondition((float)count, (float)count2, (float)count3, (float)count4, super.count6, super.count5)) {
         offset = posY + MathHelper.getDouble7((double)(count5 - count9) / (double)count8, 0.0, 1.0) * (posZ - posY);
      }

      double doubleItem = MathHelper.getDouble7((offset - posY) / (posZ - posY), 0.0, 1.0);
      double offset2 = (double)count9 + doubleItem * (double)(count8 - 2);
      RenderUtils.processDouble4(offset2 - 2.0, (double)(count2 + 1), offset2, (double)(count2 + count4 - 1), Color.black.getRGB());
      return MathHelper.getDouble7(offset, 0.0, 255.0);
   }
}
