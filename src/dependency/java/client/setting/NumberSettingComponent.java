package client.setting;

import client.font.FontFace;
import client.font.FontFaceHolder;
import client.render.RenderUtils;
import client.util.KeyUtils;
import java.awt.Color;
import java.io.IOException;
import org.lwjgl.input.Keyboard;

public class NumberSettingComponent extends FontFaceHolder {
   public long timestamp6;
   public final String labelText2;
   public long timestamp;
   public boolean enabled = false;
   public StringBuilder stringBuilder;
   public int count5 = 0;
   public long timestamp2 = 0L;
   public long timestamp3 = 0L;
   public long timestamp5;
   public long timestamp4;

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public void applyIndexByIndex(int count, int count2, int count3) throws IOException {
      if (count3 == 0 && super.fontFace != null) {
         if (RenderUtils.checkCondition(
               (float)(super.count3 - 4), (float)super.count4, (float)(this.count5 + 8), (float)((int)super.fontFace.getFontSize() + 1), count, count2
            )
            && !this.enabled) {
            if (!currentTimeMillis(this.timestamp6, 250)) {
               this.enabled = true;
            }

            this.timestamp6 = System.currentTimeMillis();
         } else if (this.enabled) {
            this.enabled = false;
         }
      }
   }

   public void applyTextByText(String text) {
      this.stringBuilder = new StringBuilder(text);
   }

   @Override
   public void applyIndex(int count, int count2, int count3) {
   }

   public int getCount5() {
      return this.count5;
   }

   private static boolean currentTimeMillis(long value, int count) {
      return System.currentTimeMillis() - value >= (long)count;
   }

   public void applyFontFaceByFontFace(int count, int count2, int count3, int count4, FontFace fontFace, Color color) {
      long value = 0x5282a794657eL;
      super.applyFontFaceByFontFace(count, count2, count3, count4, fontFace);
      if (this.stringBuilder.length() > 2
         && this.stringBuilder.substring(this.stringBuilder.length() - 2, this.stringBuilder.length()).equals(".0")
         && !this.isEnabled()) {
         this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
         this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
      }

      String text = String.valueOf(this.stringBuilder);
      this.count5 = (int)fontFace.getScaleFactor(text);
      String text2 = String.valueOf(this.stringBuilder);
      double offset = (double)count;
      double offset2 = (double)count2;
      int rgb = color.getRGB();
      double offset3 = offset2;
      double offset4 = offset;
      String text3 = text2;
      fontFace.applyString8(text3, offset4, offset3, rgb);
      if (this.enabled) {
         if (!currentTimeMillis(this.timestamp2, 500)) {
            Color color2 = new Color(185, 187, 190);
            float[] hsbValues = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
            float factor = (float)((double)hsbValues[2] + -0.2);
            if ((double)hsbValues[2] + -0.2 > 1.0) {
               factor = 1.0F;
            } else if ((double)hsbValues[2] + -0.2 < 0.0) {
               factor = 0.0F;
            }

            int rgb2 = Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor);
            RenderUtils.processDouble4(
               (double)(count + this.count5 + 2), (double)count2, (double)(count + this.count5 + 4), (double)((float)count2 + fontFace.getFontSize() - 2.0F), rgb2
            );
         } else if (currentTimeMillis(this.timestamp2, 1000)) {
            this.timestamp2 = System.currentTimeMillis();
         }

         if (KeyUtils.checkCondition(14)) {
            if (currentTimeMillis(this.timestamp, 300) && currentTimeMillis(this.timestamp3, 40)) {
               if (this.stringBuilder.length() > 0) {
                  this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
               }

               this.timestamp2 = System.currentTimeMillis();
               this.timestamp3 = System.currentTimeMillis();
            }
         } else {
            this.timestamp = System.currentTimeMillis();
         }

         boolean flag = false;

         for (int index = 0; index < this.labelText2.length(); index++) {
            if (Keyboard.getEventCharacter() == this.labelText2.charAt(index)) {
               flag = true;
               if (currentTimeMillis(this.timestamp5, 300) && currentTimeMillis(this.timestamp4, 40)) {
                  char eventCharacter = Keyboard.getEventCharacter();
                  if (eventCharacter == '-' && this.stringBuilder.length() == 0
                     || eventCharacter == '.' && !this.stringBuilder.toString().contains(".") && !this.stringBuilder.toString().isEmpty()
                     || eventCharacter != '.' && eventCharacter != '-') {
                     this.stringBuilder.append(eventCharacter);
                     this.timestamp4 = System.currentTimeMillis();
                     this.timestamp2 = System.currentTimeMillis();
                     break;
                  }
               }
            }
         }

         if (!flag) {
            this.timestamp5 = System.currentTimeMillis();
         }
      }
   }

   @Override
   public void applyChar(char character, int count) throws IOException {
      if ((count == 28 || count == 156 || count == 1) && this.enabled) {
         this.enabled = false;
      }

      if (this.enabled) {
         if (count == 14) {
            if (this.stringBuilder.length() > 0) {
               this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
            }
         } else {
            for (int index = 0; index < this.labelText2.length(); index++) {
               if (character == this.labelText2.charAt(index)) {
                  if (character == '-') {
                     if (this.stringBuilder.length() == 0) {
                        this.stringBuilder.append(character);
                     }
                  } else if (character == '.') {
                     if (!this.stringBuilder.toString().contains(".") && !this.stringBuilder.toString().isEmpty()) {
                        this.stringBuilder.append(character);
                     }
                  } else {
                     this.stringBuilder.append(character);
                  }
                  break;
               }
            }
         }
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getTextAsText() {
      return String.valueOf(this.stringBuilder);
   }

   @Override
   public void applyIndex(int count, int count2, int count3, long value) {
   }

   public NumberSettingComponent(String text, String text2) {
      this.timestamp = 0L;
      this.timestamp5 = 0L;
      this.timestamp4 = 0L;
      this.timestamp6 = 0L;
      this.labelText2 = text2;
      this.stringBuilder = new StringBuilder(text);
   }
}
