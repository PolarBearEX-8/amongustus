package net.augustus.font;

import net.augustus.utils.MutableReference;
import net.augustus.utils.render.GlStateSaver;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class FormattedUnicodeFontRenderer implements IFontRenderer {
   private final float shadowOffset;
   private final Font baseFont;
   private final int[] colorCodeTable;
   private final UnicodeFont italicFont;
   private float fontHeight;
   private final UnicodeFont boldItalicFont;
   private final UnicodeFont boldFont;
   private final GlStateSaver glStateSaver;
   private final float scaleFactor;
   private final UnicodeFont regularFont;
   private final Random random;

   public float drawFormattedString(String str, float f, float f2, int n, boolean flag, boolean flag2) {
      if (str == null) {
         return 0.0F;
      } else {
         GL11.glPushMatrix();
         GL11.glScalef(1.0F / this.scaleFactor, 1.0F / this.scaleFactor, 1.0F);
         this.glStateSaver.setBlend(true);
         this.glStateSaver.setLighting(false);
         this.glStateSaver.setTexture2D(true);
         this.glStateSaver.setAlpha(false);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.disableLighting();
         MutableReference mutableReference = new MutableReference(false);
         MutableReference mutableReference2 = new MutableReference(false);
         MutableReference mutableReference3 = new MutableReference(false);
         MutableReference mutableReference4 = new MutableReference(false);
         MutableReference mutableReference5 = new MutableReference(false);
         MutableReference mutableReference6 = new MutableReference(n);
         MutableReference mutableReference7 = new MutableReference(f * this.scaleFactor);
         if ((n >>> 24 & 0xFF) == 0) {
            Integer num = (Integer)mutableReference6.getValue() | 0xFF000000;
            mutableReference6.setValue(num);
         }

         FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
         StringBuilder sb = new StringBuilder();
         FormattedTextSegmentRenderer renderer = new FormattedTextSegmentRenderer(this, f2, sb, mutableReference, mutableReference2, mutableReference3, mutableReference4, mutableReference5, fontRendererObj, f, mutableReference6, mutableReference7, flag2, n);

         for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 167 && i + 1 < str.length()) {
               char ch2 = Character.toLowerCase(str.charAt(i + 1));
               renderer.flushSegment();
               if (isColorCodeChar(ch2)) {
                  int n2 = colorCodeToIndex(ch2);
                  Boolean bool = false;
                  mutableReference.setValue(bool);
                  Boolean bool2 = false;
                  mutableReference2.setValue(bool2);
                  Boolean bool3 = false;
                  mutableReference3.setValue(bool3);
                  Boolean bool4 = false;
                  mutableReference4.setValue(bool4);
                  Boolean bool5 = false;
                  mutableReference5.setValue(bool5);
                  if (!flag2) {
                     Integer num2 = this.resolveColorCode(n, n2, flag);
                     mutableReference6.setValue(num2);
                  }
               } else if (ch2 == 'k') {
                  Boolean bool6 = true;
                  mutableReference.setValue(bool6);
               } else if (ch2 == 'l') {
                  Boolean bool7 = true;
                  mutableReference2.setValue(bool7);
               } else if (ch2 == 'o') {
                  Boolean bool8 = true;
                  mutableReference3.setValue(bool8);
               } else if (ch2 == 'n') {
                  Boolean bool9 = true;
                  mutableReference4.setValue(bool9);
               } else if (ch2 == 'm') {
                  Boolean bool10 = true;
                  mutableReference5.setValue(bool10);
               } else if (ch2 == 'r') {
                  Boolean bool11 = false;
                  mutableReference.setValue(bool11);
                  Boolean bool12 = false;
                  mutableReference2.setValue(bool12);
                  Boolean bool13 = false;
                  mutableReference3.setValue(bool13);
                  Boolean bool14 = false;
                  mutableReference4.setValue(bool14);
                  Boolean bool15 = false;
                  mutableReference5.setValue(bool15);
                  Integer num3 = n;
                  mutableReference6.setValue(num3);
               }

               i++;
            } else {
               sb.append(ch);
            }
         }

         renderer.flushSegment();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         OpenGlHelper.glBlendFunc(GlStateManager.getBlendSrcFactor(), GlStateManager.getBlendDstFactor(), GlStateManager.getBlendSrcFactorAlpha(), GlStateManager.getBlendDstFactorAlpha());
         this.glStateSaver.restoreAlpha();
         this.glStateSaver.restoreTexture2D();
         this.glStateSaver.restoreLighting();
         this.glStateSaver.restoreBlend();
         GL11.glBindTexture(3553, 0);
         GL11.glPopMatrix();
         GlStateManager.bindTexture(0);
         return (Float)mutableReference7.getValue() / this.scaleFactor;
      }
   }

   private UnicodeFont createUnicodeFont(Font font) {
      UnicodeFont unicodeFont = new UnicodeFont(font);
      unicodeFont.addGlyphs(0, 8303);
      unicodeFont.addGlyphs("❤");
      unicodeFont.addGlyphs(9475, 9475);
      unicodeFont.addGlyphs(9733, 9733);
      unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));

      try {
         unicodeFont.loadGlyphs();
         return unicodeFont;
      } catch (SlickException e) {
         throw new RuntimeException(e);
      }
   }

   public FormattedUnicodeFontRenderer(Font font, float f) {
       super();
      this.glStateSaver = GlStateSaver.create();
      this.colorCodeTable = new int[32];
      this.random = new Random();
      this.shadowOffset = 0.5F;
      this.baseFont = font;
      this.scaleFactor = f;
      float f2 = (float)font.getSize() / 2.0F * this.scaleFactor;
      this.regularFont = this.createUnicodeFont(font.deriveFont(0, f2));
      this.boldFont = this.createUnicodeFont(font.deriveFont(1, f2));
      this.italicFont = this.createUnicodeFont(font.deriveFont(2, f2));
      this.boldItalicFont = this.createUnicodeFont(font.deriveFont(3, f2));
      this.initColorCodeTable(false);
      this.fontHeight = (float)this.regularFont.getHeight("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789") / this.scaleFactor;
   }

   public static float getScaleFactorAccess(FormattedUnicodeFontRenderer renderer) {
      return renderer.scaleFactor;
   }

   private static int darkenColorForShadow(int n) {
      return (n & 16579836) >> 2 | n & 0xFF000000;
   }

   public int drawString(String str, float f, float f2, int n) {
      return (int)Math.ceil((double)this.drawFormattedString(str, f, f2, n, false, false));
   }

   private static boolean isColorCodeChar(char ch) {
      return ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f';
   }

   public Font getBaseFont() {
      return this.baseFont;
   }

   public static UnicodeFont getStyledFontAccess(FormattedUnicodeFontRenderer renderer, boolean flag, boolean flag2) {
      return renderer.getStyledFont(flag, flag2);
   }

   public float drawStringWithShadow(String str, float f, float f2, int n) {
      this.drawFormattedString(str, f + 1.0F, f2 + 1.0F, darkenColorForShadow(n), false, true);
      return this.drawFormattedString(str, f, f2, n, false, false);
   }

   private static int colorCodeToIndex(char ch) {
      ch = Character.toLowerCase(ch);
      return ch >= 48 && ch <= 57 ? ch - 48 : 10 + (ch - 97);
   }

   public int getStringWidth(String str) {
      return Math.round(this.getStringWidthFloat(str));
   }

   public float getFontHeight() {
      return this.fontHeight;
   }

   public int measureSegmentWidth(UnicodeFont unicodeFont, String str) {
      if (str == null || str.isEmpty()) {
         return 0;
      } else if (!str.endsWith(" ")) {
         return unicodeFont.getWidth(str);
      } else {
         return unicodeFont.getWidth(str + ".") - unicodeFont.getWidth(".");
      }
   }

   public FormattedUnicodeFontRenderer(Font font) {
       this(font, (float)new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor());
   }

   private void initColorCodeTable(boolean flag) {

      for (int i = 0; i < 32; i++) {
         int n = (i >> 3 & 1) * 85;
         int n2 = (i >> 2 & 1) * 170 + n;
         int n3 = (i >> 1 & 1) * 170 + n;
         int n4 = (i & 1) * 170 + n;
         if (i == 6) {
            n2 += 85;
         }

         if (flag) {
            int n5 = (n2 * 30 + n3 * 59 + n4 * 11) / 100;
            int n6 = (n2 * 30 + n3 * 70) / 100;
            int n7 = (n2 * 30 + n4 * 70) / 100;
            n2 = n5;
            n3 = n6;
            n4 = n7;
         }

         if (i >= 16) {
            n2 /= 4;
            n3 /= 4;
            n4 /= 4;
         }

         this.colorCodeTable[i] = (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
      }
   }

   public float getStringWidthFloat(String str) {
      if (str != null && !str.isEmpty()) {
         boolean flag = false;
         boolean flag2 = false;
         boolean flag3 = false;
         float f = 0.0F;
         StringBuilder sb = new StringBuilder();
         FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;

         for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 167 && i + 1 < str.length()) {
               char ch2 = Character.toLowerCase(str.charAt(i + 1));
               if (flag3) {
                  String str2 = "";
                  if (flag) {
                     str2 = str2 + "§l";
                  }

                  if (flag2) {
                     str2 = str2 + "§o";
                  }

                  f += (float)fontRendererObj.getStringWidthFloat(str2 + sb.toString());
                  sb.setLength(0);
               } else {
                  UnicodeFont styledFont = this.getStyledFont(flag, flag2);
                  String str3 = sb.toString();
                  f += (float)this.measureSegmentWidth(styledFont, str3) / this.scaleFactor;
                  sb.setLength(0);
               }

               if (isColorCodeChar(ch2)) {
                  flag = false;
                  flag2 = false;
                  flag3 = false;
               } else if (ch2 == 'l') {
                  flag = true;
               } else if (ch2 == 'o') {
                  flag2 = true;
               } else if (ch2 == 'k') {
                  flag3 = true;
               } else if (ch2 == 'r') {
                  flag = false;
                  flag2 = false;
                  flag3 = false;
               }

               i++;
            } else {
               sb.append(ch);
            }
         }

         if (flag3) {
            String str4 = "";
            if (flag) {
               str4 = str4 + "§l";
            }

            if (flag2) {
               str4 = str4 + "§o";
            }

            f += (float)(fontRendererObj.getStringWidthFloat(str4 + sb.toString()) * (double)this.scaleFactor);
         } else {
            UnicodeFont styledFont2 = this.getStyledFont(flag, flag2);
            f += (float)styledFont2.getWidth(sb.toString()) / this.scaleFactor;
         }

         return f;
      } else {
         return 0.0F;
      }
   }

   private UnicodeFont getStyledFont(boolean flag, boolean flag2) {
      if (flag && flag2) {
         return this.boldItalicFont;
      } else if (flag) {
         return this.boldFont;
      } else {
         return flag2 ? this.italicFont : this.regularFont;
      }
   }

   public float drawString(String str, float f, float f2, int n, boolean flag) {
      return this.drawFormattedString(str, f, f2, n, flag, false);
   }

   private int resolveColorCode(int n, int n2, boolean flag) {
      int n3 = this.colorCodeTable[n2 + (flag ? 16 : 0)];
      int n4 = n >>> 24 & 0xFF;
      if (n4 == 0) {
         n4 = 255;
      }

      return n4 << 24 | n3;
   }

}
