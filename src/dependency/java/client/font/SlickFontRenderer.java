package client.font;

import client.util.BuildManager;
import client.util.PreEndHandManager;
import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.font.effects.ColorEffect;

public class SlickFontRenderer {
   public final UnicodeFont unicodeFont;
   public final Font font;
   public final float factor;
   public int count;

   public void applyText(String text, float factor2, float factor3, int count2) {
      float factor4 = factor2 - (float)(this.getWidth(text) / 2);
      this.computeFactorByText(text, factor4, factor3, count2);
   }

   public float computeFactor(String text, float factor2, float factor3, int count2) {
      float factor4 = factor2 + 1.0F;
      float factor5 = factor3 + 1.0F;
      float factor6 = factor4;
      this.computeFactorByText(text, factor6, factor5, -16777216);
      return this.computeFactorByText(text, factor2, factor3, count2);
   }

   public int getWidth(String text) {
      return (int)((float)this.unicodeFont.getWidth(text) / this.factor);
   }

   public void updateState() {
      try {
         this.unicodeFont.loadGlyphs();
      } catch (SlickException slickException) {
         throw new RuntimeException(slickException);
      }

      this.count = (int)((float)this.unicodeFont.getHeight("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789") / this.factor);
   }

   public int computeCount(char character) {
      String text = Character.toString(character);
      return (int)((float)this.getWidth(text) / this.factor);
   }

   public Font getFont() {
      return this.font;
   }

   public float computeFactorByText(String text, float factor2, float factor3, int count2) {
      if (text == null) {
         return 0.0F;
      } else {
         GL11.glPushMatrix();
         GL11.glScaled((double)(1.0F / this.factor), (double)(1.0F / this.factor), 1.0);
         boolean flag = GL11.glIsEnabled(3042);
         boolean flag2 = GL11.glIsEnabled(2896);
         boolean flag3 = GL11.glIsEnabled(3553);
         boolean flag4 = GL11.glIsEnabled(3008);
         if (!flag) {
            GL11.glEnable(3042);
         }

         if (flag2) {
            GL11.glDisable(2896);
         }

         if (flag3) {
            GL11.glDisable(3553);
            if (BuildManager.canExecute()) {
               PreEndHandManager.updateState();
            }
         }

         if (flag4) {
            GL11.glEnable(3008);
         }

         float factor4 = factor2 * this.factor;
         float factor5 = factor3 * this.factor;
         this.unicodeFont.drawString(factor4, factor5, text, new Color(count2));
         if (flag4) {
            GL11.glEnable(3008);
         }

         if (flag3) {
            GL11.glEnable(3553);
            if (BuildManager.canExecute()) {
               PreEndHandManager.updateState2();
            }
         }

         if (flag2) {
            GL11.glEnable(2896);
         }

         if (!flag) {
            GL11.glDisable(3042);
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
         GlStateManager.setActiveTexture(0);
         return factor4;
      }
   }

   public int getHeight(String text) {
      return (int)((float)this.unicodeFont.getHeight(text) / this.factor);
   }

   public SlickFontRenderer(Font font2, float factor2) {
      HieroSettings hieroSettings = new HieroSettings();
      hieroSettings.setGlyphPageWidth(2048);
      hieroSettings.setGlyphPageHeight(2048);
      this.factor = factor2;
      hieroSettings.setFontSize((int)((float)Math.round((float)font2.getSize() / 2.0F) * this.factor));
      this.unicodeFont = new UnicodeFont(font2.deriveFont(0, (float)font2.getSize() / 2.0F * this.factor));
      this.font = font2;
      this.unicodeFont.addAsciiGlyphs();
      this.unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
      this.updateState();
   }

   public SlickFontRenderer(Font font2) {
      HieroSettings hieroSettings = new HieroSettings();
      hieroSettings.setGlyphPageWidth(2048);
      hieroSettings.setGlyphPageHeight(2048);
      ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
      this.factor = (float)scaledResolution.getScaleFactor();
      this.unicodeFont = new UnicodeFont(font2.deriveFont(0, (float)font2.getSize() / 2.0F * this.factor));
      this.font = font2;
      this.unicodeFont.addAsciiGlyphs();
      this.unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
      this.updateState();
   }
}
