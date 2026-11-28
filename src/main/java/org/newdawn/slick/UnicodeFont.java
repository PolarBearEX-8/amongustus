package org.newdawn.slick;

import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.newdawn.slick.font.Glyph;
import org.newdawn.slick.font.GlyphPage;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.ResourceLoader;

public class UnicodeFont implements Font {
   private static final int DISPLAY_LIST_CACHE_SIZE = 200;
   private static final int MAX_GLYPH_CODE = 1114111;
   private static final int PAGE_SIZE = 512;
   private static final int PAGES = 2175;
   private static final SGL GL = Renderer.get();
   private static final UnicodeFont.DisplayList EMPTY_DISPLAY_LIST = new UnicodeFont.DisplayList();
   private static final Comparator heightComparator = new Comparator() {
      public int compare(Object o1, Object o2) {
         return ((Glyph)o1).getHeight() - ((Glyph)o2).getHeight();
      }
   };
   private java.awt.Font font;
   private String ttfFileRef;
   private int ascent;
   private int descent;
   private int leading;
   private int spaceWidth;
   private final Glyph[][] glyphs = new Glyph[2175][];
   private final List<GlyphPage> glyphPages = new ArrayList<>();
   private final List queuedGlyphs = new ArrayList(256);
   private final List effects = new ArrayList();
   private int paddingTop;
   private int paddingLeft;
   private int paddingBottom;
   private int paddingRight;
   private int paddingAdvanceX;
   private int paddingAdvanceY;
   private Glyph missingGlyph;
   private int glyphPageWidth = 512;
   private int glyphPageHeight = 512;
   private boolean displayListCaching = true;
   private int baseDisplayListID = -1;
   private int eldestDisplayListID;
   private UnicodeFont.DisplayList eldestDisplayList;
   private final LinkedHashMap displayLists = new LinkedHashMap(200, 1.0F, true) {
      protected boolean removeEldestEntry(Map.Entry eldest) {
         UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)eldest.getValue();
         if (displayList != null) {
            UnicodeFont.this.eldestDisplayListID = displayList.id;
         }

         return this.size() > 200;
      }
   };

   private static java.awt.Font createFont(String ttfFileRef) throws SlickException {
      try {
         return java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream(ttfFileRef));
      } catch (FontFormatException e) {
         throw new SlickException("Invalid font: " + ttfFileRef, e);
      } catch (IOException e2) {
         throw new SlickException("Error reading font: " + ttfFileRef, e2);
      }
   }

   public UnicodeFont(String ttfFileRef, String hieroFileRef) throws SlickException {
      this(ttfFileRef, new HieroSettings(hieroFileRef));
   }

   public UnicodeFont(String ttfFileRef, HieroSettings settings) throws SlickException {
      this.ttfFileRef = ttfFileRef;
      this.initializeFont(createFont(ttfFileRef), settings.getFontSize(), settings.isBold(), settings.isItalic());
      this.loadSettings(settings);
   }

   public UnicodeFont(String ttfFileRef, int size, boolean bold, boolean italic) throws SlickException {
      this.ttfFileRef = ttfFileRef;
      this.initializeFont(createFont(ttfFileRef), size, bold, italic);
   }

   public UnicodeFont(java.awt.Font font, String hieroFileRef) throws SlickException {
      this(font, new HieroSettings(hieroFileRef));
   }

   public UnicodeFont(java.awt.Font font, HieroSettings settings) {
      this.initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
      this.loadSettings(settings);
   }

   public UnicodeFont(java.awt.Font font) {
      this.initializeFont(font, font.getSize(), font.isBold(), font.isItalic());
   }

   public UnicodeFont(java.awt.Font font, int size, boolean bold, boolean italic) {
      this.initializeFont(font, size, bold, italic);
   }

   private void initializeFont(java.awt.Font baseFont, int size, boolean bold, boolean italic) {
      Map attributes = baseFont.getAttributes();
      attributes.put(TextAttribute.SIZE, new Float((float)size));
      attributes.put(TextAttribute.WEIGHT, bold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
      attributes.put(TextAttribute.POSTURE, italic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);

      try {
         attributes.put(TextAttribute.class.getDeclaredField("KERNING").get(null), TextAttribute.class.getDeclaredField("KERNING_ON").get(null));
      } catch (Exception e) {
      }

      this.font = baseFont.deriveFont(attributes);
      FontMetrics metrics = GlyphPage.getScratchGraphics().getFontMetrics(this.font);
      this.ascent = metrics.getAscent();
      this.descent = metrics.getDescent();
      this.leading = metrics.getLeading();
      char[] chars = " ".toCharArray();
      this.spaceWidth = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0).getGlyphLogicalBounds(0).getBounds().width;
   }

   private void loadSettings(HieroSettings settings) {
      this.paddingTop = settings.getPaddingTop();
      this.paddingLeft = settings.getPaddingLeft();
      this.paddingBottom = settings.getPaddingBottom();
      this.paddingRight = settings.getPaddingRight();
      this.paddingAdvanceX = settings.getPaddingAdvanceX();
      this.paddingAdvanceY = settings.getPaddingAdvanceY();
      this.glyphPageWidth = settings.getGlyphPageWidth();
      this.glyphPageHeight = settings.getGlyphPageHeight();
      this.effects.addAll(settings.getEffects());
   }

   public void addGlyphs(int startCodePoint, int endCodePoint) {
      for (int codePoint = startCodePoint; codePoint <= endCodePoint; codePoint++) {
         this.addGlyphs(new String(Character.toChars(codePoint)));
      }
   }

   public void addGlyphs(String text) {
      if (text == null) {
         throw new IllegalArgumentException("text cannot be null.");
      } else {
         char[] chars = text.toCharArray();
         GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
         int i = 0;

         for (int n = vector.getNumGlyphs(); i < n; i++) {
            int codePoint = text.codePointAt(vector.getGlyphCharIndex(i));
            Rectangle bounds = this.getGlyphBounds(vector, i, codePoint);
            this.getGlyph(vector.getGlyphCode(i), codePoint, bounds, vector, i);
         }
      }
   }

   public void addAsciiGlyphs() {
      this.addGlyphs(32, 255);
   }

   public void addNeheGlyphs() {
      this.addGlyphs(32, 128);
   }

   public boolean loadGlyphs() throws SlickException {
      return this.loadGlyphs(-1);
   }

   public boolean loadGlyphs(int maxGlyphsToLoad) throws SlickException {
      if (this.queuedGlyphs.isEmpty()) {
         return false;
      } else if (this.effects.isEmpty()) {
         throw new IllegalStateException("The UnicodeFont must have at least one effect before any glyphs can be loaded.");
      } else {
         Iterator iter = this.queuedGlyphs.iterator();

         while (iter.hasNext()) {
            Glyph glyph = (Glyph)iter.next();
            int codePoint = glyph.getCodePoint();
            if (glyph.getWidth() == 0 || codePoint == 32) {
               iter.remove();
            } else if (glyph.isMissing()) {
               if (this.missingGlyph != null) {
                  if (glyph != this.missingGlyph) {
                     iter.remove();
                  }
               } else {
                  this.missingGlyph = glyph;
               }
            }
         }

         Collections.sort(this.queuedGlyphs, heightComparator);

         for (GlyphPage glyphPage : this.glyphPages) {
            maxGlyphsToLoad -= glyphPage.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
            if (maxGlyphsToLoad == 0 || this.queuedGlyphs.isEmpty()) {
               return true;
            }
         }

         while (!this.queuedGlyphs.isEmpty()) {
            GlyphPage glyphPagex = new GlyphPage(this, this.glyphPageWidth, this.glyphPageHeight);
            this.glyphPages.add(glyphPagex);
            maxGlyphsToLoad -= glyphPagex.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
            if (maxGlyphsToLoad == 0) {
               return true;
            }
         }

         return true;
      }
   }

   public void clearGlyphs() {
      for (int i = 0; i < 2175; i++) {
         this.glyphs[i] = null;
      }

      for (GlyphPage page : this.glyphPages) {
         try {
            page.getImage().destroy();
         } catch (SlickException e) {
         }
      }

      this.glyphPages.clear();
      if (this.baseDisplayListID != -1) {
         GL.glDeleteLists(this.baseDisplayListID, this.displayLists.size());
         this.baseDisplayListID = -1;
      }

      this.queuedGlyphs.clear();
      this.missingGlyph = null;
   }

   public void destroy() {
      this.clearGlyphs();
   }

   public UnicodeFont.DisplayList drawDisplayList(float x, float y, String text, Color color, int startIndex, int endIndex) {
      if (text == null) {
         throw new IllegalArgumentException("text cannot be null.");
      } else if (text.length() == 0) {
         return EMPTY_DISPLAY_LIST;
      } else if (color == null) {
         throw new IllegalArgumentException("color cannot be null.");
      } else {
         x -= (float)this.paddingLeft;
         y -= (float)this.paddingTop;
         String displayListKey = text.substring(startIndex, endIndex);
         color.bind();
         TextureImpl.bindNone();
         UnicodeFont.DisplayList displayList = null;
         if (this.displayListCaching && this.queuedGlyphs.isEmpty()) {
            if (this.baseDisplayListID == -1) {
               this.baseDisplayListID = GL.glGenLists(200);
               if (this.baseDisplayListID == 0) {
                  this.baseDisplayListID = -1;
                  this.displayListCaching = false;
                  return new UnicodeFont.DisplayList();
               }
            }

            displayList = (UnicodeFont.DisplayList)this.displayLists.get(displayListKey);
            if (displayList != null) {
               if (!displayList.invalid) {
                  GL.glTranslatef(x, y, 0.0F);
                  GL.glCallList(displayList.id);
                  GL.glTranslatef(-x, -y, 0.0F);
                  return displayList;
               }

               displayList.invalid = false;
            } else if (displayList == null) {
               displayList = new UnicodeFont.DisplayList();
               int displayListCount = this.displayLists.size();
               this.displayLists.put(displayListKey, displayList);
               if (displayListCount < 200) {
                  displayList.id = this.baseDisplayListID + displayListCount;
               } else {
                  displayList.id = this.eldestDisplayListID;
               }
            }

            this.displayLists.put(displayListKey, displayList);
         }

         GL.glTranslatef(x, y, 0.0F);
         if (displayList != null) {
            GL.glNewList(displayList.id, 4865);
         }

         char[] chars = text.substring(0, endIndex).toCharArray();
         GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
         int maxWidth = 0;
         int totalHeight = 0;
         int lines = 0;
         int extraX = 0;
         int extraY = this.ascent;
         boolean startNewLine = false;
         Texture lastBind = null;
         int glyphIndex = 0;

         for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
            int charIndex = vector.getGlyphCharIndex(glyphIndex);
            if (charIndex >= startIndex) {
               if (charIndex > endIndex) {
                  break;
               }

               int codePoint = text.codePointAt(charIndex);
               Rectangle bounds = this.getGlyphBounds(vector, glyphIndex, codePoint);
               Glyph glyph = this.getGlyph(vector.getGlyphCode(glyphIndex), codePoint, bounds, vector, glyphIndex);
               if (startNewLine && codePoint != 10) {
                  extraX = -bounds.x;
                  startNewLine = false;
               }

               Image image = glyph.getImage();
               if (image == null && this.missingGlyph != null && glyph.isMissing()) {
                  image = this.missingGlyph.getImage();
               }

               if (image != null) {
                  Texture texture = image.getTexture();
                  if (lastBind != null && lastBind != texture) {
                     GL.glEnd();
                     lastBind = null;
                  }

                  if (lastBind == null) {
                     texture.bind();
                     GL.glBegin(7);
                     lastBind = texture;
                  }

                  image.drawEmbedded((float)(bounds.x + extraX), (float)(bounds.y + extraY), (float)image.getWidth(), (float)image.getHeight());
               }

               if (glyphIndex > 0) {
                  extraX += this.paddingRight + this.paddingLeft + this.paddingAdvanceX;
               }

               maxWidth = Math.max(maxWidth, bounds.x + extraX + bounds.width);
               totalHeight = Math.max(totalHeight, this.ascent + bounds.y + bounds.height);
               if (codePoint == 10) {
                  startNewLine = true;
                  extraY += this.getLineHeight();
                  lines++;
                  totalHeight = 0;
               }
            }
         }

         if (lastBind != null) {
            GL.glEnd();
         }

         if (displayList != null) {
            GL.glEndList();
            if (!this.queuedGlyphs.isEmpty()) {
               displayList.invalid = true;
            }
         }

         GL.glTranslatef(-x, -y, 0.0F);
         if (displayList == null) {
            displayList = new UnicodeFont.DisplayList();
         }

         displayList.width = (short)maxWidth;
         displayList.height = (short)(lines * this.getLineHeight() + totalHeight);
         return displayList;
      }
   }

   public void drawString(float x, float y, String text, Color color, int startIndex, int endIndex) {
      this.drawDisplayList(x, y, text, color, startIndex, endIndex);
   }

   public void drawString(float x, float y, String text) {
      this.drawString(x, y, text, Color.white);
   }

   public void drawString(float x, float y, String text, Color col) {
      this.drawString(x, y, text, col, 0, text.length());
   }

   private Glyph getGlyph(int glyphCode, int codePoint, Rectangle bounds, GlyphVector vector, int index) {
      if (glyphCode >= 0 && glyphCode < 1114111) {
         int pageIndex = glyphCode / 512;
         int glyphIndex = glyphCode & 511;
         Glyph glyph = null;
         Glyph[] page = this.glyphs[pageIndex];
         if (page != null) {
            glyph = page[glyphIndex];
            if (glyph != null) {
               return glyph;
            }
         } else {
            page = this.glyphs[pageIndex] = new Glyph[512];
         }

         glyph = page[glyphIndex] = new Glyph(codePoint, bounds, vector, index, this);
         this.queuedGlyphs.add(glyph);
         return glyph;
      } else {
         return new Glyph(codePoint, bounds, vector, index, this) {
            public boolean isMissing() {
               return true;
            }
         };
      }
   }

   private Rectangle getGlyphBounds(GlyphVector vector, int index, int codePoint) {
      Rectangle bounds = vector.getGlyphPixelBounds(index, GlyphPage.renderContext, 0.0F, 0.0F);
      if (codePoint == 32) {
         bounds.width = this.spaceWidth;
      } else {
         java.awt.geom.Rectangle2D ob2d = vector.getGlyphOutline(index).getBounds2D();
         if (ob2d.getWidth() > 0.0 && ob2d.getHeight() > 0.0) {
            int ox = (int)Math.floor(ob2d.getMinX());
            int oy = (int)Math.floor(ob2d.getMinY());
            int ox2 = (int)Math.ceil(ob2d.getMaxX());
            int oy2 = (int)Math.ceil(ob2d.getMaxY());
            Rectangle ob = new Rectangle(ox, oy, ox2 - ox, oy2 - oy);
            if (bounds.width <= 0 || bounds.height <= 0) {
               bounds = ob;
            } else {
               bounds = bounds.union(ob);
            }
         }
      }

      return bounds;
   }

   public int getSpaceWidth() {
      return this.spaceWidth;
   }

   public int getWidth(String text) {
      if (text == null) {
         throw new IllegalArgumentException("text cannot be null.");
      } else if (text.length() == 0) {
         return 0;
      } else {
         if (this.displayListCaching) {
            UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)this.displayLists.get(text);
            if (displayList != null) {
               return displayList.width;
            }
         }

         char[] chars = text.toCharArray();
         GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
         int width = 0;
         int extraX = 0;
         boolean startNewLine = false;
         int glyphIndex = 0;

         for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
            int codePoint = text.codePointAt(vector.getGlyphCharIndex(glyphIndex));
            Rectangle bounds = this.getGlyphBounds(vector, glyphIndex, codePoint);
            if (startNewLine && codePoint != 10) {
               extraX = -bounds.x;
            }

            if (glyphIndex > 0) {
               extraX += this.paddingLeft + this.paddingRight + this.paddingAdvanceX;
            }

            width = Math.max(width, bounds.x + extraX + bounds.width);
            if (codePoint == 10) {
               startNewLine = true;
            }
         }

         return width;
      }
   }

   public int getHeight(String text) {
      if (text == null) {
         throw new IllegalArgumentException("text cannot be null.");
      } else if (text.length() == 0) {
         return 0;
      } else {
         if (this.displayListCaching) {
            UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)this.displayLists.get(text);
            if (displayList != null) {
               return displayList.height;
            }
         }

         char[] chars = text.toCharArray();
         GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
         int lines = 0;
         int height = 0;
         int i = 0;

         for (int n = vector.getNumGlyphs(); i < n; i++) {
            int codePoint = text.codePointAt(vector.getGlyphCharIndex(i));
            if (codePoint != 32) {
               Rectangle bounds = this.getGlyphBounds(vector, i, codePoint);
               height = Math.max(height, this.ascent + bounds.y + bounds.height);
               if (codePoint == 10) {
                  lines++;
                  height = 0;
               }
            }
         }

         return lines * this.getLineHeight() + height;
      }
   }

   public int getYOffset(String text) {
      if (text == null) {
         throw new IllegalArgumentException("text cannot be null.");
      } else {
         UnicodeFont.DisplayList displayList = null;
         if (this.displayListCaching) {
            displayList = (UnicodeFont.DisplayList)this.displayLists.get(text);
            if (displayList != null && displayList.yOffset != null) {
               return displayList.yOffset.intValue();
            }
         }

         int index = text.indexOf(10);
         if (index != -1) {
            text = text.substring(0, index);
         }

         char[] chars = text.toCharArray();
         GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
         int yOffset = this.ascent + vector.getPixelBounds(null, 0.0F, 0.0F).y;
         if (displayList != null) {
            displayList.yOffset = new Short((short)yOffset);
         }

         return yOffset;
      }
   }

   public java.awt.Font getFont() {
      return this.font;
   }

   public int getPaddingTop() {
      return this.paddingTop;
   }

   public void setPaddingTop(int paddingTop) {
      this.paddingTop = paddingTop;
   }

   public int getPaddingLeft() {
      return this.paddingLeft;
   }

   public void setPaddingLeft(int paddingLeft) {
      this.paddingLeft = paddingLeft;
   }

   public int getPaddingBottom() {
      return this.paddingBottom;
   }

   public void setPaddingBottom(int paddingBottom) {
      this.paddingBottom = paddingBottom;
   }

   public int getPaddingRight() {
      return this.paddingRight;
   }

   public void setPaddingRight(int paddingRight) {
      this.paddingRight = paddingRight;
   }

   public int getPaddingAdvanceX() {
      return this.paddingAdvanceX;
   }

   public void setPaddingAdvanceX(int paddingAdvanceX) {
      this.paddingAdvanceX = paddingAdvanceX;
   }

   public int getPaddingAdvanceY() {
      return this.paddingAdvanceY;
   }

   public void setPaddingAdvanceY(int paddingAdvanceY) {
      this.paddingAdvanceY = paddingAdvanceY;
   }

   public int getLineHeight() {
      return this.descent + this.ascent + this.leading + this.paddingTop + this.paddingBottom + this.paddingAdvanceY;
   }

   public int getAscent() {
      return this.ascent;
   }

   public int getDescent() {
      return this.descent;
   }

   public int getLeading() {
      return this.leading;
   }

   public int getGlyphPageWidth() {
      return this.glyphPageWidth;
   }

   public void setGlyphPageWidth(int glyphPageWidth) {
      this.glyphPageWidth = glyphPageWidth;
   }

   public int getGlyphPageHeight() {
      return this.glyphPageHeight;
   }

   public void setGlyphPageHeight(int glyphPageHeight) {
      this.glyphPageHeight = glyphPageHeight;
   }

   public List getGlyphPages() {
      return this.glyphPages;
   }

   public List getEffects() {
      return this.effects;
   }

   public boolean isCaching() {
      return this.displayListCaching;
   }

   public void setDisplayListCaching(boolean displayListCaching) {
      this.displayListCaching = displayListCaching;
   }

   public String getFontFile() {
      if (this.ttfFileRef == null) {
         try {
            Object font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", java.awt.Font.class).invoke(null, this.font);
            Field platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
            platNameField.setAccessible(true);
            this.ttfFileRef = (String)platNameField.get(font2D);
         } catch (Throwable e) {
         }

         if (this.ttfFileRef == null) {
            this.ttfFileRef = "";
         }
      }

      return this.ttfFileRef.length() == 0 ? null : this.ttfFileRef;
   }

   public static class DisplayList {
      boolean invalid;
      int id;
      Short yOffset;
      public short width;
      public short height;
      public Object userData;

      DisplayList() {
      }
   }
}
