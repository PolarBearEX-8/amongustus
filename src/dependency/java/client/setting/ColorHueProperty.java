package client.setting;

import client.font.FontFace;
import client.render.RenderUtils;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.util.MathHelper;

public class ColorHueProperty extends NumberSliderComponent {
   public double computeValue(
      int count,
      int count2,
      int count3,
      int count4,
      int count5,
      int count6,
      double posX,
      double posY,
      double posZ,
      int count7,
      FontFace fontFace,
      Color color,
      float[] factorArray,
      int count8
   ) {
      double offset = posX;
      super.applyFontFaceByFontFace(count, count2, count5, count6, fontFace);
      int count9 = count3 - 2;
      int count10 = count + 2;
      super.count7 = count3;
      super.count9 = count4;

      for (int index = 0; index < count9; index++) {
         int count11 = 0xFF000000 | Color.HSBtoRGB((float)index / (float)count9, factorArray[1], factorArray[2]);
         RenderUtils.processAction(
            count10 + index,
            count2,
            count10 + 1 + index,
            count2 + count4,
            RandomUtil.processAction5(count11),
            RandomUtil.processAction4(count11),
            RandomUtil.processAction7(count11),
            (float)count8 / 255.0F,
            index == count9 - 1
         );
      }

      RenderUtils.processAction((double)count, (double)count2, (double)(count + count3), (double)(count2 + count4), 2.0F, color);
      if (super.enabled && RenderUtils.checkCondition((float)count, (float)count2, (float)count3, (float)count4, super.count6, super.count5)) {
         offset = posY + MathHelper.getDouble7((double)(count5 - count10) / (double)count9, 0.0, 1.0) * (posZ - posY);
      }

      double doubleItem = MathHelper.getDouble7((offset - posY) / (posZ - posY), 0.0, 0.9952153);
      double offset2 = (double)count10 + doubleItem * (double)(count9 - 2);
      RenderUtils.processDouble4(offset2 - 2.0, (double)(count2 + 1), offset2, (double)(count2 + count4 - 1), Color.black.getRGB());
      return MathHelper.getDouble7(offset, 0.0, 0.9952153);
   }
}
