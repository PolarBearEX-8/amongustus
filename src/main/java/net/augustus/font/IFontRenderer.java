package net.augustus.font;

public interface IFontRenderer {
   float getFontHeight();

   int getStringWidth(String str);

   int drawString(String str, float f, float f2, int n);
}
