package client.util;

import client.gui.TexturesGuiIconsPngManager;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StatCollector;

public class GeometryDispatcher extends TexturesGuiIconsPngManager {
   public int count2 = 200;
   public int count3 = 20;
   public int count4;
   public int count;
   public List list;
   public int count5;
   public boolean enabled;
   public boolean enabled2 = true;
   public boolean enabled3;
   public int count6;
   public int count7;
   public int count8;
   public int count9;
   public FontRenderer fontRenderer;
   public int count10;

   public void applyText(String text) {
      this.list.add(StatCollector.getText(text, new Object[0]));
   }

   public GeometryDispatcher getGeometryDispatcher() {
      this.enabled = true;
      return this;
   }

   public void applyMinecraft(Minecraft mc, int count11, int count12) {
      if (this.enabled3) {
         int count13 = this.count2 + this.count10 * 2;
         int count14 = this.count3 + this.count10 * 2;
         int count15 = this.count4 - this.count10;
         int count16 = this.count - this.count10;
         processAction(count15, count16, count15 + count13, count16 + count14, this.count7);
         this.applyIndex(count15, count15 + count13, count16, this.count8);
         this.applyIndex(count15, count15 + count13, count16 + count14, this.count9);
         this.applyIndexByIndex(count15, count16, count16 + count14, this.count8);
         this.applyIndexByIndex(count15 + count13, count16, count16 + count14, this.count9);
      }
   }

   public void applyMinecraft2(Minecraft mc, int count11, int count12) {
      if (this.enabled2) {
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         this.applyMinecraft(mc, count11, count12);
         int count13 = this.count + this.count3 / 2 + this.count10 / 2 - this.list.size() * 10 / 2;

         for (int index = 0; index < this.list.size(); index++) {
            if (this.enabled) {
               applyFontRenderer4(this.fontRenderer, (String)this.list.get(index), this.count4 + this.count2 / 2, count13 + index * 10, this.count6);
            } else {
               this.applyFontRenderer(this.fontRenderer, (String)this.list.get(index), this.count4, count13 + index * 10, this.count6);
            }
         }
      }
   }

   public GeometryDispatcher(FontRenderer fontRenderer2, int count11, int count12, int count13, int count14, int count15, int count16) {
      this.fontRenderer = fontRenderer2;
      this.count5 = count11;
      this.count4 = count12;
      this.count = count13;
      this.count2 = count14;
      this.count3 = count15;
      this.list = Lists.newArrayList();
      this.enabled = false;
      this.enabled3 = false;
      this.count6 = count16;
      this.count7 = -1;
      this.count8 = -1;
      this.count9 = -1;
      this.count10 = 0;
   }
}
