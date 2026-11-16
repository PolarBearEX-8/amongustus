package client.font;

import java.io.IOException;

public class FontFaceHolder {
   public int count3;
   public int count;
   public int count2;
   public FontFace fontFace;
   public int count4;

   public void setCount(int count5) {
      this.count = count5;
   }

   public void setCount3(int count5) {
      this.count3 = count5;
   }

   public FontFace getFontFace() {
      return this.fontFace;
   }

   public void setCount4(int count5) {
      this.count4 = count5;
   }

   public void applyIndexByIndex(int count5, int count6, int count7) throws IOException {
   }

   public void applyIndex(int count5, int count6, int count7) {
   }

   public int getCount4() {
      return this.count4;
   }

   public void applyChar(char character, int count5) throws IOException {
   }

   public void updateState() {
   }

   public void setFontFace(FontFace fontFace2) {
      this.fontFace = fontFace2;
   }

   public void setCount2(int count5) {
      this.count2 = count5;
   }

   public void applyFontFaceByFontFace(int count5, int count6, int count7, int count8, FontFace fontFace2) {
      this.count3 = count5;
      this.count4 = count6;
      this.count2 = count7;
      this.count = count8;
      this.fontFace = fontFace2;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount3() {
      return this.count3;
   }

   public void applyIndex(int count5, int count6, int count7, long value) {
   }

   public int getCount2() {
      return this.count2;
   }
}
