package client.util;

import client.setting.MovementConfigProperty;
import java.awt.image.BufferedImage;

public class NativeFloatEntry extends PrimaryFloatEntry {
   public int count3;
   public int count2;
   public float factor8;
   public BufferedImage bufferedImage;
   public int count4;
   public String labelText2;
   public String labelText = "";

   public int getCount2() {
      return this.count2;
   }

   public int getCount3() {
      return this.count3;
   }

   public void setLabelText(String labelText3) {
      this.labelText = labelText3;
   }

   public BufferedImage getBufferedImage() {
      return this.bufferedImage;
   }

   public void setBufferedImage(BufferedImage bufferedImage2) {
      this.bufferedImage = bufferedImage2;
   }

   public boolean isActiveAsFlag() {
      return this.count3 != -1;
   }

   public void setCount4(int count) {
      this.count4 = count;
   }

   public void setLabelText2(String labelText3) {
      this.labelText2 = labelText3;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public int getCount4() {
      return this.count4;
   }

   public void setCount3(int count) {
      this.count3 = count;
   }

   public void setCount2(int count) {
      this.count2 = count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setFactor8(float factor) {
      this.factor8 = factor;
   }

   public float getFactor8() {
      return this.factor8;
   }

   public NativeFloatEntry(String text, int count) {
      super(MovementConfigProperty.FREE);
      this.labelText2 = "";
      this.count4 = -1;
      this.factor8 = 1.0F;
      this.count3 = -1;
      this.labelText = text;
      this.count2 = count;
   }
}
