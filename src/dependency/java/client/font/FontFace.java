package client.font;

import client.event.EventBusManager;
import client.event.EventRenderArrayListListener;
import client.util.MinecraftAccessor;
import java.awt.Font;

public class FontFace implements EventRenderArrayListListener, MinecraftAccessor {
   public float fontSize = 0.0F;
   public final String fontName;
   public int fontStyle = Integer.MAX_VALUE;
   public SlickFontRenderer slickFontRenderer;
   public Font font;

   public String getFontName() {
      return this.fontName;
   }

   public void applyTextByText(String text, double posX, double posX2, double posY, double posZ, int count) {
      short shortValue = 7059;
      char character = '\ucc04';
      short shortValue2 = 12389;
      SlickFontRenderer slickFontRenderer2 = this.slickFontRenderer;
      float factor = (float)(posX + posY / 2.0 - (double)((float)this.slickFontRenderer.getWidth(text) / 2.0F));
      float factor2 = (float)(posX2 + posZ / 2.0 - (double)((float)this.slickFontRenderer.getHeight(text) / 2.0F));
      float factor3 = factor;
      slickFontRenderer2.computeFactorByText(text, factor3, factor2, count);
   }

   public float getScaleFactor(byte byteValue, String text, int count, int count2) {
      return (float)this.slickFontRenderer.getHeight(text);
   }

   public void applyText(String text, double posX, double posY, double posZ, int count) {
      SlickFontRenderer slickFontRenderer2 = this.slickFontRenderer;
      float factor = (float)(posX + posZ / 2.0 - (double)((float)this.slickFontRenderer.getWidth(text) / 2.0F));
      float factor2 = (float)posY;
      float factor3 = factor;
      slickFontRenderer2.computeFactorByText(text, factor3, factor2, count);
   }

   public float getScaleFactor(String text) {
      return (float)this.slickFontRenderer.getWidth(text);
   }

   public float getFontSize() {
      return this.fontSize;
   }

   public void applyText2(String text, double posX, double posY, double posZ, int count) {
      SlickFontRenderer slickFontRenderer2 = this.slickFontRenderer;
      float factor = (float)posX;
      double offset = posY + posZ / 2.0;
      String text2 = "Dj";
      float factor2 = (float)(offset - (double)((float)this.slickFontRenderer.getHeight(text2) / 2.0F));
      float factor3 = factor;
      slickFontRenderer2.computeFactorByText(text, factor3, factor2, count);
   }

   public void applyString4(String text, double offset, double offset2, int count) {
      float factor = (float)offset;
      float factor2 = (float)offset2;
      float factor3 = factor;
      this.slickFontRenderer.computeFactor(text, factor3, factor2, count);
   }

   private void updateState2() {
      if (this.fontStyle == Integer.MAX_VALUE) {
         this.slickFontRenderer = new SlickFontRenderer(this.font);
         this.slickFontRenderer.updateState();
         String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
         this.fontSize = (float)this.slickFontRenderer.getHeight(text);
      }
   }

   public void applyText(String text, double posX, double posX2, double posY, double posZ, int count) {
      SlickFontRenderer slickFontRenderer2 = this.slickFontRenderer;
      float factor = (float)(posX + posY / 2.0 - (double)((float)this.slickFontRenderer.getWidth(text) / 2.0F));
      float factor2 = (float)(posX2 + posZ / 2.0 - (double)((float)this.slickFontRenderer.getHeight(text) / 2.0F));
      float factor3 = factor;
      slickFontRenderer2.computeFactor(text, factor3, factor2, count);
   }

   public void applyString6(String text, double offset, double offset2, int count, boolean flag) {
      short shortValue = 9487;
      short shortValue2 = -27693;
      short shortValue3 = -16006;
      if (flag) {
         float factor = (float)offset;
         float factor2 = (float)offset2;
         float factor3 = factor;
         this.slickFontRenderer.computeFactor(text, factor3, factor2, count);
      } else {
         float factor4 = (float)offset;
         float factor5 = (float)offset2;
         float factor6 = factor4;
         this.slickFontRenderer.computeFactorByText(text, factor6, factor5, count);
      }
   }

   public void applyTextByText(String text, double posX, double posY, double posZ, int count) {
      SlickFontRenderer slickFontRenderer2 = this.slickFontRenderer;
      float factor = (float)posX;
      float factor2 = (float)(posY + posZ / 2.0 - (double)((float)this.slickFontRenderer.getHeight(text) / 2.0F));
      float factor3 = factor;
      slickFontRenderer2.computeFactorByText(text, factor3, factor2, count);
   }

   public void applyString8(String text, double offset, double offset2, int count) {
      float factor = (float)offset;
      float factor2 = (float)offset2;
      float factor3 = factor;
      this.slickFontRenderer.computeFactorByText(text, factor3, factor2, count);
   }

   @Override
   public void updateState() {
      this.updateState2();
   }

   public FontFace(String text, int count) {
      this.fontName = text;
      this.font = new Font(text, 0, count);
      this.slickFontRenderer = new SlickFontRenderer(this.font);
      String text2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
      this.fontSize = (float)this.slickFontRenderer.getHeight(text2);
      EventBusManager.getDietrichEvents2().subscribe(11, this);
   }

   public FontFace(String text, int count, int count2) {
      this.fontName = text;
      this.fontStyle = count2;
      this.font = new Font(text, 0, count);
      this.slickFontRenderer = new SlickFontRenderer(this.font, (float)this.fontStyle);
      String text2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
      this.fontSize = (float)this.slickFontRenderer.getHeight(text2);
      EventBusManager.getDietrichEvents2().subscribe(11, this);
   }

   public FontFace(String text, Font font2, int count) {
      this.fontName = text;
      this.fontStyle = count;
      this.font = font2;
      this.slickFontRenderer = new SlickFontRenderer(font2, (float)this.fontStyle);
      this.slickFontRenderer.updateState();
      String text2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
      this.fontSize = (float)this.slickFontRenderer.getHeight(text2);
      EventBusManager.getDietrichEvents2().subscribe(11, this);
   }

   public FontFace(String text, Font font2) {
      this.fontName = text;
      this.font = font2;
      this.slickFontRenderer = new SlickFontRenderer(font2);
      String text2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
      this.fontSize = (float)this.slickFontRenderer.getHeight(text2);
      EventBusManager.getDietrichEvents2().subscribe(11, this);
   }

   public FontFace() {
      this.fontName = "";
   }
}
