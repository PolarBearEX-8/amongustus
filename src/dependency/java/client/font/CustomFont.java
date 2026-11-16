package client.font;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class CustomFont extends FontFace {
   public final String labelText;
   public int count;
   public final Int2ObjectMap int2ObjectMap = new Int2ObjectOpenHashMap();

   public void processAction(int count2) {
      if (count2 != this.count) {
         SlickFontRenderer fontRenderer = (SlickFontRenderer)this.int2ObjectMap.get(count2);
         if (fontRenderer != null) {
            this.slickFontRenderer = fontRenderer;
         } else if (this.fontStyle == Integer.MAX_VALUE) {
            this.slickFontRenderer = new SlickFontRenderer(FontRegistry.resolveFont(this.labelText, count2));
         } else {
            this.slickFontRenderer = new SlickFontRenderer(FontRegistry.resolveFont(this.labelText, count2), (float)this.fontStyle);
         }

         this.font = this.slickFontRenderer.getFont();
         String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqurstuvwxyz1234567890";
         this.fontSize = (float)this.slickFontRenderer.getHeight(text);
         this.count = this.font.getSize();
      }
   }

   private void applyIndex(int count2) {
      try {
         Font font = FontRegistry.resolveFont(this.labelText);
         if (count2 == Integer.MAX_VALUE) {
            for (int index = 16; index < 80; index++) {
               this.int2ObjectMap.put(index, new SlickFontRenderer(FontRegistry.resolveFont(font, index)));
            }
         } else {
            for (int index2 = 16; index2 < 80; index2++) {
               this.int2ObjectMap.put(index2, new SlickFontRenderer(FontRegistry.resolveFont(font, index2), (float)count2));
            }
         }
      } catch (FontFormatException | IOException fontFormatException) {
         fontFormatException.printStackTrace();
      }
   }

   private void applyIndexByIndex(int count2) {
      this.applyIndex(count2);
   }

   public CustomFont(String text, String text2, int count2) {
      super(text, FontRegistry.resolveFont(text2, count2));
      this.count = this.font.getSize();
      this.labelText = text2;
      this.applyIndex(Integer.MAX_VALUE);
   }

   public CustomFont(String text, String text2, int count2, int count3) {
      super(text, FontRegistry.resolveFont(text2, count2), count3);
      this.count = this.font.getSize();
      this.labelText = text2;
      CompletableFuture.runAsync(() -> this.applyIndexByIndex(count3));
   }
}
