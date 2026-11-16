package client.setting;

import client.font.FontFace;
import client.font.FontFaceHolder;
import client.render.RenderUtils;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class NumberSliderComponent extends FontFaceHolder {
   public int count5;
   public int count6;
   public int count7;
   public boolean enabled = false;
   public int count9;

   public int getCount5() {
      return this.count5;
   }

   public double computeValueByFontFace(
      int count, int count2, int count3, int count4, int count8, int count10, double posX, double posY, double posZ, int count11, FontFace fontFace, Color color, Color color2
   ) {
      double offset = posX;
      super.applyFontFaceByFontFace(count, count2, count8, count10, fontFace);
      this.count7 = count3;
      this.count9 = count4;
      RenderUtils.processAction((double)count, (double)count2, (double)(count + count3), (double)(count2 + count4), 2.0F, color);
      if (posX == Double.MAX_VALUE) {
         offset = 0.0;
      }

      if (this.enabled && RenderUtils.checkCondition((float)count, (float)count2, (float)count3, (float)count4, this.count6, this.count5)) {
         offset = posY + MathHelper.getDouble7((double)(count8 - count) / (double)count3, 0.0, 1.0) * (posZ - posY);
      }

      offset = this.computeValue(offset, count11);
      double doubleItem = MathHelper.getDouble7((offset - posY) / (posZ - posY), 0.0, 1.0);
      double offset2 = (double)(count + 2) + doubleItem * (double)(count3 - 3);
      RenderUtils.processDouble4((double)(count + 2), (double)(count2 + 2), offset2, (double)(count2 + count4 - 2), color2.getRGB());
      return offset;
   }

   public int getCount7() {
      return this.count7;
   }

   @Override
   public void applyIndexByIndex(int count, int count2, int count3) throws IOException {
      this.count6 = count;
      this.count5 = count2;
   }

   private double computeValue(double offset, int count) {
      if (count == 0) {
         return (double)((int)offset);
      } else {
         double offset2 = Math.pow(10.0, (double)count);
         return (double)Math.round(offset * offset2) / offset2;
      }
   }

   public int getCount6() {
      return this.count6;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
      this.enabled = false;
      this.count6 = Integer.MAX_VALUE;
      this.count5 = Integer.MAX_VALUE;
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if (count == 1) {
         this.enabled = false;
      }
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public void updateState() {
      this.enabled = false;
      this.count6 = Integer.MAX_VALUE;
      this.count5 = Integer.MAX_VALUE;
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
      if (count3 == 0 && RenderUtils.checkCondition((float)super.count3, (float)super.count4, (float)this.count7, (float)this.count9, this.count6, this.count5)) {
         this.enabled = true;
      }
   }

   public int getCount9() {
      return this.count9;
   }

   public NumberSliderComponent() {
      this.count6 = 0;
      this.count5 = 0;
   }
}
