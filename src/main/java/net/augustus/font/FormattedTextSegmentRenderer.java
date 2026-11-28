package net.augustus.font;

import net.augustus.utils.MutableReference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;

public class FormattedTextSegmentRenderer {
   public final float baseY;
   public final StringBuilder segmentBuffer;
   public final float baseX;
   public final MutableReference underlineRef;
   public final boolean shadowPass;
   public final MutableReference italicRef;
   public final MutableReference currentColorRef;
   public final FontRenderer fontRenderer;
   public final MutableReference strikethroughRef;
   public final MutableReference boldRef;
   public final FormattedUnicodeFontRenderer parentRenderer;
   public final MutableReference obfuscatedRef;
   public final MutableReference cursorXRef;
   public final int shadowColor;

   public void flushSegment() {
      float f = this.baseY * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer);
      if (this.segmentBuffer.length() != 0) {
         String str = this.segmentBuffer.toString();
         this.segmentBuffer.setLength(0);
         if ((Boolean)this.obfuscatedRef.getValue()) {
            short s = 27947;
            String str2 = "§k";
            if ((Boolean)this.boldRef.getValue()) {
               str2 = str2 + "§l";
            }

            if ((Boolean)this.italicRef.getValue()) {
               str2 = str2 + "§o";
            }

            if ((Boolean)this.underlineRef.getValue()) {
               str2 = str2 + "§n";
            }

            if ((Boolean)this.strikethroughRef.getValue()) {
               str2 = str2 + "§m";
            }

            GL11.glPushMatrix();
            GL11.glScaled((double)FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer), (double)FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer), (double)FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer));
            this.fontRenderer.drawString(str2 + str, this.baseX, this.baseY, (Integer)this.currentColorRef.getValue());
            GL11.glPopMatrix();
            MutableReference cursorXRef2 = this.cursorXRef;
            Float f2 = (float)((double)((Float)this.cursorXRef.getValue()).floatValue() + this.fontRenderer.getStringWidthFloat(str) * (double)FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer));
            cursorXRef2.setValue(f2);
         } else {
            FormattedUnicodeFontRenderer parentRenderer2 = this.parentRenderer;
            boolean value = (Boolean)this.boldRef.getValue();
            boolean value2 = (Boolean)this.italicRef.getValue();
            UnicodeFont styledFontAccess = FormattedUnicodeFontRenderer.getStyledFontAccess(parentRenderer2, value, value2);
            Color color = new Color(this.shadowPass ? this.shadowColor : (Integer)this.currentColorRef.getValue());
            styledFontAccess.drawString((Float)this.cursorXRef.getValue(), f, str, color);
            float f3 = (float)this.parentRenderer.measureSegmentWidth(styledFontAccess, str) / FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer);
            if ((Boolean)this.underlineRef.getValue() || (Boolean)this.strikethroughRef.getValue()) {
               GlStateManager.disableTexture2D();
               GlStateManager.color(
                  (float)((this.shadowPass ? this.shadowColor : (Integer)this.currentColorRef.getValue()) >> 16 & 0xFF) / 255.0F,
                  (float)((this.shadowPass ? this.shadowColor : (Integer)this.currentColorRef.getValue()) >> 8 & 0xFF) / 255.0F,
                  (float)((this.shadowPass ? this.shadowColor : (Integer)this.currentColorRef.getValue()) & 0xFF) / 255.0F,
                  (float)((this.shadowPass ? this.shadowColor : (Integer)this.currentColorRef.getValue()) >> 24 & 0xFF) / 255.0F
               );
               GL11.glBegin(7);
               if ((Boolean)this.strikethroughRef.getValue()) {
                  float f4 = f + this.parentRenderer.getFontHeight() * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer) / 2.0F;
                  float value3 = (Float)this.cursorXRef.getValue();
                  this.emitQuadVertices(value3, f4, (Float)this.cursorXRef.getValue() + f3 * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer), f4 + 1.0F);
               }

               if ((Boolean)this.underlineRef.getValue()) {
                  float f9 = f + this.parentRenderer.getFontHeight() * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer);
                  float value4 = (Float)this.cursorXRef.getValue();
                  this.emitQuadVertices(value4, f9, (Float)this.cursorXRef.getValue() + f3 * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer), f9 + 1.0F);
               }

               GL11.glEnd();
               GlStateManager.enableTexture2D();
            }

            MutableReference cursorXRef3 = this.cursorXRef;
            Float f14 = (Float)this.cursorXRef.getValue() + f3 * FormattedUnicodeFontRenderer.getScaleFactorAccess(this.parentRenderer);
            cursorXRef3.setValue(f14);
         }
      }
   }

   public FormattedTextSegmentRenderer(
      FormattedUnicodeFontRenderer renderer,
      float f,
      StringBuilder sb,
      MutableReference mutableReference,
      MutableReference mutableReference2,
      MutableReference mutableReference3,
      MutableReference mutableReference4,
      MutableReference mutableReference5,
      FontRenderer fontRenderer2,
      float f2,
      MutableReference mutableReference6,
      MutableReference mutableReference7,
      boolean flag,
      int n
   ) {
      this.parentRenderer = renderer;
      this.baseY = f;
      this.segmentBuffer = sb;
      this.obfuscatedRef = mutableReference;
      this.boldRef = mutableReference2;
      this.italicRef = mutableReference3;
      this.underlineRef = mutableReference4;
      this.strikethroughRef = mutableReference5;
      this.fontRenderer = fontRenderer2;
      this.baseX = f2;
      this.currentColorRef = mutableReference6;
      this.cursorXRef = mutableReference7;
      this.shadowPass = flag;
      this.shadowColor = n;
   }

   public void emitQuadVertices(float f, float f2, float f3, float f4) {
      GL11.glVertex2f(f, f2);
      GL11.glVertex2f(f3, f2);
      GL11.glVertex2f(f3, f4);
      GL11.glVertex2f(f, f4);
   }

}
