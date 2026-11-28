package net.augustus.font;

import net.augustus.utils.render.GlStateSaver;
import java.awt.Color;
import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class UnicodeFontRenderer {
   private final Font awtFont;
   private final UnicodeFont unicodeFont;
   private final GlStateSaver glStateSaver = GlStateSaver.create();
   private final float scaleFactor;

   public void loadGlyphs() {
      try {
         this.unicodeFont.loadGlyphs();
      } catch (SlickException e) {
         throw new RuntimeException(e);
      }
   }

   public float drawStringWithShadow(String str, float f, float f2, int n) {
      this.drawString(str, f + 1.0F, f2 + 1.0F, -16777216);
      return this.drawString(str, f, f2, n);
   }

   public int getCharWidth(char ch) {
      return (int)((float)this.getStringWidth(Character.toString(ch)) / this.scaleFactor);
   }

   public Font getAwtFont() {
      return this.awtFont;
   }

   public void drawCenteredString(String str, float f, float f2, int n) {
      this.drawString(str, f - (float)(this.getStringWidth(str) / 2), f2, n);
   }

   public int getStringHeight(String str) {
      return (int)((float)this.unicodeFont.getHeight(str) / this.scaleFactor);
   }

   public int getStringWidth(String str) {
      return (int)((float)this.unicodeFont.getWidth(str) / this.scaleFactor);
   }

   public UnicodeFontRenderer(Font font) {
      this.scaleFactor = (float)new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
      this.unicodeFont = new UnicodeFont(font.deriveFont(0, (float)font.getSize() / 2.0F * this.scaleFactor));
      this.awtFont = font;
      this.unicodeFont.addGlyphs(0, 8303);
      this.unicodeFont.addGlyphs("❤");
      this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));
      this.loadGlyphs();
   }

   public UnicodeFontRenderer(Font font, float f) {
      this.scaleFactor = f;
      this.unicodeFont = new UnicodeFont(font.deriveFont(0, (float)font.getSize() / 2.0F * this.scaleFactor));
      this.awtFont = font;
      this.unicodeFont.addGlyphs(0, 8303);
      this.unicodeFont.addGlyphs("❤");
      this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));
      this.loadGlyphs();
   }

   public float drawString(String str, float f, float f2, int n) {
      if (str == null) {
         return 0.0F;
      } else {
         GL11.glPushMatrix();
         GL11.glScaled((double)(1.0F / this.scaleFactor), (double)(1.0F / this.scaleFactor), 1.0);
         this.glStateSaver.setBlend(true);
         this.glStateSaver.setLighting(false);
         this.glStateSaver.setTexture2D(false);
         this.glStateSaver.setAlpha(false);
         f *= this.scaleFactor;
         f2 *= this.scaleFactor;
         this.unicodeFont.drawString(f, f2, str, new org.newdawn.slick.Color(n));
         GL11.glDisable(3553);
         GL11.glBindTexture(3553, 0);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         OpenGlHelper.glBlendFunc(GlStateManager.getBlendSrcFactor(), GlStateManager.getBlendDstFactor(), GlStateManager.getBlendSrcFactorAlpha(), GlStateManager.getBlendDstFactorAlpha());
         this.glStateSaver.restoreAlpha();
         this.glStateSaver.restoreTexture2D();
         this.glStateSaver.restoreLighting();
         this.glStateSaver.restoreBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
         GlStateManager.bindTexture(0);
         return f;
      }
   }
}
