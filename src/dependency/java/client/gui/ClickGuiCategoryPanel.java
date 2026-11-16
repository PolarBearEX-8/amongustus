package client.gui;

import client.font.FontFace;
import client.module.Category;
import client.render.RenderUtils;
import java.awt.Color;
import java.util.List;

public class ClickGuiCategoryPanel {
   public int count6;
   public int count10;
   public int count5;
   public int count2;
   public final Category category;
   public FontFace fontFace;
   public int count9;
   public int count = 0;
   public int count11;
   public int count4;
   public int count8;
   public boolean enabled;
   public int count3;
   public int count7;

   public int getCount4() {
      return this.count4;
   }

   public void setCount(int count12) {
      this.count = count12;
   }

   public void applyIndex(int count12, int count13, int count14, int count15, boolean flag) {
      this.count2 = count14;
      this.count11 = count15;
      this.count8 = this.count2 - this.count4;
      float count16 = (float)this.count2;
      FontFace fontFace2 = this.fontFace;
      String text = this.category.name();
      this.count3 = (int)(count16 + fontFace2.getScaleFactor(text) + (float)this.count4);
      this.count9 = this.count11 - this.count7 + 3;
      this.count6 = (int)((float)this.count11 + this.fontFace.getFontSize() + (float)this.count7);
      FontFace fontFace3 = this.fontFace;
      String text2 = this.category.toString();
      double count17 = (double)this.count2;
      double count18 = (double)this.count11;
      double offset = count17;
      String text3 = text2;
      fontFace3.applyString8(text3, offset, count18, -4604994);
      int count19 = this.count5;
      int count20 = this.count10;
      int count21 = this.count9;
      int count22 = this.count8;
      if (this.checkIndex(count22, count21, count20, count19, count12, count13) && !flag) {
         double count23 = (double)this.count2;
         double offset2 = (double)((float)this.count11 + this.fontFace.getFontSize());
         float count24 = (float)this.count2;
         FontFace fontFace4 = this.fontFace;
         String text4 = this.category.name();
         RenderUtils.processAction2(
            count23, offset2, (double)(count24 + fontFace4.getScaleFactor(text4)), (double)((float)count15 + this.fontFace.getFontSize()), 2.0F, new Color(185, 187, 190)
         );
      }
   }

   public int getCount7() {
      return this.count7;
   }

   public int getCount() {
      return this.count;
   }

   public Category getCategoryAsCategory() {
      return this.category;
   }

   public List getList() {
      return this.category.getModuleList();
   }

   public int getCount2() {
      return this.count2;
   }

   public boolean checkIndex(int count12, int count13, int count14, int count15, int count16, int count17) {
      return count16 >= count12 && count17 >= count13 && count16 < count12 + count14 && count17 < count13 + count15;
   }

   public int getCount11() {
      return this.count11;
   }

   public ClickGuiCategoryPanel(int count12, int count13, int count14, int count15, Category category2, FontFace fontFace2) {
      this.fontFace = fontFace2;
      this.count2 = count12;
      this.count11 = count13;
      this.count4 = count14;
      this.count7 = count15;
      this.count8 = this.count2 - count14;
      float count16 = (float)this.count2;
      FontFace fontFace3 = this.fontFace;
      String text = category2.name();
      this.count3 = (int)(count16 + fontFace3.getScaleFactor(text) + (float)count14);
      this.count9 = this.count11 - count15 + 3;
      this.count6 = (int)((float)this.count11 + this.fontFace.getFontSize() + (float)count15);
      this.count10 = this.count3 - this.count8;
      this.count5 = this.count6 - this.count9;
      this.category = category2;
   }
}
