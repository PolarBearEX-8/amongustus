package net.augustus.font;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class DynamicFontUtil extends CustomFontUtil {
   private File fontFile;
   private String resourcePath;
   private final Int2ObjectMap sizedFontCache;
   private int currentFontSize;

   public DynamicFontUtil(String str, String str2, int n) {
       super(str, FontManager.loadFontResource(str2, n));
      this.sizedFontCache = new Int2ObjectOpenHashMap();
      this.currentFontSize = this.awtFont.getSize();
      this.resourcePath = str2;
      this.preloadFontSizes(Integer.MAX_VALUE);
   }

   public DynamicFontUtil(String str, File file, int n, int n2) {
       super(str, FontManager.loadFontFile(file, n), n2);
      this.sizedFontCache = new Int2ObjectOpenHashMap();
      this.currentFontSize = this.awtFont.getSize();
      this.fontFile = file;
      CompletableFuture.runAsync(() -> this.preloadFileFontsAsync(n2));
   }

   private void preloadFileFontsAsync(int n) {
      this.preloadFontSizes(n);
   }

   private void preloadFontSizes(int n) {

      try {
         Font font;
         if (this.fontFile != null) {
            font = FontManager.loadRawFontFile(this.fontFile);
         } else {
            font = FontManager.loadRawFontResource(this.resourcePath);
         }

         if (n == Integer.MAX_VALUE) {
            for (int i = 16; i < 80; i++) {
               this.sizedFontCache.put(i, new UnicodeFontRenderer(FontManager.deriveFontSize(font, i)));
            }
         } else {
            for (int j = 16; j < 80; j++) {
               this.sizedFontCache.put(j, new UnicodeFontRenderer(FontManager.deriveFontSize(font, j), (float)n));
            }
         }
      } catch (FontFormatException | IOException e) {
         e.printStackTrace();
      }
   }

   public void setFontSize(int fontSize) {
      if (fontSize != this.currentFontSize) {
         UnicodeFontRenderer unicodeFontRenderer2 = (UnicodeFontRenderer)this.sizedFontCache.get(fontSize);
         if (unicodeFontRenderer2 != null) {
            this.unicodeFontRenderer = unicodeFontRenderer2;
         } else {
            Font font;
            if (this.fontFile != null) {
               font = FontManager.loadFontFile(this.fontFile, fontSize);
            } else {
               font = FontManager.loadFontResource(this.resourcePath, fontSize);
            }

            if (this.scaleOverride == Integer.MAX_VALUE) {
               this.unicodeFontRenderer = new UnicodeFontRenderer(font);
            } else {
               this.unicodeFontRenderer = new UnicodeFontRenderer(font, (float)this.scaleOverride);
            }
         }

         this.awtFont = this.unicodeFontRenderer.getAwtFont();
         String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
         this.fontHeight = (float)this.unicodeFontRenderer.getStringHeight(str);
         this.currentFontSize = this.awtFont.getSize();
      }
   }

   public DynamicFontUtil(String str, String str2, int n, int n2) {
       super(str, FontManager.loadFontResource(str2, n), n2);
      this.sizedFontCache = new Int2ObjectOpenHashMap();
      this.currentFontSize = this.awtFont.getSize();
      this.resourcePath = str2;
      CompletableFuture.runAsync(() -> this.preloadResourceFontsAsync(n2));
   }

   private void preloadResourceFontsAsync(int n) {
      this.preloadFontSizes(n);
   }

}
